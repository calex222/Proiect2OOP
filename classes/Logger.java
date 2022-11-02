package classes;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instanta = new Logger();

    private Logger() {
    }

    // used to get class instance
    public static Logger getInstance() {
        return instanta;
    }

    public void init() {
    }

    // writes log to file
    public void writeLog(String s) {
        try {
            FileWriter logFile = new FileWriter("logfile.txt", true);
            logFile.write(s);
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
