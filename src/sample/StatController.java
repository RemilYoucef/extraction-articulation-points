package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

/**
 * Created by KARIM on 11/6/2018.
 */
public class StatController {
    @FXML
    private TextField nb_sommet;

    @FXML
    private TextField nb_suc;

    @FXML
    private Button ajouter;

    @FXML
    private VBox VBOX;

    @FXML
    private AnchorPane lineChart;
    //creation des axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    //creation du chart
    final LineChart<Number,Number> chart =
            new LineChart<Number,Number>(xAxis,yAxis);

    public void initialize(){
    lineChart.getChildren().add(chart);
    }

    //executer l'algorithme sur le graphe aleatoire et afficher le temps d'execution sur le chart
    @FXML
    void extraction(MouseEvent event) {
            try{
                //generation du graphe et execution de l'algorithme
                Donnee d = new Donnee(Integer.parseInt(nb_sommet.getText()),Integer.parseInt(nb_suc.getText()));
                //ajouter les information verbose au VBOX
                //Vbox est une classe qui herite de VBox standard de javafx en ajoutant les informations de l'execution de l'algorihtme
                //sur le graphe géneré
                Vbox h = new Vbox(d);
                h.styleProperty().setValue("-fx-border-color:black;");
                VBOX.getChildren().add(0,h);

                //afichage du temps d'execution sur le chart
                XYChart.Series series = new XYChart.Series();
                series.getData().add(new XYChart.Data(d.getNbr_sommets_moy(), d.getTE()));
                chart.getData().add(series);

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }

}
