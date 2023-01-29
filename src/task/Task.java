package task;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Task {
    private static int counter = 1;
    public enum TaskType {
        PERSONAL,
        WORK
    }


    private String taskName;
    private TaskType type;
    private String description;
    private LocalDate date;
    private final int id;

    public Task(
            String taskName,
            TaskType type,
            String description)
    {
        this.id = counter;
        this.date = LocalDate.now();
        this.taskName = taskName;
        this.type = type;
        this.description = description;
        counter++;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(int year, int month, int dayOfMonth) {
        this.date.of(year, month, dayOfMonth);
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(taskName, task.taskName) && type == task.type && Objects.equals(description, task.description) && Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, type, description, date, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

    public abstract LocalDate dateRepeat();
    public abstract void printDateRepeat();

}
