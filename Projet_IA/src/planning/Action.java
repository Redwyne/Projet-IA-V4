package planning;

import java.util.*;
import representations.*;

public class Action extends Rule {

    public Action(State premisse, State conclusion) {
        super(premisse, conclusion);
}



/*
Ce que j'ai pigé :
    les 3 methodes demandé sont les fonctions memes de actions
    Ce que j'ai pas encore pigé :
        -pourquoi l'etat actuel de la voiture n'esst pas dans les arguments de action.
            rep : parce qu'action n'est qu'un objet, ce qu'on lance dans le main c'est apply (qui lui contien etat en argument)
- faire un   concstructeur super de action premisse et conclusoin pour pouvoir l"'instancier .


    reste a faire :
        check les 3 methodes la dessous si elles correspondent a ce qu'on veut
        - instancier une action dans le main
        - instancier un etat dans le main
        - appliquer une action a un etat dans le main

 */

    public static boolean satisfies(State etat, State etat_partiel) {
        for (Map.Entry elt : etat_partiel.getEtat().entrySet()) {
            if (!etat.getEtat().containsKey(elt.getKey()) || etat.getEtat().get(elt.getKey()) != elt.getValue()) {
                return false;
            }
        }

        return true;
    }

    public static boolean is_applicable(ArrayList<Action> action, State etat){
        for (Action act: action){
            if (satisfies(etat,act.getPremisse())){
                return true;
            }
        }
        return false;
    }



    public static State apply(ArrayList<Action> actions, State etat) {
        State etat_copy=new State(etat.getEtat());
        if (is_applicable(actions,etat)){
            for (Action act: actions){
                if(satisfies(etat,act.getPremisse())){
                    for (Map.Entry me : act.getConclusion().getEtat().entrySet()) {
                        etat_copy.getEtat().put((Variable) me.getKey(),(String) me.getValue());
                    }
                }
            }
        }

        return etat_copy;
    }

}
