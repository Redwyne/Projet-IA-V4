
package ppc;

import representations.*;

import java.util.*;

public class backtracking_nosol {
    private static int x;
    public static boolean Test(ArrayList<Variable> tableauv, ArrayList tableauc) {

        Map mycar = CarCreator(tableauv);

        boolean out = false;
        int nbop=tableauv.size()+1;

        x=0;
        while (x < nbop) {
            for (Object aTableauc : tableauc) {
                if (aTableauc instanceof Rule) {
                    Rule elt = (Rule) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }


                else if (aTableauc instanceof AllEqualConstraint) {
                    AllEqualConstraint elt = (AllEqualConstraint) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
                else if (aTableauc instanceof Disjunction) {
                    Disjunction elt = (Disjunction) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
                else if (aTableauc instanceof IncompatibilityConstraint) {
                    IncompatibilityConstraint elt = (IncompatibilityConstraint) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
            }
            mycar=Carmofidier(mycar, tableauv, out);
            if (out) {
                x++;
            }
        }

        return true;
    }

    public static Map CarCreator(ArrayList<Variable> tableauv) {
        Map<String, String> mycar = new HashMap<>();

        Object[] l_dom = Arrays.copyOf(tableauv.get(0).getDomaine().toArray(), tableauv.get(0).getDomaine().size());
        mycar.put(tableauv.get(0).getNom(),(String) l_dom[0]);

        return mycar;
    }


    public static Map<String,String> Carmofidier(Map<String, String> mycar, ArrayList<Variable> tableauv,boolean out) {
        System.out.println(mycar);
        if (out){
            //cherche la premiere variable pas encore attribuée à mycar, l ajoute à mycar en affectant la premiere valeur puis retourne
            for (Variable elt:tableauv){
                if (!mycar.containsKey(elt.getNom())){
                    Object[] l_dom = Arrays.copyOf(elt.getDomaine().toArray(),elt.getDomaine().size());
                    mycar.put(elt.getNom(),(String) l_dom[0]);
                    return mycar;
                }
            }
        }else{
            String last="";
            Object [] l_dom=null;
            for (Variable elt:tableauv){
                if (mycar.containsKey(elt.getNom())){
                    last=elt.getNom();
                    l_dom = Arrays.copyOf(elt.getDomaine().toArray(),elt.getDomaine().size());
                }
            }
            for (int val=0;val<l_dom.length;val++){
                if(l_dom[val].equals(mycar.get(last)) && val<l_dom.length-1){
                    mycar.put(last,(String) l_dom[val+1]);
                    return mycar;
                }
                else if(val==l_dom.length-1){
                    mycar.remove(last);
                    x--;
                }
            }
        }

        return mycar;
    }


}
