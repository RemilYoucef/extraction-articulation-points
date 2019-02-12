package sample;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.Random;

/**
 * Created by KARIM on 11/6/2018.
 */

// Cette classe contient toutes les informations necessaires pour l'execution de l'algorithme d'extraction sur un jeu de donnée
public class Donnee {
    private int nbr_sommets_moy;
    private int nbr_arret_par_sommet_moyen;
    private Long TE;
    private int nbPointArt;

    public Donnee(int nbr_sommets_moy, int nbr_arret_par_sommet_moyen) {
        this.nbr_sommets_moy = nbr_sommets_moy;
        //si le nombre d'arc est supperieur au nombre de sommet on le limite par le nombre de sommets -1
        if(nbr_arret_par_sommet_moyen<nbr_sommets_moy){

        this.nbr_arret_par_sommet_moyen = nbr_arret_par_sommet_moyen;


        }else{
            this.nbr_arret_par_sommet_moyen = this.nbr_sommets_moy-1;
        }

    }

    public int getNbr_sommets_moy() {
        return nbr_sommets_moy;
    }

    public int getNbr_arret_par_sommet_moyen() {
        return nbr_arret_par_sommet_moyen;
    }
    public void setResultat(long te,int nb){
        this.TE = te;
        this.nbPointArt = nb;

    }
    //construction du graphe et execution de l'algorithme
    public void Extraction(){
        Random r = new Random();
        Solution s = new Solution();
        List<Integer> res;
            Graphe g = new Graphe(this.getNbr_sommets_moy());
            //remplissage du graphe
            for(int sommet=0;sommet<this.getNbr_sommets_moy();sommet++){
                for(int arc=0;arc<this.getNbr_arret_par_sommet_moyen()/2 +1;arc++){
                    g.setArc(sommet,r.nextInt(this.getNbr_sommets_moy()));
                }
            }
            long t0,t1;

            //calcul de temps d'execution
            t0 = System.currentTimeMillis();
            //execution de l'algorithme et res contient la liste des points d'articulation
            res = s.extraction(g,this.getNbr_sommets_moy());
            t1 = System.currentTimeMillis();
            System.out.println("temps d'exec = "+(t1-t0)/1000 +" s");
            setResultat((t1-t0),res.size());


    }

    public Long getTE() {
        return TE;
    }

    public int getNbPointArt() {
        return nbPointArt;
    }
}
