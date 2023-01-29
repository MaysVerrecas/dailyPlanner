package task;

import exeption.IncorrectArgumentExeption;

import java.time.LocalDate;

public class SingleTask extends Task{
    private final Repeatability repeat;

    public SingleTask(String taskName, TaskType type, String description) throws IncorrectArgumentExeption {
        super(taskName, type, description);
        this.repeat = Repeatability.YEARLY;
    }

    @Override
    public LocalDate dateRepeat() {
        return this.getDate();
    }

    @Override
    public void printDateRepeat() {
        System.out.println("Повторяемость задачи : " + repeat.getTitle());
    }
    public String getRepeat() {
        return repeat.getTitle();
    }

    @Override
    public String toString() {
        return super.toString() + "repeat = " + getRepeat();
    }
}
