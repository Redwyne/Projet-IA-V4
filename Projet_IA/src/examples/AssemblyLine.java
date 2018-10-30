package examples;

import java.util.*;
import java.util.concurrent.*;

import planning.Action;
import representations.*;
import planning.*;
import javax.print.attribute.standard.Sides;

public class AssemblyLine {
    HashMap<Variable,String> init_state;

    public AssemblyLine(HashMap<Variable,String> init_state) {
        this.init_state=init_state;
    }

    public State simulation(){
        //on initialise toutes nos variables (et les possibilités de couleurs) (ici par ce que je sais pas ou les mettre autrement)
        Set<String> tf=new HashSet();
        tf.add("true");
        tf.add("false");
        Set<String> ALL_COLORS=new HashSet<>();
        ALL_COLORS.add("GRAY");
        ALL_COLORS.add("BLACK");
        ALL_COLORS.add("WHITE");
        ALL_COLORS.add("RED");
        ALL_COLORS.add("GREEN");
        ALL_COLORS.add("BLUE");
        ALL_COLORS.add("ORANGE");
        ALL_COLORS.add("YELLOW");

        Variable HAS_CHASSIS=new Variable("chassis",tf);
        Variable HAS_roue_avg=new Variable("roue_avg",tf);
        Variable HAS_roue_avd=new Variable("roue_avd",tf);
        Variable HAS_roue_arg=new Variable("roue_arg",tf);
        Variable HAS_roue_ard=new Variable("roue_ard",tf);
        Variable HAS_CORPS=new Variable("corps",tf);

        ArrayList<Variable> X_Y_WHEEL_COLOR=new ArrayList<>();
        X_Y_WHEEL_COLOR.add(new Variable(HAS_roue_avg.getNom(),ALL_COLORS));
        X_Y_WHEEL_COLOR.add(new Variable(HAS_roue_avd.getNom(),ALL_COLORS));
        X_Y_WHEEL_COLOR.add(new Variable(HAS_roue_arg.getNom(),ALL_COLORS));
        X_Y_WHEEL_COLOR.add(new Variable(HAS_roue_ard.getNom(),ALL_COLORS));

        ArrayList<Variable> SIDE_COLOR=new ArrayList();
        SIDE_COLOR.add(new Variable("FRONT",ALL_COLORS));
        SIDE_COLOR.add(new Variable("LEFT",ALL_COLORS));
        SIDE_COLOR.add(new Variable("RIGHT",ALL_COLORS));
        SIDE_COLOR.add(new Variable("REAR",ALL_COLORS));
        SIDE_COLOR.add(new Variable("ROOF",ALL_COLORS));
        //on fait un état initial, contenant chaque variable, avec la couleur grise
        for (Variable elt: X_Y_WHEEL_COLOR){
            init_state.put(elt,"GRAY");
        }
        for (Variable elt: SIDE_COLOR){
            init_state.put(elt,"GRAY");
        }

        State etat_but=new State(init_state);
        //on crée une action permettant d ajouter le chassis(aucune precondition) avant de l appliquer a notre etat_but (le chassis permettant la pose de toutes autres pieces)
        Map<Variable,String> precond= new HashMap<>();
        Map<Variable,String> effect= new HashMap<>();
        effect.put(HAS_CHASSIS,"true");
        Action ajout_chassis =new Action(precond,effect);
        ArrayList<Action> liste_action=new ArrayList<>();
        liste_action.add(ajout_chassis);
        //etat_but.getEtat().put(HAS_CHASSIS,"true");               équivalent si apply marche

        //on vide effect (precond n a pas besoin) pour le re utiliser
        //on parcourt notre init_state pour créer chacune de ses valeurs à notre etat_but (qu on va accompagner d une couleur random)
        for (HashMap.Entry jsp : init_state.entrySet()) {
            precond.clear();
            effect.clear();
            precond.put(HAS_CHASSIS,"true");
            int randomNum = ThreadLocalRandom.current().nextInt(0, ALL_COLORS.size() + 1);
            Object[] l_allcolor =Arrays.copyOf(ALL_COLORS.toArray(), (ALL_COLORS.size()));
            effect.put((Variable)jsp.getKey(),(String) l_allcolor[randomNum]);
            Action actionX=new Action(precond,effect);
            liste_action.add(actionX);
;            //etat_but.getEtat().put((Variable) jsp.getKey(),(String) l_allcolor[randomNum]);           equivalent si action marche
            //ALLCOLOR-1 ou ALLCOLOR.size-1
        }
        Action.apply(liste_action,etat_but);
        return etat_but;
    }
}
