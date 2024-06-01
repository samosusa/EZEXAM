package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Vinyl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VinylListViewModel implements PropertyChangeListener {

    private ObservableList<SimpleVinylViewModel> list;
    private ObjectProperty<SimpleVinylViewModel> selection; //  May handle differently
    private StringProperty errorLabel;
    private StringProperty nameProperty;
    private Model model;


    public VinylListViewModel(Model model){
        this.model = model;

        errorLabel = new SimpleStringProperty();
        list = FXCollections.observableArrayList();
        selection = new SimpleObjectProperty<>();
        nameProperty = new SimpleStringProperty();

        loadFromModel();

        model.addListener(this);
    }

    public void clear(){errorLabel.set("");}
    private void loadFromModel(){   //  Loads all dummy data, rewrites it
        list.clear();
        for (int i = 0; i < model.getList().getAlbum().size(); i++) {
            list.add(new SimpleVinylViewModel(model.getList().getAlbum().get(i)));
        }
    }
    public ObservableList<SimpleVinylViewModel> getAll() {return list;}
    public void setSelected(SimpleVinylViewModel vinyl){selection.set(vinyl);}
    public void deselect(){selection = null;}

    public boolean onBorrow(){
        if(nameProperty().get() != null){
            model.borrowVinyl(selection.get().titleProperty().get(), nameProperty().get());
            return true;
        }
        else{
            return false;
        }


    }
    public boolean onReserve(){
        if(nameProperty().get() != null){
            model.reserveVinyl(selection.get().titleProperty().get(), nameProperty().get());
            return true;
        }
        else{
            return false;
        }
    }
    public boolean onReturn(){
        if(nameProperty().get() != null){
            model.returnVinyl(selection.get().titleProperty().get(), nameProperty().get());
            return true;
        }
        else{
            return false;
        }
    }


    public StringProperty nameProperty() {
        return nameProperty;
    }

    public void onAdd(){
        // need to make changes to the model
    }
    public void onRemove(){
        //  need to make changes to the model
    }

    private void setVinylState(String title, Vinyl vinyl){

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).titleProperty().get().equals(title)){
                list.get(i).setStateProperty(vinyl.getStatus());
                list.get(i).setBorrowerProperty(vinyl.getBorrower());
                list.get(i).setReserverProperty(vinyl.getReserver());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Platform.runLater(() -> {
            setVinylState(evt.getOldValue().toString(), ((Vinyl) evt.getNewValue()));
        });
    }
}
