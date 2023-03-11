package service;

import exeption.IncorrectArgumentExeption;
import exeption.TaskNotFoundExeption;
import task.Task;

import java.time.LocalDate;
import java.util.*;

public class ServiceTask {
    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Set<Task> removeTasks = new HashSet<>();

    public void add(Task task) { //добавить
        taskMap.put(task.getId(), task);
    }

    public void remove(int id) throws TaskNotFoundExeption { //удалить по id
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundExeption ("Задача не найдена");
        }
        removeTasks.add(taskMap.get(id));
        taskMap.get(id).setDelete(true);
    }

    public List<Task> getAllByDate (LocalDate localDate) throws TaskNotFoundExeption { //список по дате
        List<Task> taskList = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.appearsIn(localDate) && !task.isDelete()){
                taskList.add(task);
            }
        }
        if (taskList.isEmpty()) {
            throw new TaskNotFoundExeption("Задачи по дате не найдены!");
        }
        return taskList;
    }

    public void printRemoveTasks() throws TaskNotFoundExeption {
        System.out.println("\nАрхив удаленных задач :");
        if (!removeTasks.isEmpty()) {
            for (Task task : removeTasks) {
                System.out.println(task.toString());
            }
        } else {
            throw new TaskNotFoundExeption("Архив удаленных задач - пуст.");
        }
    }

    private Task findById(int id) throws TaskNotFoundExeption {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundExeption("Задача не найдена");
        }
        return taskMap.get(id);
    }

    public void updateTask(int id, String taskName, String description) throws TaskNotFoundExeption, IncorrectArgumentExeption {
        Task task = findById(id);
        task.setTaskName(taskName);
        task.setDescription(description);
    }

    public Map<LocalDate, ArrayList<Task>> getGroupedByDate() throws TaskNotFoundExeption {
        Map<LocalDate, ArrayList<Task>> localDateArrayListMap = new HashMap<>();
        if (!taskMap.isEmpty()) {
            for (Task value : taskMap.values()) {
                localDateArrayListMap.computeIfAbsent(value.getDate(), k -> new ArrayList<>()).
                        add(value);
            }
        } else {
            throw new TaskNotFoundExeption("Задачи не найдены!");
        }
        return localDateArrayListMap;
    }

    public void printAllTasks() throws TaskNotFoundExeption {
        int count = 0;
        if (!taskMap.isEmpty()) {
            for (Task task : taskMap.values()) {
                if (!task.isDelete()) {
                    System.out.println(task);
                    count++;
                }
            }
            if (count == 0) {
                throw new TaskNotFoundExeption("Список активных задач - пуст.");
            }
        } else {
            throw new TaskNotFoundExeption("Нет созданных задач!");
        }
    }


}
