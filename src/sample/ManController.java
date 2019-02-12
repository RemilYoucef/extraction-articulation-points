package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
//cette classe permet de dessiner les graphes
public class ManController {
@FXML private AnchorPane plan;
@FXML private Button b;
@FXML private TextField nombre_sommets;
@FXML private Button nouveau_graphe;
    private int N = 5;

    private Canvas layer; // un canvas represante le dessin d'un sommet
    private int i_gen=0;//compteur de sommet et identifiant du dernier sommet crée a l'instant t
    private Canvas layer_previous;//pour garder trace du dernier sommet cliqué dessus pour le relier avec l'actuel
    private boolean next=false;//pour distinger le cas de creation d'un sommet isolé par rapport au precedant ou relié avec a travers un arc
    private List<Canvas> canvas_list=null ;//liste des canvas pour les garder a la fin pour les colorier
    private GraphicsContext gc1;//outils de dessin
    private Color bleu,rouge;//des couleurs :3
    private Solution s;//la classe qui contient la soltion

    private Graphe g;//instance du model d'un graphe
    private List<Integer> res;//liste des points d'artuculation
    @FXML
    public void initialize(){

    s = new Solution();
    g = new Graphe(N);
    bleu = Color.web("#3232ff");rouge = Color.web("#990000");
    nombre_sommets.setText("5");
    canvas_list = new ArrayList<Canvas>();
        // cette fonction est appelé lors du clique sur extraction des points d'articulation
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(canvas_list!= null) {
                    //execution de l'algorithme
                    res = s.extraction(g, N);

                    //colorier les points d'articulation(redessiner avec une autre couleur)
                    for(int i:res){
                        for(Canvas c:canvas_list){
                            if(c.getNodeID()==i){

                                gc1 = c.getGraphicsContext2D();
                                gc1.setFill(rouge);
                                gc1.setStroke(rouge);
                                gc1.setLineWidth(3);
                                gc1.strokeOval(2, 2, 20, 20);
                                gc1.fillOval(2,2,20,20);
                                gc1.setTextAlign(TextAlignment.CENTER);
                                gc1.setTextBaseline(VPos.CENTER);
                                gc1.setFill(Color.WHITE);
                                gc1.fillText(
                                        String.valueOf(c.getNodeID()),
                                        Math.round(c.getWidth() / 2 - 1),
                                        Math.round(c.getHeight() / 2 - 2)
                                );

                            }
                        }
                    }
                }

            }
        };

            //cette fonction est appelé lors du clique sur nouveau graphe
            EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    try {
                        //introduire le nouveau nombre de sommets
                        N = Integer.valueOf(nombre_sommets.getText());
                        g = new Graphe(N);
                        //remettre a 0 le compteur de sommets crée
                        i_gen =0;
                        //supprimer les canvas sur le plan
                        plan.getChildren().clear();
                        next = false;
                        //vider la liste de Canvas
                        canvas_list = new ArrayList<Canvas>();
                    }catch(Exception ex){

                    }

                    }

                };

        //Registering the event filter
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        nouveau_graphe.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler2);

    }


public void onClick(MouseEvent e){
    //System.out.println("x="+e.getSceneX() + " y="+e.getSceneY());
   if(e.getTarget().getClass().toString().equals("class javafx.scene.layout.AnchorPane") && i_gen<N) {

       layer = new Canvas(26, 26,i_gen++);
       canvas_list.add(layer);

       layer.setLayoutY(e.getY() - 10);
       layer.setLayoutX(e.getX() - 10);

       gc1 = layer.getGraphicsContext2D();
       gc1.setFill(bleu);
       gc1.setStroke(bleu);
       gc1.setLineWidth(3);
       gc1.strokeOval(2, 2, 20, 20);
        gc1.fillOval(2,2,20,20);
       gc1.setTextAlign(TextAlignment.CENTER);
       gc1.setTextBaseline(VPos.CENTER);
       gc1.setFill(Color.WHITE);
       gc1.fillText(
               String.valueOf(layer.getNodeID()),
               Math.round(layer.getWidth() / 2 - 1),
               Math.round(layer.getHeight() / 2 - 2)
       );
       plan.getChildren().add(layer);
    if(next) {
        Line line = new Line(layer.getLayoutX() + 10, layer.getLayoutY() + 10, layer_previous.getLayoutX() + 10, layer_previous.getLayoutY() + 10);
        plan.getChildren().add(line);
        line.setFill(bleu);
        line.toBack();
        layer.toFront();
        layer_previous.toFront();
        g.setArc(layer.getNodeID(),layer_previous.getNodeID());
        next = false;

    }
    }
   else if(e.getTarget().getClass().toString().equals("class sample.Canvas")){
        if(next==false){
       next = true;
       layer_previous = (Canvas) e.getTarget();}
       else{
            Canvas intermediaire = (Canvas) e.getTarget();
            System.out.println("B");
            System.out.println("inter = "+intermediaire.getNodeID()+" prev= "+layer_previous.getNodeID());
            Line line = new Line(intermediaire.getLayoutX()+10, intermediaire.getLayoutY()+10,layer_previous.getLayoutX()+10, layer_previous.getLayoutY()+10);
            plan.getChildren().add(line);
            line.setFill(bleu);
            line.toBack();
            intermediaire.toFront();
            layer_previous.toFront();
            g.setArc(intermediaire.getNodeID(),layer_previous.getNodeID());
            next = false;
        }
   }


}
}
