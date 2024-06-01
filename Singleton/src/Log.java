import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Log {
    public ArrayList<LogLine> getLogs() {
        return logs;
    }

    private ArrayList<LogLine> logs;


    //  Singleton
    private static Log instance;
    private static Object lock = new Object();

    private Log(){
        logs = new ArrayList<>();
    }   //  Private Constructor

    public synchronized static Log getInstance(){
        if(instance == null)    //  if it is not created, create it
        {
            synchronized (lock){    //  Thread safe
                if(instance == null){
                    instance = new Log();   //  creating instance here
                }
            }
        }
        return instance;
    }
    public synchronized void addLog(String text){
        LogLine newLog = new LogLine(text);
        addToFile(newLog);
        logs.add(newLog);
    }
    private synchronized void addToFile(LogLine log) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;
        try {
            String filename = "Log-" + log.getDateTime().getSortableDate() + ".txt";
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public synchronized String toString() {
        String s = "";
        for (int i = 0; i < logs.size(); i++) {
            s+=logs.get(i).toString() + "\n";
        }
        return s;
    }
}
