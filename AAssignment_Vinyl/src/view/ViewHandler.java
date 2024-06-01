package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;


import java.io.IOException;

public class ViewHandler {
    ViewModelFactory factory;
    private Stage primaryStage;
    private Scene currentScene;
    //  Controllers

    private VinylListController vinylListController;

    public ViewHandler(ViewModelFactory factory){
        this.factory = factory;
        currentScene = new Scene(new Region());
    }
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        openView("list");
    }
    public void openView(String id){
        Region root = null;
        switch (id){
            case "list" -> root = loadListView("VinylListView.fxml");

        }
        currentScene.setRoot(root);

        primaryStage.setTitle("Zec mi pele");
        primaryStage.setScene(currentScene);
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.show();

    }





    public Region loadListView(String fxmlFile){
        Region root = null;
        if(vinylListController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                vinylListController = loader.getController();
                vinylListController.init(this,factory.getListViewModel(),root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            vinylListController.reset();
        }
        return vinylListController.getRoot();
    }

}
