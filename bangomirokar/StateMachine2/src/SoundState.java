public class SoundState implements AlertState {


    @Override
    public void click(Phone phone) {
        phone.setState(new VibrationState());
    }

    @Override
    public String alert(Phone phone) {
        return "SOUND, VOLUME = " + phone.getVolume();
    }

    @Override
    public void volumeUp(Phone phone) {
        phone.incrementVolume();
    }

    @Override
    public void volumeDown(Phone phone) {
        if(phone.getVolume() <= 1){
            phone.setState(new SilentState(phone));
        }
        else{
            phone.decrementVolume();
        }
    }
}
