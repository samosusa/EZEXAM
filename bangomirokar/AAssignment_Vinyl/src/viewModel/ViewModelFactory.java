package viewModel;

import model.Model;

public class ViewModelFactory {

    private Model model;
    private VinylListViewModel listViewModel;
    public ViewModelFactory(Model model){
        this.model = model;
        listViewModel = new VinylListViewModel(model);
    }

    public VinylListViewModel getListViewModel() {
        return listViewModel;
    }
}
