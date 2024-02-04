package model;

import java.util.Objects;

public class SubTask extends Task {
    Epic epic;

    public SubTask(String name, Status status, String description, Epic epic) {
        super(name, status, description);
        this.epic = epic;
    }

    public SubTask(String name, Status status, String description) {
        super(name, status, description);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() + '\'' +
                ", epic=" + epic.getName() +
        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTask subTask)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(epic, subTask.epic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epic);
    }
}
