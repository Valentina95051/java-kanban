package service;
import model.*;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {


    Task createTask(Task task);

    Task getTask(int id);

    List<Task> getAllTasks();

    void updateTask(Task task);

    void deleteTask(int id);

    void deleteAllTasks();

    Epic createEpic(Epic epic);

    Epic getEpic(int id);

    List<Epic> getAllEpics();

    void updateEpic(Epic epic);

    void deleteEpic(int id);

    void deleteAllEpics();

    SubTask createSubTask(SubTask subTask);

    SubTask getSubTask(int id);

    List<SubTask> getAllSubTasks();

    void updateSubTask(SubTask subTask);

    void deleteAllSubTasks();

    void deleteSubTask(int id);

    List<SubTask> getAllSubTasksForEpic(int id);

    void calculateStatus(Epic epic);

    ArrayList<Task> getHistory();

}
