package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> tasks;

    @Override
    public List<Task> getAll() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    @Override
    public void addTaskToHistory(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
            tasks.add(task);
        } else if (tasks.size() >= 10) {
            tasks.remove(0);
            tasks.add(task);
        } else if (task == null) {
            return;
        } else {
            tasks.add(task);
        }
    }
}
