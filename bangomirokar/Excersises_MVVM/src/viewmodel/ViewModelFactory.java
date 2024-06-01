package viewmodel;


import model.Model;

public class ViewModelFactory {

    private ManageExerciseViewModel manageExerciseViewModel;
    private ListExerciseViewModel listExerciseViewModel;
    private ViewState viewState;
    public ViewModelFactory(Model model){
        viewState = new ViewState();
        manageExerciseViewModel = new ManageExerciseViewModel(model,viewState);
        listExerciseViewModel = new ListExerciseViewModel(model,viewState);
    }

    public ManageExerciseViewModel getManageExerciseViewModel() {
        return manageExerciseViewModel;
    }

    public ListExerciseViewModel getListExerciseViewModel() {
        return listExerciseViewModel;
    }
    //  Add getters

}
