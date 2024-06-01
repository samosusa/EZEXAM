package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Vinyl;

public class SimpleVinylViewModel {
    private StringProperty titleProperty;
    private StringProperty artistProperty;
    private StringProperty yearProperty;
    private StringProperty stateProperty;
    private StringProperty borrowerProperty;
    private StringProperty reserverProperty;

    public SimpleVinylViewModel(Vinyl vinyl){
        titleProperty = new SimpleStringProperty(vinyl.getTitle());
        artistProperty = new SimpleStringProperty(vinyl.getArtist());
        yearProperty = new SimpleStringProperty(vinyl.getYear() + "");
        stateProperty = new SimpleStringProperty(vinyl.getStatus());
        borrowerProperty = new SimpleStringProperty(vinyl.getBorrower());
        reserverProperty = new SimpleStringProperty(vinyl.getReserver());
    }

    public StringProperty titleProperty() {
        return titleProperty;
    }

    public void setTitleProperty(String titleProperty) {
        this.titleProperty.set(titleProperty);
    }


    public StringProperty artistProperty() {
        return artistProperty;
    }

    public void setArtistProperty(String artistProperty) {
        this.artistProperty.set(artistProperty);
    }

    public StringProperty yearProperty() {
        return yearProperty;
    }

    public void setYearProperty(String yearProperty) {
        this.yearProperty.set(yearProperty);
    }

    public StringProperty stateProperty() {
        return stateProperty;
    }

    public void setStateProperty(String stateProperty) {
        this.stateProperty.set(stateProperty);
    }


    public StringProperty borrowerProperty() {
        return borrowerProperty;
    }

    public void setBorrowerProperty(String borrower){this.borrowerProperty.set(borrower);}
    public void setReserverProperty(String reserver){this.reserverProperty.set(reserver);}
    public StringProperty reserverProperty() {
        return reserverProperty;
    }
}
