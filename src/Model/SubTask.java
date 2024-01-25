package Model;

public class SubTask extends Task {
    Epic epic;

    public SubTask(String name, Service.Status status, String description) {
        super(name, status, description);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
