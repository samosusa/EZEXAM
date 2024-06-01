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
    ListExercisesViewController listExercisesViewController;
    ManageExerciseViewController manageExerciseViewController;
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
            case "list" -> root = loadListView("ListExercisesView.fxml");
            case "manage" -> root = loadManageView("ManageExerciseView.fxml");
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
        if(listExercisesViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                listExercisesViewController = loader.getController();
                listExercisesViewController.init(this,factory.getListExerciseViewModel(),root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            listExercisesViewController.reset();
        }
        return listExercisesViewController.getRoot();
    }
    public Region loadManageView(String fxmlFile){
        Region root = null;
        if(manageExerciseViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                manageExerciseViewController = loader.getController();
                manageExerciseViewController.init(this,factory.getManageExerciseViewModel(),root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            manageExerciseViewController.reset();
        }
        return manageExerciseViewController.getRoot();
    }
}
