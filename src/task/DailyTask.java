package task;

import exeption.IncorrectArgumentExeption;

import java.time.LocalDate;

public class DailyTask extends Task{
    private final Repeatability repeat;

    public DailyTask(String taskName, TaskType type, String description) throws IncorrectArgumentExeption {
        super(taskName, type, description);
        this.repeat = Repeatability.DAILY;
    }

    @Override
    public LocalDate dateRepeat() {
        return this.getDate().plusDays(1);
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
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDate()) || localDate.isEqual(getDate());
    }
}
