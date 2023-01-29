package exeption;

public class IncorrectArgumentExeption extends Exception {
    private final String argument;

    public IncorrectArgumentExeption(String argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return "Параметр : " + argument + " не корректный.";
    }
}
