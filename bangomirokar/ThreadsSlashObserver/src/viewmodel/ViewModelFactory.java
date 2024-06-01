package viewmodel;

import model.TemperatureModel;

public class ViewModelFactory {
    private TempViewModel tempViewModel;
    private TemperatureModel model;
    public ViewModelFactory(TemperatureModel model){
        this.model = model;
        tempViewModel = new TempViewModel(model);
    }
    public TempViewModel getTempViewModel(){return tempViewModel; }
}
