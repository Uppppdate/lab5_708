package org.example.managers;

import org.example.commands.CommandException;
import org.example.data.*;
import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.sql.SQLException;
import java.util.*;

/**
 * Менеджер для работы с коллекцией
 */
public class CollectionManager {
    /**
     * Переменная, хранящая коллекцию
     */
    private LinkedHashSet<Organization> orgSet;

    /**
     * Дата инициализации
     */
    private final Date initializationDate;

    /**
     * Конструктор, в котором задаётся дата инициализации, а также коллекция
     */
    public CollectionManager() {
        initializationDate = new Date();
        update();
        try {
            IdManager.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(){
        try {
            orgSet = DatabaseManager.loadAllOrganizations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Метод, возвращающий коллекцию
     *
     * @return Объект типа LinkedHashSet, параметризованный Organization, являющийся коллекцией
     */
    public LinkedHashSet<Organization> getOrgSet() {
        return orgSet;
    }

    /**
     * Метод возвращает дату инициализации коллекции
     *
     * @return Объект типа Date, являющийся датой инициализации
     */
    public Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Метод очищает коллекцию
     */
    public void clearCollection() {
        orgSet = new LinkedHashSet<>(orgSet.stream().filter(organization -> organization.getOwnerId()!=AuthorizationManager.currentUserId).toList());
        try {
            DatabaseManager.saveTable(AuthorizationManager.currentUserId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Максимальный объект в коллекции
     */
    public Organization getMax() {
        Optional<Organization> opt = orgSet.stream().max(Comparator.naturalOrder());
        return opt.orElse(null);
    }

    /**
     * Добавляет организацию по массиву данных, который может быть неполным
     *
     * @param data Массив с данными объекта
     * @throws DataErrorException Пробрасывается исключение об ошибке в данных
     */
    public void addOrganizationFromData(String[] data) throws DataErrorException, SQLException {
        try {
            Validator.checkData(data);
        } finally {
            String[] fullsize_data = Arrays.copyOf(data, 13);
            Arrays.fill(fullsize_data, data.length, fullsize_data.length, "0");
            OrganizationBuilder orb = new OrganizationBuilder();
            Organization org = orb
                    .setName(fullsize_data[1])
                    .setCoordinatesX(fullsize_data[2])
                    .setCoordinatesY(fullsize_data[3])
                    .setCreationDate(fullsize_data[4])
                    .setAnnualTurnover(fullsize_data[5])
                    .setEmployeesCount(fullsize_data[6])
                    .setType(fullsize_data[7])
                    .setAddressStreet(fullsize_data[8])
                    .setAddressZipCode(fullsize_data[9])
                    .setLocationX(fullsize_data[10])
                    .setLocationY(fullsize_data[11])
                    .setLocationZ(fullsize_data[12])
                    .build();
            DatabaseManager.addOrganization(AuthorizationManager.currentUserId, org);
            org.setOwnerUsername(AuthorizationManager.currentUserName);
            org.setOwnerId(AuthorizationManager.currentUserId);
            orgSet.add(org);
            IdManager.update();
        }
    }

    /**
     * Удаляет организацию по ID
     * @param id id
     * @throws DataErrorException пробрасывается, если переданного id не существует в коллекции
     */
    public void removeOrganization(Long id) throws DataErrorException {
        if (!IdManager.checkId(id)) {
            throw new DataErrorException("Указанного ID не существует");
        }
        Optional<Organization> organization = orgSet.stream().filter(org -> org.getId().equals(id)).findFirst();
        organization.ifPresent(orgSet::remove);
        try {
            DatabaseManager.saveTable(AuthorizationManager.currentUserId);
            IdManager.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает организацию по переданному ID
     *
     * @param id id
     * @return Организацию с указанным id
     * @throws DataErrorException пробрасывается, если указанного id не существует в коллекции
     */
    public Organization getOrgById(Long id) throws DataErrorException {
        if (!IdManager.checkId(id)) {
            throw new DataErrorException("Указанного ID не существует");
        }
        //пишу сразу get, поскольку ранее уже проверено, что указанный id есть в коллекции
        return orgSet.stream().filter(org -> org.getId().equals(id)).findFirst().get();
    }

    /**
     * Чистит коллекцию от объектов с нулевым id
     */
    public void cleanUpCollection() {
        //проверяю наличие объектов с нулевым ID с помощью потоков
        List<Organization> list = getOrgSet().stream().filter(org -> org.getId() == 0).toList();
        //удаляю каждый найденный объект из коллекции
        for (Organization org : list) {
            getOrgSet().remove(org);
        }
        //вероятно, уже не нужно.
//        //перезаписываю файл без объектов с нулевым ID
//        DataWriter.toSave();
    }
}
