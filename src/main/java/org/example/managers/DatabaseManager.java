package org.example.managers;

import org.example.commands.CommandException;
import org.example.data.*;
import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.example.Main.clm;

public class DatabaseManager {
    public static void addOrganization(long ownerId, Organization organization) throws SQLException {
        Connection connection = null;
        long organizationId = -1;

        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            // 1. Добавляем локацию (town)
            Location town = organization.getOfficialAddress().getTown();
            long locationId = insertLocation(connection, town.getX(), town.getY(), town.getZ());

            // 2. Добавляем адрес
            Address address = organization.getOfficialAddress();
            long addressId = insertAddress(connection, address.getStreet(), address.getZipCode(), locationId);

            // 3. Добавляем организацию (с привязкой к ID пользователя)
            organizationId = insertOrganization(connection, organization, addressId, ownerId);

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private static long insertLocation(Connection connection, double x, double y, Long z) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO s465521.locations (x, y, z) VALUES (?, ?, ?) RETURNING id",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, x);
            ps.setDouble(2, y);
            if (z != null) ps.setLong(3, z);
            else ps.setNull(3, Types.BIGINT);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                else throw new SQLException("Location insertion failed");
            }
        }
    }

    private static long insertAddress(Connection connection, String street, String zipCode, long townId)
            throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO s465521.addresses (street, zip_code, town_id) VALUES (?, ?, ?) RETURNING id",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, street);
            ps.setString(2, zipCode);
            ps.setLong(3, townId);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                else throw new SQLException("Address insertion failed");
            }
        }
    }

    private static long insertOrganization(Connection connection, Organization org, long addressId, long ownerId)
            throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO s465521.organizations ("
                        + "name, creation_date, annual_turnover, employees_count, type, "
                        + "coordinates_x, coordinates_y, address_id, owner_id) "
                        + "VALUES (?, ?, ?, ?, ?::s465521.organization_type, ?, ?, ?, ?) RETURNING id",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, org.getName());
            ps.setTimestamp(2, new Timestamp(org.getCreationDate().getTime()));
            ps.setFloat(3, org.getAnnualTurnover());

            if (org.getEmployeesCount() != null) ps.setInt(4, org.getEmployeesCount());
            else ps.setNull(4, Types.INTEGER);

            ps.setString(5, org.getType().name());

            Coordinates coord = org.getCoordinates();
            ps.setLong(6, coord.getX());
            ps.setDouble(7, coord.getY());

            ps.setLong(8, addressId);
            ps.setLong(9, ownerId);  // Используем переданный ID пользователя

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                else throw new SQLException("Organization insertion failed");
            }
        }
    }

    public static void saveTable(long ownerId) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            // 1. Получаем текущие ID организаций пользователя из БД
            HashSet<Long> dbIds = getOrganizationIdsForUser(connection, ownerId);

            // 2. Создаем копию коллекции для обработки
            HashSet<Organization> currentOrgs = new HashSet<>(clm.getOrgSet());

            // 3. Обновляем или добавляем организации
            Iterator<Organization> iterator = currentOrgs.iterator();
            while (iterator.hasNext()) {
                Organization org = iterator.next();
                if (dbIds.contains(org.getId())) {
                    // Обновляем существующую запись
                    updateOrganization(connection, ownerId, org);
                    dbIds.remove(org.getId()); // Помечаем как обработанную
                } else {
                    // Добавляем новую организацию
                    long newId = addOrganization(connection, ownerId, org);
                    org.setId(newId); // Обновляем ID в объекте
                }
            }

            // 4. Удаляем организации, отсутствующие в коллекции
            for (Long idToDelete : dbIds) {
                deleteOrganization(connection, ownerId, idToDelete);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private static HashSet<Long> getOrganizationIdsForUser(Connection connection, long ownerId)
            throws SQLException {

        HashSet<Long> ids = new HashSet<>();
        String sql = "SELECT id FROM s465521.organizations WHERE owner_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, ownerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getLong("id"));
                }
            }
        }
        return ids;
    }

    private static void updateOrganization(Connection connection, long ownerId, Organization org)
            throws SQLException {

        // 1. Обновляем локацию
        Location town = org.getOfficialAddress().getTown();
        updateLocation(connection, town, getLocationIdForAddress(connection, org.getOfficialAddress()));

        // 2. Обновляем адрес
        updateAddress(connection, org.getOfficialAddress());

        // 3. Обновляем организацию
        String sql = "UPDATE s465521.organizations SET " +
                "name = ?, creation_date = ?, annual_turnover = ?, " +
                "employees_count = ?, type = ?::s465521.organization_type, " +
                "coordinates_x = ?, coordinates_y = ? " +
                "WHERE id = ? AND owner_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, org.getName());
            ps.setTimestamp(2, new Timestamp(org.getCreationDate().getTime()));
            ps.setFloat(3, org.getAnnualTurnover());

            if (org.getEmployeesCount() != null) {
                ps.setInt(4, org.getEmployeesCount());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setString(5, org.getType().name());

            Coordinates coord = org.getCoordinates();
            ps.setLong(6, coord.getX());
            ps.setDouble(7, coord.getY());

            ps.setLong(8, org.getId());
            ps.setLong(9, ownerId);

            ps.executeUpdate();
        }
    }

    private static long addOrganization(Connection connection, long ownerId, Organization org)
            throws SQLException {

        // 1. Добавляем локацию
        Location town = org.getOfficialAddress().getTown();
        long locationId = insertLocation(connection, town.getX(), town.getY(), town.getZ());

        // 2. Добавляем адрес
        Address address = org.getOfficialAddress();
        long addressId = insertAddress(connection, address.getStreet(), address.getZipCode(), locationId);

        // 3. Добавляем организацию
        String sql = "INSERT INTO s465521.organizations (" +
                "id, name, creation_date, annual_turnover, employees_count, " +
                "type, coordinates_x, coordinates_y, address_id, owner_id) " +
                "VALUES (?, ?, ?, ?, ?, ?::s465521.organization_type, ?, ?, ?, ?) " +
                "RETURNING id";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, org.getId()); // Используем существующий ID
            ps.setString(2, org.getName());
            ps.setTimestamp(3, new Timestamp(org.getCreationDate().getTime()));
            ps.setFloat(4, org.getAnnualTurnover());

            if (org.getEmployeesCount() != null) {
                ps.setInt(5, org.getEmployeesCount());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, org.getType().name());

            Coordinates coord = org.getCoordinates();
            ps.setLong(7, coord.getX());
            ps.setDouble(8, coord.getY());

            ps.setLong(9, addressId);
            ps.setLong(10, ownerId);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                else throw new SQLException("Organization insertion failed");
            }
        }
    }

    private static void deleteOrganization(Connection connection, long ownerId, long orgId)
            throws SQLException {

        // Удаление через каскадное удаление (addresses и locations удалятся автоматически)
        String sql = "DELETE FROM s465521.organizations WHERE id = ? AND owner_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, orgId);
            ps.setLong(2, ownerId);
            ps.executeUpdate();
        }
    }

    private static long getLocationIdForAddress(Connection connection, Address address) throws SQLException {
        String sql = "SELECT town_id FROM s465521.addresses WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Предполагаем, что в Address есть поле id
            ps.setLong(1, address.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("town_id");
                } else {
                    throw new SQLException("Address not found");
                }
            }
        }
    }

    private static void updateLocation(Connection connection, Location location, long locationId) throws SQLException {
        String sql = "UPDATE s465521.locations SET x = ?, y = ?, z = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, location.getX());
            ps.setDouble(2, location.getY());

            if (location.getZ() != null) {
                ps.setLong(3, location.getZ());
            } else {
                ps.setNull(3, Types.BIGINT);
            }

            ps.setLong(4, locationId);
            ps.executeUpdate();
        }
    }

    private static void updateAddress(Connection connection, Address address) throws SQLException {
        String sql = "UPDATE s465521.addresses SET street = ?, zip_code = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getZipCode());

            // Предполагаем, что в Address есть поле id
            ps.setLong(3, address.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Загружает все организации из базы данных, независимо от владельца
     *
     * @return HashSet всех организаций
     * @throws SQLException при ошибках работы с БД
     */
    public static LinkedHashSet<Organization> loadAllOrganizations() throws SQLException {
        LinkedHashSet<Organization> organizations = new LinkedHashSet<>();
        String sql = "SELECT o.*, a.id AS address_id, a.street, a.zip_code, " +
                "l.id AS location_id, l.x AS loc_x, l.y AS loc_y, l.z, " +
                "u.username AS owner_username " +
                "FROM s465521.organizations o " +
                "JOIN s465521.addresses a ON o.address_id = a.id " +
                "JOIN s465521.locations l ON a.town_id = l.id " +
                "JOIN s465521.users u ON o.owner_id = u.id";

        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Organization org = mapOrganizationFromResultSet(rs);
                organizations.add(org);
            }
        }
        return organizations;
    }

    /**
     * Создает объект Organization из ResultSet
     */
    private static Organization mapOrganizationFromResultSet(ResultSet rs) throws SQLException {
        // Создаем Location
        Location town = new Location();
        town.setId(rs.getLong("location_id"));
        town.setX(rs.getDouble("loc_x"));
        town.setY(rs.getDouble("loc_y"));
        town.setZ(rs.getObject("z", Long.class));

        // Создаем Address
        Address address = new Address();
        address.setId(rs.getLong("address_id"));
        address.setStreet(rs.getString("street"));
        address.setZipCode(rs.getString("zip_code"));
        address.setTown(town);

        // Создаем Organization
        Organization org = new Organization();
        org.setId(rs.getLong("id"));
        org.setName(rs.getString("name"));
        org.setCreationDate(rs.getTimestamp("creation_date"));
        org.setAnnualTurnover(rs.getFloat("annual_turnover"));
        org.setEmployeesCount(rs.getObject("employees_count", Integer.class));
        org.setType(OrganizationType.valueOf(rs.getString("type")));

        Coordinates coordinates = new Coordinates();
        coordinates.setX(rs.getLong("coordinates_x"));
        coordinates.setY(rs.getDouble("coordinates_y"));
        org.setCoordinates(coordinates);

        org.setOfficialAddress(address);

        // Добавляем информацию о владельце
        org.setOwnerId(rs.getLong("owner_id"));
        org.setOwnerUsername(rs.getString("owner_username"));

        return org;
    }

    /**
     * Проверяет, является ли пользователь владельцем организации
     *
     * @param userId ID пользователя
     * @param orgId  id организации для проверки
     * @return true если пользователь может редактировать организацию
     */
    public static boolean canEditOrganization(long userId, Long orgId) {
        return orgId.equals(userId);
    }

    /**
     * Удаляет все организации текущего пользователя
     *
     * @return количество удаленных организаций
     * @throws SQLException при ошибках работы с БД
     */
    public static int clear() throws SQLException {
        try (Connection connection = ConnectionManager.getConnection()) {
            // Удаление организаций (каскадно удалит связанные адреса и локации)
            String sql = "DELETE FROM s465521.organizations WHERE owner_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, AuthorizationManager.currentUserId);
                int deletedCount = ps.executeUpdate();

                // Обновляем кеш ID организаций
                IdManager.update();

                return deletedCount;
            }
        }
    }

    /**
     * Обновляет организацию по ID
     *
     * @param id      ID организации для обновления
     * @param data    Массив данных для обновления в порядке:
     *                [0] - name (String)
     *                [1] - coordinates.x (long)
     *                [2] - coordinates.y (double)
     *                [3] - creationDate (String в формате "yyyy-MM-dd HH:mm:ss")
     *                [4] - annualTurnover (float)
     *                [5] - employeesCount (Integer) или null
     *                [6] - type (OrganizationType как String)
     *                [7] - address.street (String)
     *                [8] - address.zipCode (String)
     *                [9] - address.town.x (double)
     *                [10] - address.town.y (double)
     *                [11] - address.town.z (Long) или null
     * @param ownerId ID владельца организации (для проверки прав)
     * @return true если обновление прошло успешно
     * @throws SQLException             при ошибках работы с БД
     * @throws IllegalArgumentException при неверных данных
     */
    public static boolean updateById(long id, String[] data, long ownerId)
            throws SQLException, IllegalArgumentException, CommandException, DataErrorException {

        // Проверяем, что массив данных содержит все необходимые поля
        if (data == null || data.length < 12) {
            throw new IllegalArgumentException("Недостаточно данных для обновления организации. " +
                    "Ожидается 12 полей, получено " + (data == null ? "null" : data.length));
        }

        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            // 1. Проверяем права на редактирование
            if (!DatabaseManager.canEditOrganization(ownerId, clm.getOrgById(id).getOwnerId())) {
                throw new CommandException("У вас нет прав для редактирования этой организации");
            }

            // 2. Получаем текущие связанные ID (адреса и локации)
            long[] relatedIds = getRelatedIds(connection, id);
            long addressId = relatedIds[0];
            long locationId = relatedIds[1];

            // 3. Парсим дату создания
            java.util.Date creationDate;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                creationDate = dateFormat.parse(data[3]);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Неверный формат даты. Используйте формат: yyyy-MM-dd HH:mm:ss");
            }


            // 4. Обновляем локацию
            updateLocation(connection, locationId,
                    Double.parseDouble(data[9]),  // town.x
                    Double.parseDouble(data[10]), // town.y
                    data[11] != null && !data[11].isEmpty() ? Long.parseLong(data[11]) : null // town.z
            );

            // 5. Обновляем адрес
            updateAddress(connection, addressId, data[7], data[8]);

            // 6. Обновляем организацию
            String sql = "UPDATE s465521.organizations SET " +
                    "name = ?, coordinates_x = ?, coordinates_y = ?, " +
                    "creation_date = ?, annual_turnover = ?, " +
                    "employees_count = ?, type = ?::s465521.organization_type " +
                    "WHERE id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, data[0]); // name
                ps.setLong(2, Long.parseLong(data[1])); // coordinates.x
                ps.setDouble(3, Double.parseDouble(data[2])); // coordinates.y
                ps.setTimestamp(4, new Timestamp(creationDate.getTime()));
                ps.setFloat(5, Float.parseFloat(data[4])); // annualTurnover

                if (data[5] != null && !data[5].isEmpty()) {
                    ps.setInt(6, Integer.parseInt(data[5])); // employeesCount
                } else {
                    ps.setNull(6, Types.INTEGER);
                }

                ps.setString(7, data[6].toUpperCase()); // type
                ps.setLong(8, id);

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Организация не была обновлена, возможно, не найдена");
                }
            }

            connection.commit();
            return true;
        } catch (SQLException | NumberFormatException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private static long[] getRelatedIds(Connection conn, long organizationId) throws SQLException {
        String sql = "SELECT address_id, a.town_id FROM s465521.organizations o " +
                "JOIN s465521.addresses a ON o.address_id = a.id " +
                "WHERE o.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, organizationId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new long[]{rs.getLong("address_id"), rs.getLong("town_id")};
                }
                throw new SQLException("Организация с ID " + organizationId + " не найдена");
            }
        }
    }

    private static void updateLocation(Connection conn, long locationId,
                                       Double x, Double y, Long z) throws SQLException {
        String sql = "UPDATE s465521.locations SET x = ?, y = ?, z = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, x);
            ps.setDouble(2, y);

            if (z != null) {
                ps.setLong(3, z);
            } else {
                ps.setNull(3, Types.BIGINT);
            }

            ps.setLong(4, locationId);
            ps.executeUpdate();
        }
    }

    private static void updateAddress(Connection conn, long addressId,
                                      String street, String zipCode) throws SQLException {
        String sql = "UPDATE s465521.addresses SET street = ?, zip_code = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, street);
            ps.setString(2, zipCode);
            ps.setLong(3, addressId);
            ps.executeUpdate();
        }
    }
}
