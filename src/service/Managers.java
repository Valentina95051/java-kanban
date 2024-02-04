package service;

public class Managers {

    public static TaskManager getDefaultTaskManager(){
        return new InMemoryTaskTaskManager(new InMemoryHistoryManager());
    }
}
