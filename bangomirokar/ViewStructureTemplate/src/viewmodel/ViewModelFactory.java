package viewmodel;

import model.*;

public class ViewModelFactory {
    private Model model;

    private ConvertViewModel convModel;
    public ViewModelFactory(Model model){
        this.model = model;
        this.convModel = new ConvertViewModel(model);

    }

    public ConvertViewModel getConvModel(){
        return convModel;
    }


}
