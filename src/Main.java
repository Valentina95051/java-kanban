import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;
import service.HistoryManager;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;
import service.Managers;

import java.util.ArrayList;
import java.util.List;

import static service.Managers.getDefaultHistory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создали две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей:");
        Task task1 = new Task("Доделать финальный проект 4 спринта", Status.NEW,
                "Доделать финальный проект 4 спринта и успешно пройти ревью");
        Task task2 = new Task("Помыть посуду", Status.NEW,
                "");
        List<SubTask> subTasks1 = new ArrayList<>();
        SubTask subTask1 = new SubTask("Пройти оставшиеся уроки", Status.NEW, "");
        SubTask subTask2 = new SubTask("Сдать финальный проект 5 модуля", Status.NEW, "");
        subTasks1.add(subTask1);
        subTasks1.add(subTask2);
        List<SubTask> subTasks2 = new ArrayList<>();
        SubTask subTask3 = new SubTask("Пропылесосить", Status.NEW, "");
        subTasks2.add(subTask3);
        Epic epic1 = new Epic("Закончить 5 модуль", Status.NEW, "До 5 февраля", subTasks1);
        Epic epic2 = new Epic("Уборка", Status.NEW, "По субботам", subTasks2);
        subTask1.setEpic(epic1);
        subTask2.setEpic(epic1);
        subTask3.setEpic(epic2);
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        HistoryManager historyManager = inMemoryTaskManager.getHistoryManager();
        Task testTask1 = inMemoryTaskManager.createTask(task1);
        Task testTask2 = inMemoryTaskManager.createTask(task2);
        Epic testEpic1 = inMemoryTaskManager.createEpic(epic1);
        Epic testEpic2 = inMemoryTaskManager.createEpic(epic2);
        SubTask testST1 = inMemoryTaskManager.createSubTask(subTask1);
        SubTask testST2 = inMemoryTaskManager.createSubTask(subTask2);
        SubTask testST3 = inMemoryTaskManager.createSubTask(subTask3);
        System.out.println(testTask1);
        System.out.println(testTask2);
        System.out.println(testEpic1);
        System.out.println(testST1);
        System.out.println(testST2);
        System.out.println(testEpic2);
        System.out.println(testST3);
        System.out.println("Поменяли статусы:");
        testTask1.setStatus(Status.IN_PROGRESS);
        testTask2.setStatus(Status.DONE);
        testST1.setStatus(Status.IN_PROGRESS);
        testST2.setStatus(Status.DONE);
        testST3.setStatus(Status.DONE);
        inMemoryTaskManager.updateTask(testTask1);
        inMemoryTaskManager.updateTask(testTask2);
        inMemoryTaskManager.updateSubTask(testST1);
        inMemoryTaskManager.updateSubTask(testST2);
        inMemoryTaskManager.updateSubTask(testST3);
        inMemoryTaskManager.updateEpic(testEpic1);
        inMemoryTaskManager.updateEpic(testEpic2);
        System.out.println(testTask1);
        System.out.println(testTask2);
        System.out.println(testEpic1);
        System.out.println(testST1);
        System.out.println(testST2);
        System.out.println(testEpic2);
        System.out.println(testST3);


        System.out.println("Задачи:");
        for (Task task : inMemoryTaskManager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : inMemoryTaskManager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : inMemoryTaskManager.getAllSubTasksForEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : inMemoryTaskManager.getAllSubTasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        inMemoryTaskManager.getTask(0);
        inMemoryTaskManager.getEpic(2);
        inMemoryTaskManager.getSubTask(4);
        for (Task task : inMemoryTaskManager.getHistory()) {
            System.out.println(task);
        }
    }
}