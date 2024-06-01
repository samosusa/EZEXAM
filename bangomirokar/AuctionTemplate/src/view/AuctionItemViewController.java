package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import viewmodel.AuctionItemViewModel;



public class AuctionItemViewController {

    @FXML private Label itemLabel;
    @FXML private Label timeLabel;
    @FXML private Label currentBidTitle;
    @FXML private Label currentBidLabel;
    @FXML private Label currentBidderLabel;
    @FXML private TextField bidField;
    @FXML private Label errorLabel;
    private Region root;
    private AuctionItemViewModel viewModel;
    private ViewHandler view;

    public void init(ViewHandler view, AuctionItemViewModel viewModel, Region root){
        this.root = root;
        this.view = view;
        this.viewModel = viewModel;

        itemLabel.textProperty().bind(viewModel.itemProperty());
        timeLabel.textProperty().bind(viewModel.timeProperty());

        currentBidTitle.textProperty().bind(viewModel.currentBidTitleProperty());
        currentBidderLabel.textProperty().bind(viewModel.currentBidderProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());


        //  Current bid is not StringProperty therefor it is a lil bit different
        Bindings.bindBidirectional(currentBidLabel.textProperty(), viewModel.currentBidProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                if (number == null || number.intValue() == 0){
                    return "";
                }
                else{
                    return number.toString();
                }
            }

            @Override
            public Number fromString(String s) {
                try{
                    return Integer.parseInt(s);
                }
                catch(Exception e) {return 0;}
            }
        });


        Bindings.bindBidirectional(bidField.textProperty(), viewModel.bidProperty(), new StringConverter<>() {
            @Override
            public String toString(Number number) {
                if (number == null || number.intValue() == 0){
                    return "";
                }
                else{
                    return number.toString();
                }
            }

            @Override
            public Number fromString(String s) {
                try{
                    return Integer.parseInt(s);
                }
                catch(Exception e) {return 0;}
            }
        });

    }
    public Region getRoot(){return root;}
    public void reset(){
        //  RESET LOGIC
        viewModel.clear();
    }

    @FXML private void bidOnAction(){
        //  CALL VIEW-MODEL OR SMT
        viewModel.bid();
    }
}
