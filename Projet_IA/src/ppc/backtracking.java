package ppc;

import representations.*;

import java.util.*;

public class backtracking {
    public static ArrayList<Variable> tableauv;
    public static ArrayList<Constraint> tableauc;
    public static Map mycar;
    public static int x = 0;
    public static boolean end = false;
    public static Object last;

    public backtracking(ArrayList<Variable> tableauv, ArrayList<Constraint> tableauc, Map mycar) {
        this.tableauv = tableauv;
        this.tableauc = tableauc;
        this.mycar = mycar;
    }

    public static Map Test(ArrayList<Variable> tableauv, ArrayList tableauc, Map mycar) {

        boolean out = false;
        int nbop = tableauv.size() + 1;
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
                if (aTableauc instanceof AllEqualConstraint) {
                    AllEqualConstraint elt = (AllEqualConstraint) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
                if (aTableauc instanceof Disjunction) {
                    Disjunction elt = (Disjunction) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
                if (aTableauc instanceof IncompatibilityConstraint) {
                    IncompatibilityConstraint elt = (IncompatibilityConstraint) aTableauc;
                    if (elt.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
            }
            Carmofidier(mycar, tableauv, out);
            if (out) {
                x++;
            }
        }
        return mycar;
    }

    public static Map CarCreator(ArrayList<Variable> tableauv) {
        Map<String, String> mycar = new HashMap<>();

        Object[] l_dom = Arrays.copyOf(tableauv.get(0).getDomaine().toArray(), tableauv.get(0).getDomaine().size());
        mycar.put(tableauv.get(0).getNom(), (String) l_dom[0]);

        return mycar;
    }


    public static Map<String, String> Carmofidier(Map<String, String> mycar, ArrayList<Variable> tableauv, boolean out) {
        if (out) {
            for (Variable elt : tableauv) {
                if (!mycar.containsKey(elt.getNom())) {
                    Object[] l_dom = Arrays.copyOf(elt.getDomaine().toArray(), elt.getDomaine().size());
                    mycar.put(elt.getNom(), (String) l_dom[0]);
                    return mycar;
                }
            }
        } else {
            String last = "";
            Object[] l_dom = null;
            for (Variable elt : tableauv) {
                if (mycar.containsKey(elt.getNom())) {
                    last = elt.getNom();
                    l_dom = Arrays.copyOf(elt.getDomaine().toArray(), elt.getDomaine().size());
                }
            }


            for (int val = 0; val < l_dom.length; val++) {
                if (l_dom[val].equals(mycar.get(last)) && val < l_dom.length - 1) {

                    mycar.put(last, (String) l_dom[val + 1]);
                    return mycar;
                } else if (val == l_dom.length - 1) {
                    mycar.remove(last);
                    x--;
                    Carmofidier(mycar, tableauv, false);
                }
            }
        }

        return mycar;
    }

    public static ArrayList solution() {
        ArrayList<HashMap<String,String>> res=new ArrayList();

        for (int y=0;y<5;y++){
            HashMap<String,String> voiture= (HashMap) Test(tableauv,tableauc,mycar);

            HashMap paul = new HashMap<String, String>();
            for (Map.Entry elt : voiture.entrySet()) {

                paul.put(elt.getKey(), elt.getValue());
            }
            res.add(paul);


            Carmofidier(voiture,tableauv,false);
            x--;
            for (Variable elt:tableauv){
                if (mycar.containsKey(elt.getNom())) {
                    last=elt.getNom();
                }
            }
            mycar.remove(last);
            System.out.println(res);
            System.out.println("\n");
        }
        return res;
    }
}