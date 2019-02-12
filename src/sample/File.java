package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karim on 24/10/2018.
 */
//le model de la file d'attente
public class File {
    List<Integer> f = new ArrayList<Integer>();

    public void enfiler(int i){
        f.add(i);
    }

    public int defiler(){
    int r = f.get(0);
    f.remove(0);
    return r;
    }
    public boolean vide(){
        if(f.size()==0)return true;
        return false;
    }

}
