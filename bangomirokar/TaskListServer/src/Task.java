public class Task {
    private String text;

    @Override
    public String toString() {
        return "Task{ " + getText() + "; estimatedTime=" + estimatedTime + " }";
    }

    private long estimatedTime;

    public Task(String text, long estimatedTime){
        this.text = text;
        this.estimatedTime = estimatedTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
