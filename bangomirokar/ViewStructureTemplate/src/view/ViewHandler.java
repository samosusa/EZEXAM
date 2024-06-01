package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler {

    private  Stage primaryStage;
    private Scene currentScene;
    private ViewModelFactory factory;
    //  Controllers
    private ConvertViewController convertViewController;

    public ViewHandler(ViewModelFactory modelFactory){
        factory = modelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        openView("first");
    }

    public void openView(String id){
        Region root = null;
        switch (id){
            case "first":
                root = loadConvertView("ConvertViewController.fxml");
                break;
        }
        currentScene.setRoot(root);
        String title = "";
        if(root.getUserData() != null) title += root.getUserData();
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();

    }

    public Region loadConvertView(String fxmlFile){
        Region root = null;
        if(convertViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                convertViewController = loader.getController();
                convertViewController.init(root,this,factory.getConvModel());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            convertViewController.reset();
        }
        return convertViewController.getRoot();
    }

}
