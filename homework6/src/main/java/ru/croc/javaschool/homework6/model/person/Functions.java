package ru.croc.javaschool.homework6.model.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Функция(роль) в фильме.
 * Служит для корректной сериализации данных.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Functions {
    /**
     * Функция (сценарист или режиссер).
     */
    @XmlAttribute(name = "name")
    private String function;

    public Functions(String function) {
        this.function = function;
    }

    public Functions() {
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "FunctionsList{" +
                "function='" + function + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Functions functions = (Functions) o;
        return Objects.equals(function, functions.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(function);
    }
}
