package examples;
import planning.Action;
import planning.State;
import representations.*;
import ppc.*;
import java.lang.reflect.Array;
import java.util.*;

import static ppc.backtracking.*;
import planning.*;
public class main {
    public static void main (String[] args) {


        // Creation de la variable de couleur du toit et de son domaine

        Set<String> d_1 = new HashSet<>();
        //d_1.add("R");
        d_1.add("G");
        d_1.add("V");
        d_1.add("N");
        //d_1.add("P");
        //d_1.add("F");
        //d_1.add("Y");
        //d_1.add("A");
        //d_1.add("U");
        //d_1.add("J");
        Variable ct=new Variable("couleur toit",d_1);


        // Creation de la variable de couleur de l'aile gauche et de son domaine

        Set<String> d_2 = new HashSet<>();
        //d_2.add("R");
        d_2.add("G");
        d_2.add("V");
        d_2.add("N");
        //d_2.add("P");
        //d_2.add("F");
        //d_2.add("Y");
        //d_2.add("A");
        //d_2.add("U");
        //d_2.add("J");
        Variable cg=new Variable("Couleur gauche",d_2);

        // Creation de la variable de couleur de l'aile droite et de son domaine

        Set<String> d_3 = new HashSet<>();
        d_3.add("G");
        d_3.add("B");
        d_3.add("V");
        d_3.add("N");
        //d_3.add("P");
        //d_3.add("F");
        //d_3.add("Y");
        //d_3.add("A");
        //d_3.add("U");
        //d_3.add("J");
        Variable cd=new Variable("Couleur droite",d_3);

        // Creation de la variable de couleur des roues  et de son domaine

        Set<String> d_4 = new HashSet<>();
        d_4.add("G");
        d_4.add("B");
        d_4.add("V");
        d_4.add("N");
        //d_4.add("P");
        //d_4.add("F");
        //d_4.add("Y");
        //d_4.add("A");
        //d_4.add("U");
        //d_4.add("J");
        Variable cr=new Variable("couleur roues",d_4);

        // Creation de la variable de couleur du volant et de son domaine

        Set<String> d_6 = new HashSet<>();
        d_6.add("G");
        d_6.add("B");
        d_6.add("V");
        d_6.add("N");
        //d_6.add("P");
        //d_6.add("F");
        //d_6.add("Y");
        //d_6.add("A");
        //d_6.add("U");
        //d_6.add("J");
        Variable cv=new Variable("couleur volant",d_6);


        // instanciation d'une regle de type Rule avec ses premisses (pr ) et ses conclusions ( ccl)

        HashMap<Variable,String> pr_etat= new HashMap<>();
        pr_etat.put(ct,"R");
        pr_etat.put(cg,"V");
        State pr=new State(pr_etat);
        HashMap<Variable,String> ccl_etat= new HashMap<>();
        ccl_etat.put(cd,"B");
        State ccl=new State(ccl_etat);
        Rule R1=new Rule(pr,ccl);

        // instanciation d'une regle de type Allequal constraint


        Map<Variable,String> AEC= new HashMap<>();
        AEC.put(ct,"R");
        AEC.put(cg,"V");
        AllEqualConstraint A1=new AllEqualConstraint();
        A1.setAEC(AEC);

        // instanciation d'une regle de type disjunction

        Map<Variable,String> dj= new HashMap<>();
        dj.put(ct,"R");
        dj.put(cg,"V");
        Disjunction D1=new Disjunction(dj);

        // instanciation d'une regle de type IncompatibilityConstraint

        Map<Variable,String> ic= new HashMap<>();
        ic.put(ct,"R");
        ic.put(cg,"V");
        IncompatibilityConstraint IC1=new IncompatibilityConstraint(ic);

        //instanciation d'un tableua de toutes les contraintes (regles)

        ArrayList<Constraint> tableauc=new ArrayList<>();
        tableauc.add(R1);
        tableauc.add(A1);
        tableauc.add(D1);

        //instancation d'un tableau de toutes les variables et leurs domaines.

        ArrayList<Variable> tableauv=new ArrayList<>();
        tableauv.add(ct);
        tableauv.add(cg);
        tableauv.add(cd);
        tableauv.add(cr);
        tableauv.add(cv);



        HashMap<Variable,String> premisses_etat= new HashMap<>();
        State premisses=new State(premisses_etat);
        premisses.getEtat().put(cg,"V");
        HashMap<Variable,String> effect_etat= new HashMap<>();
        State effect=new State(effect_etat);
        effect.getEtat().put(cg,"N");
        Action color =new Action(premisses,effect);

        HashMap<Variable,String> etat=new HashMap();
        State blabla= new State(etat);
        blabla.affectation(tableauv);

        ArrayList<Action> Actions= new ArrayList<>();
        Actions.add(color);
        blabla.AfficheEtat();
        Action.apply(Actions,blabla);
        blabla.AfficheEtat();

        blabla = Action.apply(Actions,blabla);
        /*
        test de Backtracking :

        backtracking.mycar = CarCreator(tableauv);
        for (Variable elt:tableauv){
            System.out.println(elt.getDomaine());
            System.out.println(elt.getNom());
        }
        backtracking.tableauv=tableauv;
        backtracking.tableauc=tableauc;
        backtracking.solution();

        backtracking_nosol.Test(tableauv,tableauc);

       */


    }
}