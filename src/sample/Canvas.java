package sample;

/**
 * Created by Karim on 27/10/2018.
 */

//cette classe herite de la classe standard Canvas de javafx et on a ajouté l'identifiant du sommet correspndant a ce Canvas
public class Canvas extends javafx.scene.canvas.Canvas {
    int node;
    public Canvas(int w,int h,int node){
        super(w,h);
        this.node = node;
    }
    public int getNodeID(){
        return node;
    }

}
