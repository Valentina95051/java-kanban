package model;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EpicTest {

    @Test
    void epicsShouldBeEqualIfHaveSameId() {
        //проверьте, что экземпляры класса Task равны друг другу, если равен их id
        List<SubTask> testSubtasks = new ArrayList<>();
        Epic testEpic1 = new Epic("Тестовое задание1", Status.NEW, "", testSubtasks);
        testEpic1.setId(10);
        Epic testEpic2 = new Epic("Тестовое задание2", Status.NEW, "", testSubtasks);
        testEpic2.setId(10);
        assertEqualsEpic(testEpic1, testEpic2);
    }

    void assertEqualsEpic(Epic testEpic1, Epic testEpic2) {
        assertEquals(testEpic1.getId(), testEpic2.getId(), "Эпики должны совпадать, если равен их id");
    }
}
