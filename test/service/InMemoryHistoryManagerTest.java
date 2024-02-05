package service;

import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Status.NEW;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    public void testGetAllReturnsEmptyListWhenTasksIsNull() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        List<Task> tasks = historyManager.getAll();

        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testGetAllReturnsNonEmptyListWhenTasksIsNotNull() {
        Task task = new Task("Test Task", NEW, "Test Task description");
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.addTaskToHistory(task);
        List<Task> tasks = historyManager.getAll();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }

    @Test
    public void testAddTaskToHistory() {
        Task task = new Task("Test Task", NEW, "Test Task description");
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.addTaskToHistory(task);
        List<Task> tasks = historyManager.getAll();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void testAddTaskToHistoryWhenTasksIsNull() {
        Task task = new Task("Test Task", NEW, "Test Task description");
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        List<Task> tasks = null;
        assertNull(tasks);

        historyManager.addTaskToHistory(task);
        tasks = historyManager.getAll();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testAddTaskToHistoryWhenMoreThen10Tasks() {
        Task task1 = new Task("Task 1", NEW, "Description for Task 1");
        Task task2 = new Task("Task 2", NEW, "Description for Task 2");
        Task task3 = new Task("Task 3", NEW, "Description for Task 3");
        Task task4 = new Task("Task 4", NEW, "Description for Task 4");
        Task task5 = new Task("Task 5", NEW, "Description for Task 5");
        Task task6 = new Task("Task 6", NEW, "Description for Task 6");
        Task task7 = new Task("Task 7", NEW, "Description for Task 7");
        Task task8 = new Task("Task 8", NEW, "Description for Task 8");
        Task task9 = new Task("Task 9", NEW, "Description for Task 9");
        Task task10 = new Task("Task 10", NEW, "Description for Task 10");
        Task task11 = new Task("Task 11", NEW, "Description for Task 11");
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.addTaskToHistory(task1);
        historyManager.addTaskToHistory(task2);
        historyManager.addTaskToHistory(task3);
        historyManager.addTaskToHistory(task4);
        historyManager.addTaskToHistory(task5);
        historyManager.addTaskToHistory(task6);
        historyManager.addTaskToHistory(task7);
        historyManager.addTaskToHistory(task8);
        historyManager.addTaskToHistory(task9);
        historyManager.addTaskToHistory(task10);
        historyManager.addTaskToHistory(task11);
        List<Task> tasks = historyManager.getAll();

        assertNotNull(tasks);
        assertEquals(10, tasks.size());
        assertEquals(task2, tasks.get(0));
        assertEquals(task11, tasks.get(9));
    }
}
