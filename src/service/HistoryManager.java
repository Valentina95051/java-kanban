package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

//интерфейс для управления историей просмотров
public interface HistoryManager {

    void addTaskToHistory(Task task);

    List<Task> getAll();
}
