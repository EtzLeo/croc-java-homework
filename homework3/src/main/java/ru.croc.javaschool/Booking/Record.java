package ru.croc.javaschool.Booking;

/**
 * Запись.
 */
public class Record {
    /**
     * Данные.
     */
    private final String data;

    public Record(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Record{" +
                "data='" + data + '\'' +
                '}';
    }
}
