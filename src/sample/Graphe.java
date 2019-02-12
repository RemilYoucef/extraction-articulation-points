package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karim on 24/10/2018.
 */

//le model de graphe
public class Graphe {
    private int n;
    private int M[][];

    public int i_supp=-1;//i_supp contient le sommet supprimé acuellement, puisque on supprime qu'un sommet a la fois
    //constructeur de creation d'un graphe d'ordre n
    public Graphe(int n) {
        this.n = n;
        M = new int[n][n];
        for(int i=0;i<n;i++)for(int j=0;j<n;j++){M[i][j]=0;}
    }
    //creation d'un arc entre le sommet i et le sommet j
    public void setArc(int i,int j){
        M[i][j]=1;
        M[j][i]=1;
    }
    //supprimer le sommet i
    public void removeNode(int i){
        i_supp=i;
    }
    //la liste de successeurs du sommet i

    public List<Integer> getChildren(int i){
        List<Integer> child = new ArrayList<Integer>();
        if(i==i_supp) return null;
        for(int j=0;j<n;j++){
            if(M[i][j]==1 && j!=i_supp)child.add(j);
        }
        if(child.size()==0) return null;
        return child;
    }
    //afficher la matrice M
    public void afficherM(){
        for(int i=0;i<n;i++){for(int j=0;j<n;j++){System.out.print(M[i][j]+"  ");}System.out.print("\n");};
    }

}

