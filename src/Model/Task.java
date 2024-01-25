package Model;

import java.security.Provider;

public class Task {
    private int id;
    private String name;
    private String description;
    private Service.Status status;

    public Task(String name, Service.Status status, String description) {
    this.name = name;
    this.status = status;
    this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service.Status getStatus() {
        return status;
    }

    public void setStatus(Service.Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
