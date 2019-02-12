package sample;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by KARIM on 11/6/2018.
 */
//cette classe affiche les informations d'execution d'un algorithme sur les graphes générés aleatoirement
 class Vbox extends VBox {
    Label nb_som,nb_arc,te,nb_art;
    public Vbox(Donnee donnee){
        super();
        nb_arc = new Label("Nombre de sommets= "+donnee.getNbr_sommets_moy());
        nb_som = new Label("Nombre moyen de successeurs = "+donnee.getNbr_arret_par_sommet_moyen());
        te = new Label("Temps d'execution = ");
        nb_art = new Label("Nombre de pts d'articulation= ");
        getChildren().add(nb_som);
        getChildren().add(nb_arc);
        getChildren().add(te);
        getChildren().add(nb_art);
        //execution de l'algorithme
        donnee.Extraction();
        nb_art.setText(nb_art.getText()+donnee.getNbPointArt());
        te.setText(te.getText()+donnee.getTE()+" ms");
    }
}
