package service;

import exeption.IncorrectArgumentExeption;
import exeption.IncorrectDateExeption;
import exeption.TaskNotFoundExeption;
import task.*;

import java.time.LocalDate;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    private final ServiceTask taskService = new ServiceTask();

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMenu() {
        System.out.println("Выберите значение, введите, нажмите Enter");
        System.out.println("1: Создать задачу");
        System.out.println("2: Показать все задачи");
        System.out.println("3: Показать все задачи по дате");
        System.out.println("4: Смотреть архив удаленных задач");
        System.out.println("5: Сгруппировать по датам");
        System.out.println("6: Завершить программу");
    }

    public void printMessageMenu() {
        System.out.println("Выберите значение, введите, нажмите Enter");
        System.out.println("1: Удалить");
        System.out.println("2: Изменить");
        System.out.println("3: Обратно в меню");
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private LocalDate getDateOf() throws IncorrectArgumentExeption {
        int year = 0;
        int mounth = 0;
        int day = 0;
        System.out.println("Введите год");
        String dataYear = this.scanner.nextLine();
        if (isDigit(dataYear)) {
            year = Integer.parseInt(dataYear);
            if (year < 1000 || year > 3000) {
                throw new IncorrectArgumentExeption("Введен не ккоректный год.");
            }
        } else {
            throw new IncorrectArgumentExeption("Введен не корректный год.");
        }
        System.out.println("Введите месяц");
        String dataMonth = this.scanner.nextLine();
        if (isDigit(dataMonth)) {
            mounth = Integer.parseInt(dataMonth);
            if (mounth < 1 || mounth > 13) {
                throw new IncorrectArgumentExeption("Такого месяца не существует.");
            }
        } else {
            throw new IncorrectArgumentExeption("Месяц введен некорректно");
        }
        System.out.println("Введите день");
        String dataDay = this.scanner.nextLine();
        if (isDigit(dataDay)) {
            day = Integer.parseInt(dataDay);
            if (day < 1 || day > 32) {
                throw new IncorrectArgumentExeption("Введен не корректный день.");
            }
        } else {
            throw new IncorrectArgumentExeption("Введен не корректный день.");
        }
        return LocalDate.of(year,mounth,day);
    }

    private Task printCreateNewTask() throws IncorrectArgumentExeption, IncorrectArgumentExeption, IncorrectDateExeption {
        System.out.print("Введите имя задачи: ");
        String name = null;
        name = this.scanner.nextLine();
        if (name.isBlank() || isDigit(name) || name.isEmpty()) {
            throw new IncorrectArgumentExeption("Некорректные данные");
        }
        System.out.println("Выберите тип задачи" + '\n' + "1: личная" + '\n' + "2: рабочая");
        String answer = this.scanner.nextLine();
        int typeIndex = 0;
        if (isDigit(answer)) {
            typeIndex = Integer.parseInt(answer);
        } else {
            throw new IncorrectArgumentExeption("Некорректные данные");
        }
        if (typeIndex > 2 || typeIndex < 1) {
            throw new IncorrectArgumentExeption("Некорректные данные");
        }
        TaskType type = TaskType.WORK;
        if (typeIndex == 1) {
            type = TaskType.PERSONAL;
        }
        System.out.print("Введите описание задачи: ");
        String description = null;
        description =  this.scanner.nextLine();
        System.out.println("Выберите повторяемость задачи" + '\n' + "1: ежедневная" + '\n' + "2: еженедельная" + '\n' +
                "3: ежемесячная" + '\n' + "4: ежегодная" + '\n' + "5: одноразовая");
        String answer1 = this.scanner.nextLine();
        int repeat = 0;
        if (isDigit(answer1)) {
            repeat = Integer.parseInt(answer1);
            if (repeat < 1 || repeat > 5) {
                throw new IncorrectArgumentExeption("Выберите повторяемость задачи из предложенных");
            }
        } else {
            throw new IncorrectArgumentExeption("Некорректные данные");
        }
        Task task = null;
        switch (repeat) {
            case 1:
                task = new DailyTask(name, type, description);
                break;
            case 2:
                task = new WeeklyTask(name, type, description);
                break;
            case 3:
                task = new MonthlyTask(name, type, description);
                break;
            case 4:
                task = new YearlyTask(name, type, description);
                break;
            case 5:
                task = new SingleTask(name, type, description);
                break;
        }
        System.out.print("На какую дату записать задачу?");
        task.setDate(getDateOf());
        System.out.println("Задача сохранена");
        return task;
    }

    public void start() throws IncorrectArgumentExeption, TaskNotFoundExeption, IncorrectDateExeption {
        if (this.scanner != null) {
            int key;
            do {
                printMenu();
                String str = this.scanner.nextLine();
                if (!isDigit(str)) {
                    throw new IncorrectArgumentExeption("ЭТО НЕ ЧИСЛО!!!111!!!");
                }
                else {
                    key = Integer.parseInt(str);
                }
                switch (key) {
                    case 1:
                        taskService.add(printCreateNewTask());
                        break;
                    case 2:
                        int keyMessage;
                        taskService.printAllTasks();
                        do {
                            printMessageMenu();
                            String str1 = this.scanner.nextLine();
                            if (!isDigit(str1)) {
                                throw new IncorrectArgumentExeption("ЭТО НЕ ЧИСЛО!!!111!!!");
                            } else {
                                keyMessage = Integer.parseInt(str1);
                            }
                            switch (keyMessage) {
                                case 1:
                                    int idD;
                                    System.out.println("Введите id задачи для удаления");
                                    String idDelete = this.scanner.nextLine();
                                    if (!isDigit(idDelete)) {
                                        throw new IncorrectArgumentExeption("ЭТО НЕ ЧИСЛО!!!111!!!");
                                    } else {
                                        idD = Integer.parseInt(idDelete);
                                    }
                                    taskService.remove(idD);
                                    System.out.println("Задача удалена.");
                                    break;
                                case 2:
                                    int idCh;
                                    System.out.println("Введите id задачи для изменения");
                                    String idChange = this.scanner.nextLine();
                                    if (!isDigit(idChange)) {
                                        throw new IncorrectArgumentExeption("ЭТО НЕ ЧИСЛО!!!111!!!");
                                    } else {
                                        idCh = Integer.parseInt(idChange);
                                    }
                                    System.out.println("Введите новое название задачи");
                                    String newTaskName = this.scanner.nextLine();
                                    System.out.println("Введите новое описание задачи");
                                    String newTaskDescription = this.scanner.nextLine();
                                    taskService.updateTask(idCh, newTaskName, newTaskDescription);
                                    break;

                                case 3:
                                    System.out.println("Возвращаемся!!!");
                                    break;
                                default:
                                    System.out.println("Я что, для дауна варианты прописываю?" +
                                            " Нужно выбрать из вариантов!");
                            }
                            }
                        while (keyMessage != 3);
                        break;
                    case 3:
                        System.out.println(taskService.getAllByDate(getDateOf()));
                        break;
                    case 4:
                       taskService.printRemoveTasks();
                       break;
                    case 5:
                        System.out.println(taskService.getGroupedByDate());
                        break;
                    case 6:
                        System.out.println("BYE BYE");
                        break;
                    default:
                        System.out.println("Введите правильный номер меню\n");
                }
            }
            while (key != 6);
        }
    }

}