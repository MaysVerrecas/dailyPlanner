import exeption.IncorrectArgumentExeption;
import exeption.IncorrectDateExeption;
import exeption.TaskNotFoundExeption;
import service.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TaskNotFoundExeption, IncorrectArgumentExeption, IncorrectDateExeption {
        new Menu(new Scanner(System.in)).start();
    }
}