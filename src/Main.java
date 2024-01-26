import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;
import service.TaskManager;

import java.util.ArrayList;
import java.util.List;

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
        TaskManager taskManager = new TaskManager();
        Task testTask1 = taskManager.createTask(task1);
        Task testTask2 = taskManager.createTask(task2);
        Epic testEpic1 = taskManager.createEpic(epic1);
        Epic testEpic2 = taskManager.createEpic(epic2);
        SubTask testST1 = taskManager.createSubTask(subTask1);
        SubTask testST2 = taskManager.createSubTask(subTask2);
        SubTask testST3 = taskManager.createSubTask(subTask3);
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
        taskManager.updateTask(testTask1);
        taskManager.updateTask(testTask2);
        taskManager.updateSubTask(testST1);
        taskManager.updateSubTask(testST2);
        taskManager.updateSubTask(testST3);
        taskManager.updateEpic(testEpic1);
        taskManager.updateEpic(testEpic2);
        System.out.println(testTask1);
        System.out.println(testTask2);
        System.out.println(testEpic1);
        System.out.println(testST1);
        System.out.println(testST2);
        System.out.println(testEpic2);
        System.out.println(testST3);
        System.out.println("Удалили одну из задач и один эпик:");
        taskManager.deleteTask(testTask1.getId());
        taskManager.deleteEpic(testEpic2.getId());
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubTasksForEpic(testEpic1.getId()));
    }
}
