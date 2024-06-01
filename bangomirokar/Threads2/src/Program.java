public class Program implements Runnable{

    private String program;
    private long maxFrequency;
    private String action;
    private int count;
    private static long RUNTIME = 10000;

    public Program(String program, String action, int count){
        this.program = program;
        this.action = action;
        this.count = count;
    }
    private void normalOperation(){
        System.out.println(program + " wants to update " + action);
    }
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {

            normalOperation();
            try {
                Thread.sleep(RUNTIME/count);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
