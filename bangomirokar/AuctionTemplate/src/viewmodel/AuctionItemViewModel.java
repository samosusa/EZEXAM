package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.AuctionModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuctionItemViewModel implements PropertyChangeListener {

    AuctionModel model;

    private StringProperty item;
    private StringProperty time;
    private IntegerProperty bid;
    private StringProperty bidder;
    private IntegerProperty currentBid;
    private StringProperty currentBidder;
    private StringProperty error;
    private BooleanProperty end;
    private StringProperty currentBidTitle;
    public AuctionItemViewModel(AuctionModel model){
        this.model = model;

        item = new SimpleStringProperty(model.getItem());
        time = new SimpleStringProperty();
        bid = new SimpleIntegerProperty();
        bidder = new SimpleStringProperty();
        currentBid = new SimpleIntegerProperty(model.getCurrentBid());
        currentBidder = new SimpleStringProperty(model.getCurrentBidder());
        error = new SimpleStringProperty();
        end = new SimpleBooleanProperty();
        currentBidTitle = new SimpleStringProperty();
        currentBidTitle.set("Current Bid:");

        model.addListener(null,this);
    }

    public void clear(){
        item.set("");
        time.set("");
        bid.set(0);
        bidder.set("");
        currentBid.set(0);
        currentBidder.set("");
        error.set("");
        currentBidTitle.set("");
    }
    public void bid(){
        if(!model.placeBid(bid.get(),bidder.get())){
            error.set("Bet was not placed");
        }
        else{
            currentBidder.set("STRING");
            currentBid.set(bid.get());
        }
    }

    public AuctionModel getModel() {
        return model;
    }

    public String getItem() {
        return item.get();
    }

    public StringProperty itemProperty() {
        return item;
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public int getBid() {
        return bid.get();
    }

    public IntegerProperty bidProperty() {
        return bid;
    }

    public String getBidder() {
        return bidder.get();
    }

    public StringProperty bidderProperty() {
        return bidder;
    }

    public int getCurrentBid() {
        return currentBid.get();
    }

    public IntegerProperty currentBidProperty() {
        return currentBid;
    }

    public String getCurrentBidder() {
        return currentBidder.get();
    }

    public StringProperty currentBidderProperty() {
        return currentBidder;
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public boolean isEnd() {
        return end.get();
    }

    public BooleanProperty endProperty() {
        return end;
    }

    public String getCurrentBidTitle() {
        return currentBidTitle.get();
    }

    public StringProperty currentBidTitleProperty() {
        return currentBidTitle;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {   // !!!!!!!!

            switch (evt.getPropertyName()){
                case "time":
                    time.set((String) evt.getNewValue());
                    break;
                case "bid":
                    currentBid.set(model.getCurrentBid());
                    currentBidder.set(model.getCurrentBidder());
                    break;
                case "end":
                    error.set("Auction ended, winner is: " + currentBidder);
                    break;
            }
        });

    }
}
