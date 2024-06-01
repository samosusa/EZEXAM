package viewmodel;

public class ViewState {
    private String number;
    private boolean remove;

    //  Violation variables
    private String topic;
    private boolean isCompleted;

    public String getNumber(){return number;}

    public void setNumber(String number){this.number=number;}
    public void removeNumber(){number=null;}
    public boolean isRemove(){return remove;}
    public void setRemove(boolean remove){this.remove = remove;}

    //  Violation of diagram
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {

        isCompleted = completed;
    }



}
