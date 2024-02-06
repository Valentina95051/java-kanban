package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks= new HashMap<>();


    private final HistoryManager historyManager = Managers.getDefaultHistory();

    public HistoryManager getHistoryManager(){
        return this.historyManager;
    }

    int count = 0;

    int generateId() {
        return count++;
    }

    @Override
    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Task getTask(int id) {
        historyManager.addTaskToHistory(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.addTaskToHistory(epics.get(id));
        return epics.get(id);
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
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

    @Override
    public void deleteEpic(int id) {
        Epic epic = epics.get(id);
        List<SubTask> subTasksList = epic.getSubTasks();
        for (SubTask subTask : subTasksList) {
            subTasks.remove(subTask.getId());
        }
        epics.remove(id);
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        deleteAllSubTasks();
    }


    @Override
    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        Epic epic = subTask.getEpic();
        epic.setSubTasks(getAllSubTasks());
        subTasks.put(subTask.getId(), subTask);
        calculateStatus(epic);
        return subTask;
    }

    @Override
    public SubTask getSubTask(int id) {
        historyManager.addTaskToHistory(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public List<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }


    @Override
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

    @Override
    public void deleteAllSubTasks() {
        for (int id : subTasks.keySet()) {
            deleteSubTask(id);
        }
    }

    @Override
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

    @Override
    public List<SubTask> getAllSubTasksForEpic(int id) {
        Epic epic = epics.get(id);
        return epic.getSubTasks();
    }

    @Override
    public void calculateStatus(Epic epic) {
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

    @Override
    public List<Task> getHistory() {
        return historyManager.getAll();
    }
}

