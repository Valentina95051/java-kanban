package service;


import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Status.*;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    @Test
    void shouldUpdateTask() {
        Task task = new Task("Test updateTask", NEW, "Test updateTask description");
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(historyManager);
        taskManager.updateTask(task);

        final Task savedTask = taskManager.getTask(task.getId());

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void shouldUpdateEpic() {
        List<SubTask> testSubTasks = new ArrayList<>();
        Epic epic = new Epic("Test Epic Name", NEW, "", testSubTasks);
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(historyManager);
        Epic testEpic = taskManager.createEpic(epic);
        taskManager.updateEpic(testEpic);

        final Epic savedEpic = taskManager.getEpic(testEpic.getId());

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(testEpic, savedEpic, "Эпики не совпадают.");

        List<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(testEpic, epics.get(0), "Задачи не совпадают.");
    }


    @Test
    void shouldUpdateSubTask() {

        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        InMemoryTaskManager taskManager = new InMemoryTaskManager(historyManager);
        List<SubTask> testSubTasks = new ArrayList<>();
        //testSubTasks.add(subTask);
        Epic epic = new Epic("Test Epic Name", NEW, "", testSubTasks);
        SubTask subTask = new SubTask("Test updateTask", NEW, "Test updateTask description", epic);
        SubTask testSubTask = taskManager.createSubTask(subTask);
        taskManager.updateSubTask(testSubTask);

        SubTask savedSubTask = taskManager.getSubTask(testSubTask.getId());

        assertNotNull(savedSubTask, "Задача не найдена.");
        assertEquals(testSubTask, savedSubTask, "Задачи не совпадают.");

        List<SubTask> subTasks = taskManager.getAllSubTasks();

        assertNotNull(subTasks, "Задачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество задач.");
        assertEquals(testSubTask, subTasks.get(0), "Задачи не совпадают.");
    }
}