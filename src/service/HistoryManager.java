package service;
import model.*;
import java.util.ArrayList;
//интерфейс для управления историей просмотров
public interface HistoryManager <T extends Task>{

    void addTaskToHistory(T task);

ArrayList<T> getAll();
}
