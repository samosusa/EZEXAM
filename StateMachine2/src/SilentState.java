public class SilentState implements AlertState{
    public SilentState(Phone phone){
        phone.setMinimumVolume();
    }
    @Override
    public void click(Phone phone) {
        phone.setMediumVolume();
        phone.setState(new SoundState());
    }

    @Override
    public String alert(Phone phone) {
        return "SILENCE, VOLUME = " + phone.getVolume();
    }

    @Override
    public void volumeUp(Phone phone) {
        phone.incrementVolume();
        phone.setState(new SoundState());
    }

    @Override
    public void volumeDown(Phone phone) {
        // Nothing
    }
}
