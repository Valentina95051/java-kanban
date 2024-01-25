package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Epic;
import Model.SubTask;
import Model.Task;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;

    public TaskManager(HashMap<Integer, Task> tasks, HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subTasks) {
        this.tasks = tasks;
        this.epics = epics;
        this.subTasks = subTasks;
    }

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    int count = 0;

    private int generateId() {
        return count++;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
        saved.setSubTasks(epic.getSubTasks());
        epics.put(epic.getId(), saved);
    }

    public void deleteEpic(int id) {
        Epic epic = epics.get(id);
        List<SubTask> subTasksList = epic.getSubTasks();
        for (SubTask subTask : subTasksList) {
            subTasks.remove(subTask.getId());
        }
        epics.remove(id);
    }

    public void deleteAllEpics() {
        epics.clear();
    }


    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        Epic epic = subTask.getEpic();
        epic.setSubTasks(getAllSubTasks());
        subTasks.put(subTask.getId(), subTask);
        calculateStatus(epic);
        return subTask;
    }

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    public List<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public void updateSubTask(SubTask subTask) {
        Epic epic = subTask.getEpic();
        Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        SubTask savedST = subTasks.get(subTask.getId());
        if (savedST == null) {
            return;
        }
        savedST.setName(subTask.getName());
        savedST.setDescription(subTask.getDescription());
        savedST.setEpic(savedEpic);
        calculateStatus(savedEpic);
        epics.put(epic.getId(), savedEpic);
        subTasks.put(subTask.getId(), savedST);
    }

    public void deleteAllSubTasks() {
        subTasks.clear();
    }

    public void deleteSubTask(int id) {
        if (!subTasks.containsKey(id)) {
            System.out.println("Такого id не существует");
            return;
        }
        SubTask removeST = subTasks.remove(id);
        Epic epic = removeST.getEpic();
        Epic epicSaved = epics.get(epic.getId());
        epicSaved.getSubTasks().remove(removeST);
        calculateStatus(epicSaved);
    }

    public List<SubTask> getAllSubTasksForEpic(int id) {
        Epic epic = epics.get(id);
        return epic.getSubTasks();
    }

    private void calculateStatus(Epic epic) {
        List<SubTask> subTaskList = epic.getSubTasks();
        boolean allSTNEW = true;
        boolean allSTDONE = true;
        for (SubTask subTask : subTaskList) {
            if (subTask.getStatus() != Status.NEW) {
                allSTNEW = false;
            } else if (subTask.getStatus() != Status.DONE) {
                allSTDONE = false;
            }
        }
        if (subTaskList.isEmpty() || allSTNEW) {
            epic.setStatus(Status.NEW);
        } else if (allSTDONE) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}

