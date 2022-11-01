package classes;

public class Logger {
    private static Logger instanta = new Logger();

    private Logger() {
    }

    public static Logger getInstance() {
        return instanta;
    }

}
