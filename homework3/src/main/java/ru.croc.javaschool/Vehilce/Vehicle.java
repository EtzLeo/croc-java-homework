package ru.croc.javaschool.Vehilce;


import java.time.LocalDate;

/**
 * Транспортное средство.
 */
public abstract class Vehicle {
    /**
     * Тип транспортного средства.
     */
    protected final String type;

    /**
     * Производитель.
     */
    protected final String manufacturer;

    /**
     * Сдача в аренду.
     * Возможен null в случае отсутствия аренды.
     */
    protected Rent rent = null;

    /**
     * Техническое состояние.
     */
    protected TechnicalCondition technicalCondition = TechnicalCondition.WORKING;

    /**
     * Создание нового объекта с определенными значениями.
     *
     * @param type тип транспортного средства
     * @param manufacturer производитель
     */
    public Vehicle(String type, String manufacturer) {
        this.type = type;
        this.manufacturer = manufacturer;
    }

    /**
     * Арендован ли транспорт.
     *
     * @return true, если арендован; иначе - false
     */
    public boolean isRent() {
        return rent != null;
    }

    /**
     * Установка аренды.
     * Невозможно арендовать, если транспорт сломан или арендован.
     *
     * @param rent аренда
     */
    public void setRent(Rent rent) {
        if (this.getTechnicalCondition() == TechnicalCondition.BROKEN){
            System.out.println("Ошибка. Транспорт не может быть сдан.");
        }
        else
            if (this.isRent()){
            System.out.println("Ошибка. Транспорт уже арендован.");
        }
            else {
                this.rent = rent;
            }
    }

    /**
     * Снятие с аренды.
     */
    public void takeOffRent(){
        rent = null;
    }

    /**
     * Изменение даты окончания аренды.
     * Невозможно изменить дату, если транспорт не арендован.
     *
     * @param endDate дата окончания аренды
     */
    public void changeRent(LocalDate endDate){
        if (!this.isRent()){
            System.out.println("Ошибка. Траснспорт не сдан.");
        }
        else {
            this.rent.setEnd(endDate);
        }
    }

    public TechnicalCondition getTechnicalCondition() {
        return technicalCondition;
    }

    public void setTechnicalCondition(TechnicalCondition technicalCondition) {
        this.technicalCondition = technicalCondition;
    }

    public String getType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Поломка.
     * В случае поломки автоматически снимаем транспорт с аренды.
     */
    public void broke(){
        this.technicalCondition = TechnicalCondition.BROKEN;
        takeOffRent();
    }

    /**
     * Починка.
     */
    public void repair(){
        this.technicalCondition = TechnicalCondition.WORKING;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", rent=" + rent +
                ", technicalCondition=" + technicalCondition +
                '}';
    }
}
