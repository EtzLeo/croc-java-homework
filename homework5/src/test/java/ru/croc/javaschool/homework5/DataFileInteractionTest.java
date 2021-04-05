package ru.croc.javaschool.homework5;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework5.controller.DataFileInteraction;
import ru.croc.javaschool.homework5.model.Status;
import ru.croc.javaschool.homework5.model.Task;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFileInteractionTest {

    DataFileInteraction dataFileInteraction = new DataFileInteraction("test.txt");
    Task task1 = new Task(1, "name1", "desc1", "Ivan", Status.BACKLOG);
    Task task2 = new Task(2, "name2", "desc2", "Bogdan", Status.IN_PROGRESS);
    Task task3 = new Task(3, "name3", "desc3", "Vladimir", Status.COMPLETE);

    @Test
    @Description("Тест получения списка задач.")
    public void testGetTasksList() {
        try (PrintWriter writer = new PrintWriter("test.txt")) {
            writer.print("");
        } catch (FileNotFoundException ignored) {
        }

        List<Task> tasks = new ArrayList<>();
        Assertions.assertEquals(tasks, dataFileInteraction.getTasksList());

        dataFileInteraction.createTask(task1);
        dataFileInteraction.createTask(task2);
        dataFileInteraction.createTask(task3);

        tasks = Arrays.asList(task1,task2,task3);

        Assertions.assertEquals(tasks, dataFileInteraction.getTasksList());
    }

    @Test
    @Description("Тест получения задачи по id.")
    public void testGetTaskById() {
        try (PrintWriter writer = new PrintWriter("test.txt")) {
            writer.print("");
        } catch (FileNotFoundException ignored) {
        }

        Assertions.assertNull(dataFileInteraction.getTaskById(2));

        dataFileInteraction.createTask(task1);
        dataFileInteraction.createTask(task2);
        dataFileInteraction.createTask(task3);

        Assertions.assertEquals(task2, dataFileInteraction.getTaskById(2));
    }
    @Test
    @Description("Тест получения id последней задачи.")
    public void testGetLastId() {
        try (PrintWriter writer = new PrintWriter("test.txt")) {
            writer.print("");
        } catch (FileNotFoundException ignored) {
        }

        Assertions.assertEquals(0, dataFileInteraction.getLastId());

        dataFileInteraction.createTask(task1);
        dataFileInteraction.createTask(task2);
        dataFileInteraction.createTask(task3);

        Assertions.assertEquals(task3.getId(), dataFileInteraction.getLastId());
    }
}
