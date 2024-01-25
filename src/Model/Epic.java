package Model;

import Service.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    List<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, Status status, String description, List<SubTask> subTasks) {
        super(name, status, description);
        this.subTasks = subTasks;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
