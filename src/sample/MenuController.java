package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by KARIM on 11/6/2018.
 */
public class MenuController {
    @FXML
    private Button man;

    @FXML
    private Button stat;
    public void initialize(){

    }
    //ouvre le stage de la generation manuelle d'un graphe
    @FXML
    void manClicked(MouseEvent event) {
    try{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("man.fxml"));
        primaryStage.setTitle("TP2 BOUTIBA KARIM & REMIL YOUCEF");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }catch (Exception ex){
        System.out.println(ex.getMessage());
    }
    }


    //ouvre le stage de l'ennoncé
    @FXML
    void ennClicked(MouseEvent event) {
        try{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ennonce.fxml"));
            primaryStage.setTitle("TP2 BOUTIBA KARIM & REMIL YOUCEF");
            primaryStage.setScene(new Scene(root, 1000, 650));
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    //ouvre le stage de la generation automatique
    @FXML
    void statClicked(MouseEvent event) {
        try{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
            primaryStage.setTitle("TP2 BOUTIBA KARIM & REMIL YOUCEF");
            primaryStage.setScene(new Scene(root, 1000, 650));
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    }
