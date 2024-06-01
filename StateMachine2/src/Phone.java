public class Phone {

    private AlertState state;
    private final static int MIN_VOLUME = 0;
    private final static int MAX_VOLUME = 10;
    private int volume;

    public Phone(){
        state = new SilentState(this);
    }
    public void clickSoundButton(){
        state.click(this);
    }

    public void volumeUp(){
        state.volumeUp(this);
    }
    public void volumeDown(){
        state.volumeDown(this);
    }


    public void setState(AlertState state){
        this.state = state;
    }
    public String receive(){
        return state.alert(this);
    }
    public void incrementVolume(){volume++;}
    public void decrementVolume(){volume--;}
    public int getVolume(){return volume;}

    public void setMinimumVolume(){
        volume = MIN_VOLUME;
    }
    public void setMediumVolume(){
        volume = MAX_VOLUME / 2;
    }
    public void setMaximumVolume(){
        volume = MAX_VOLUME;
    }

}
