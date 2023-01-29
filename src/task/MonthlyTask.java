package task;

import exeption.IncorrectArgumentExeption;

import java.time.LocalDate;

public class MonthlyTask extends Task{
    private final Repeatability repeat;
    public MonthlyTask(String taskName, TaskType type, String description) throws IncorrectArgumentExeption {
        super(taskName, type, description);
        this.repeat = Repeatability.MONTHLY;
    }

    @Override
    public LocalDate dateRepeat() {
        return this.getDate().plusMonths(1);
    }

    @Override
    public void printDateRepeat() {
        System.out.println("Повторяемость задачи : " + repeat.getTitle() +
                " , следующее повторение - " + dateRepeat().toString());
    }
    public String getRepeat() {
        return repeat.getTitle();
    }

    @Override
    public String toString() {
        return super.toString() + "repeat = " + getRepeat();
    }
}
