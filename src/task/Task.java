package task;

import exeption.IncorrectArgumentExeption;
import exeption.IncorrectDateExeption;

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
            String description) throws IncorrectArgumentExeption {
        this.id = counter;
        this.date = LocalDate.now();
        setTaskName(taskName);
        setType(type);
        setDescription(description);
        counter++;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) throws IncorrectArgumentExeption {
        if (taskName != null || !taskName.isEmpty() || !taskName.isBlank()) {
            this.taskName = taskName;
        } else {
            throw new IncorrectArgumentExeption("Название задачи");
        }

    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) throws IncorrectArgumentExeption {
        if (type != null) {
            this.type = type;
        } else {
            throw new IncorrectArgumentExeption("Тип задачи");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentExeption {
        if (description != null || !description.isEmpty() || !description.isBlank()) {
            this.description = description;
        } else {
            throw new IncorrectArgumentExeption("Описание задачи");
        }
    }

    public LocalDate getDate() {
        return date;
    }
    private LocalDate loadDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }
    public void setDate(int year, int month, int dayOfMonth) throws IncorrectDateExeption {
        if (loadDate(year, month, dayOfMonth).isAfter(LocalDate.now())) {
            throw new IncorrectDateExeption("Задача не может быть добавлена в прошлое");
        } else {
            this.date = loadDate(year, month, dayOfMonth);
        }
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
