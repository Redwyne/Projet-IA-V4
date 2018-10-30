package ppc;

import representations.*;
import java.util.*;
import java.util.Random;

public class gat {


    public static boolean Test(ArrayList<Variable> tableauv, ArrayList tableauc) {
     /* 1 . creation de la voiture en appelamnt Carcreator
        2. test avec toutes les contraintes differentes
        3. si passe pas : Appel a carmodifier
        4.sinon OK
      */
        Map mycar = CarCreator(tableauv);
        boolean out = false ;
        while (!out) {
            Carmofidier(mycar, tableauv);
            for (Object aTableauc : tableauc) {
                if (aTableauc instanceof Rule) {
                    Rule elt = (Rule) aTableauc;
                    out = elt.isSatisfiedBy(mycar);
                    if (!out) {
                        break;
                    }
                }

                else if (aTableauc instanceof AllEqualConstraint) {
                    AllEqualConstraint elt = (AllEqualConstraint) aTableauc;
                    out = elt.isSatisfiedBy(mycar);
                    if (!out){
                        break;
                    }
                }
                if (aTableauc instanceof Disjunction) {
                    Disjunction elt = (Disjunction) aTableauc;
                    if (elt.isSatisfiedBy(mycar)){
                        out = true;
                    }
                    else {
                        out = false;
                    }
                }
                if (aTableauc instanceof IncompatibilityConstraint) {
                    IncompatibilityConstraint elt = (IncompatibilityConstraint) aTableauc;
                    if (elt.isSatisfiedBy(mycar)){
                        out = true;
                    }
                    else {
                        out = false;
                    }
                }
            }

        }
        return out;
    }

    public static Map<String,String> CarCreator(ArrayList<Variable> tableauv) {
        Map<String, String> mycar = new HashMap();
        for (int i=0;i<tableauv.size();i++){
            Variable elt=tableauv.get(i);
            Set dom= elt.getDomaine();
            int taille = dom.size();
            int item = new Random().nextInt(taille);
            int cmpt = 0;
            for(Object str : dom)
            {
                if (cmpt == item)
                    mycar.put(elt.getNom(), (String) str);
                cmpt++;
            }

        }
        return mycar;


    }


    public static Map<String,String> Carmofidier(Map<String, String> mycar, ArrayList<Variable> tableauv) {
        int rand = new Random().nextInt(tableauv.size());
        String r_cle=tableauv.get(rand).getNom();
        Set domdom=tableauv.get(rand).getDomaine();
        Object[] l_dom= Arrays.copyOf(domdom.toArray(),domdom.size());
        int rand2 = new Random().nextInt(domdom.size());
        String r_value=(String) l_dom[rand2];
        //String finalcountdown=(String) Arrays.copyOf(tableauv.get(rand).getDomaine().toArray(),tableauv.get(rand).getDomaine().size())[rand2];
        mycar.put(r_cle,r_value);


        return mycar;
    }

}