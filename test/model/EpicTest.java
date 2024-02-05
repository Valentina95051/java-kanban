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


    /*@Test
    void shouldNotAddEpicIntoItSelf(){
        Проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи - тест есть в ТЗ, но само устройство
        кода не допускает такого действия и попытка запуска теста приводит к ошибке компиляции. В итоге наставник
        написал что в ТЗ ошибка и этот тест делать не нужно.
        List<SubTask> testSubtasks = new ArrayList<>();
        Epic testEpic = new Epic("Тестовое задание1", Status.NEW, "", testSubtasks);
        assertThrows(IllegalArgumentException.class, () -> testSubtasks.add(testEpic));
    }*/
}
