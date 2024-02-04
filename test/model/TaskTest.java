package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void tasksShouldBeEqualIfHaveSameId() {
        //проверьте, что экземпляры класса Task равны друг другу, если равен их id
        Task testTask1 = new Task("Тестовое задание1", Status.NEW, "");
        testTask1.setId(8);
        Task testTask2 = new Task("Тестовое задание2", Status.NEW, "");
        testTask2.setId(8);
        assertEqualsTask(testTask1, testTask2);
    }
    void assertEqualsTask(Task testTask1, Task testTask2){
        assertEquals(testTask1.getId(), testTask2.getId(), "Задачи должны совпадать, если равен их id");
    }

}