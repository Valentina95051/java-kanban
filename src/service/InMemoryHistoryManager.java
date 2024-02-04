package service;

import model.*;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> last10Tasks = new ArrayList<>(10);

    @Override
    public ArrayList<Task> getAll() {
        return last10Tasks;
    }

    @Override
    public void addTaskToHistory(Task task) {
        if(last10Tasks.size() >= 9){
            last10Tasks.remove(0);
            last10Tasks.add(task);
        } else {
            last10Tasks.add(task);
        }
    }
}
