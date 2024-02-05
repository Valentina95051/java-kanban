package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

//интерфейс для управления историей просмотров
public interface HistoryManager<T extends Task> {

    void addTaskToHistory(T task);

    List<Task> getAll();
}
