package sample;
import javafx.concurrent.Task;
import org.omg.CORBA.SystemException;

import java.util.ArrayList;
import java.util.List;

/**

 */
//la classe principale qui contient l'implementation de l'algorithme d'extraction des points d'articulation
public class Solution {
    public List<Integer> extraction(Graphe g,int n){

                //instance de la file
                File f = new File();
                //liste des sommets marqué lors du parcours
                List<Integer> marque = new ArrayList<Integer>();
                //liste des points d'articulation mis a jour a travers les iterations de l'algorithme
                List<Integer> res = new ArrayList<Integer>();
                int j;//variable intermediaire represante  un sommet
                for(int i=0;i<n;i++){
                    //enfiler le sommet i
                    f.enfiler(i);
                    marque.add(i);
                    //repeter jusqu'a la fin du parcours des successeurs du sommet i
                    //marquer les sommets parcouru
                    while(!f.vide()){
                        j= f.defiler();

                        if(g.getChildren(j)!=null) {
                            for (int k : g.getChildren(j)) {
                                if (!marque.contains(k)) {f.enfiler(k);
                                    marque.add(k);
                                }
                            }
                        }
                    }
                    //supprimer le sommet i
                    g.removeNode(i);
                    //demarquer le sommet i
                    marque.remove((Integer)i);

                    //parcourir les sommets marqué pour detecter si le nombre de composant connexe a augmenter ou non
                    for(int k:marque){
                        List<Integer> marque_tmp = new ArrayList<Integer>();
                        //copier la liste des sommets marqué dans marque_tmp
                        marque_tmp.addAll(marque);
                        f.enfiler(k);
                        int l;
                        //parcours du graphe en supprimant le sommet i
                        while(!f.vide()){
                            l= f.defiler();
                            //enlever un sommet parcouru de la liste des sommets marqué temporaire
                            if(marque_tmp.contains((Integer)l))marque_tmp.remove((Integer) l);

                            if(g.getChildren(l)!=null) {
                                for (int m : g.getChildren(l)) {
                                    //enfiler le successeur si il y on l'a pas deja fait puisque on peut l'attendre a partir du sommet k
                                    if (marque_tmp.contains(m)){ f.enfiler(m);

                                    }
                                }
                            }
                        }
                        //si tout les sommets marqué sont supprimé alors la partie marqué est toujours connexe donc i n'est pas un point d'articulation
                        //sinon les succ de i se sont eparpillé en plusieurs composantes connexe
                        if(marque_tmp.size()>0 && !res.contains((Integer)i)){res.add(i);System.out.println(i); break;}

                    }
                    //supprimé les sommets marqué pour l'iteraton i+1
                    marque.clear();
                    g.i_supp=-1;
                }
        return res;

    }
}
