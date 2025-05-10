package org.example.managers;

import org.example.data.*;
import org.example.files.DataErrorException;
import org.example.files.DataParser;
import org.example.files.DataWriter;

import java.util.*;

/**
 * Менеджер для работы с коллекцией
 */
public class CollectionManager {
    /**
     * Переменная, хранящая коллекцию
     */
    private static LinkedHashSet<Organization> orgSet;

    /**
     * Дата инициализации
     */
    private static Date initializationDate;

    public CollectionManager() {
        initializationDate = new Date();
        orgSet = new LinkedHashSet<>();
    }

    public static LinkedHashSet<Organization> getOrgSet() {
        return orgSet;
    }

    public static Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Очищает коллекцию
     */
    public static void clearCollection() {
        orgSet.clear();
    }

    /**
     * @return Максимальный объект в коллекции
     */
    public static Organization getMax() {
        Optional<Organization> opt = orgSet.stream().max(Comparator.naturalOrder());
        return opt.orElse(null);
    }

    /**
     * Добавляет организацию по не обязательно полным данным
     *
     * @param data
     * @throws DataErrorException
     */
    public static void addOrganizationFromData(String[] data) throws DataErrorException {
        try {
            Validator.checkData(data);
        } finally {
            String[] fullsize_data = Arrays.copyOf(data, 13);
            Arrays.fill(fullsize_data, data.length, fullsize_data.length, "0");
            OrganizationBuilder orb = new OrganizationBuilder();
            Organization org = orb
                    .setId(fullsize_data[0])
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
            orgSet.add(org);
            IdManager.addId(org.getId());
        }
    }

    /**
     * Удаляет организацию по ID
     *
     * @param id
     * @throws DataErrorException
     */
    public static void removeOrganization(Long id) throws DataErrorException {
        if (!IdManager.checkId(id)) {
            throw new DataErrorException("Указанного ID не существует");
        }
        IdManager.removeId(id);
        Optional<Organization> organization = orgSet.stream().filter(org -> org.getId().equals(id)).findFirst();
        organization.ifPresent(value -> orgSet.remove(value));
    }

    /**
     * Возвращает организацию по переданному ID
     *
     * @param id
     * @return Организацию по id
     * @throws DataErrorException
     */
    public static Organization getOrgById(Long id) throws DataErrorException {
        if (!IdManager.checkId(id)) {
            throw new DataErrorException("Указанного ID не существует");
        }
        //пишу сразу get, поскольку ранее уже проверено, что указанный id есть в коллекции
        return orgSet.stream().filter(org -> org.getId().equals(id)).findFirst().get();
    }

    /**
     * Чистит коллекцию от объектов с нулевым id
     */
    public static void cleanUpCollection() {
        //проверяю наличие объектов с нулевым ID с помощью потоков
        List<Organization> list = CollectionManager.getOrgSet().stream().filter(org -> org.getId() == 0).toList();
        //удаляю каждый найденный объект из коллекции
        for (Organization org : list) {
            CollectionManager.getOrgSet().remove(org);
        }
        //перезаписываю файл без объектов с нулевым ID
        DataWriter.toSave();
    }
}
