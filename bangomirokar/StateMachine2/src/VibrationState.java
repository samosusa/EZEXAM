public class VibrationState implements AlertState{


    @Override
    public void click(Phone phone) {
        phone.setState(new SilentState(phone));
    }

    @Override
    public String alert(Phone phone) {
        return "VIBRATION, VOLUME = " + phone.getVolume();
    }

    @Override
    public void volumeUp(Phone phone) {
        phone.incrementVolume();
    }

    @Override
    public void volumeDown(Phone phone) {
        phone.decrementVolume();
    }
}
