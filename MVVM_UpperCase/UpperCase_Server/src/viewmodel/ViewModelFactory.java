package viewmodel;

import model.Model;

public class ViewModelFactory {

    private LogViewModel logViewModel;
    private Model model;
    public ViewModelFactory(Model model){
        logViewModel = new LogViewModel(model);
    }

    public LogViewModel getLogViewModel() {
        return logViewModel;
    }
}
