package ru.croc.javaschool.Booking;

import java.util.Arrays;

/**
 * Система учета.
 */
public final class AccountingSystem {
    /**
     * Экземпляр этого класса.
     */
    private static AccountingSystem instance;

    /**
     * Записи системы учета.
     */
    public Record[] records = new Record[] {};

    /**
     * Конструктор с модификатором доступа Private.
     *
     * @param records записи
     */
    private AccountingSystem(Record[] records) {
        this.records = records;
    }

    /**
     * Получить экземпляр.
     *
     * @param records записи
     * @return экземпляр
     */
    public static AccountingSystem getInstance(Record[] records) {
        if (instance == null) {
            instance = new AccountingSystem(records);
        }
        return instance;
    }

    /**
     * Добавить новую запись.
     *
     * @param record запись
     */
    public void add(Record record)  {
        records = Arrays.copyOf(records, records.length + 1);
        records[records.length - 1] = record;
    }

    /**
     * Удаляет запись из справочника.
     *
     * @param record удаляемая запись
     */
    public void remove(Record record) {
        final Record[] newRecords = new Record[records.length - 1];
        int index = 0;
        for (Record currentRecord : records) {
            if (currentRecord != record) {
                newRecords[index++] = currentRecord;
            }
        }
        records = newRecords;
    }

    /**
     * Возвращает все записи.
     *
     * @return список записей
     */
    public Record[] records() {
        return Arrays.copyOf(records, records.length);
    }
}
