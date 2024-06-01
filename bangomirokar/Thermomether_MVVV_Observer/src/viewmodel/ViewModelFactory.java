package viewmodel;

import model.TemperatureModel;

public class ViewModelFactory {
    private TemperatureModel model;
    ThermometerViewModel thermometerViewModel;

    public ViewModelFactory(TemperatureModel model){
        this.model = model;
        thermometerViewModel = new ThermometerViewModel(model);
        //  May Add Listeners to model
    }
    public ThermometerViewModel getThermometerViewModel(){
        return thermometerViewModel;
    }
}
