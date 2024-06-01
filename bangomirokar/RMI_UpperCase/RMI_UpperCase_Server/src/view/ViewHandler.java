package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler {
    ViewModelFactory factory;
    private Stage primaryStage;
    private Scene currentScene;
    //  Controllers

    private LogViewController logViewController;

    public ViewHandler(ViewModelFactory factory){
        this.factory = factory;
        currentScene = new Scene(new Region());
    }
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        openView("logs");
    }
    public void openView(String id){
        Region root = null;
        switch (id){
            case "logs" -> root = loadLogsView("LogView.fxml");

        }
        currentScene.setRoot(root);

        primaryStage.setTitle("Zec mi pele");
        primaryStage.setScene(currentScene);
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.show();

    }






public Region loadLogsView(String fxmlFile){
    Region root = null;
    if(logViewController == null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logViewController = loader.getController();
        logViewController.init(this,factory.getLogViewModel(),root);
    }
    else{
        logViewController.reset();
    }
    return logViewController.getRoot();
}
}
