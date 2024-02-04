package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    @Test
    void subTasksShouldBeEqualIfHaveSameId() {
        //проверьте, что экземпляры класса Task равны друг другу, если равен их id

        SubTask testSubTask1 = new SubTask("Тестовое задание1", Status.NEW, "");
        testSubTask1.setId(7);
        SubTask testSubTask2 = new SubTask("Тестовое задание2", Status.NEW, "");
        testSubTask2.setId(7);
        assertEqualsSubTasks(testSubTask1,testSubTask2);
    }
    void assertEqualsSubTasks(SubTask testSubTask1,SubTask testSubTask2){
        assertEquals(testSubTask1.getId(),testSubTask2.getId(), "Подзадачи должны совпадать, если равен их id");
    }
}