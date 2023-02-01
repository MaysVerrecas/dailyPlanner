package task;

import exeption.IncorrectArgumentExeption;

import java.time.LocalDate;

public class WeeklyTask extends Task{

    private final Repeatability repeat;

    public WeeklyTask(String taskName, TaskType type, String description) throws IncorrectArgumentExeption {
        super(taskName, type, description);
        this.repeat = Repeatability.WEEKLY;
    }

    @Override
    public LocalDate dateRepeat() {
        return this.getDate().plusWeeks(1);
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
        if (localDate.isAfter(getDate()) || localDate.isEqual(getDate()) && localDate.getDayOfWeek() == getDate().getDayOfWeek()){
            return true;
        } else {
            return false;
        }
    }
}
