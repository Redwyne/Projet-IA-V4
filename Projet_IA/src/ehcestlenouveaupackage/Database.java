package ehcestlenouveaupackage;

import javafx.util.Pair;
import representations.Variable;

import java.util.ArrayList;
import java.util.HashMap;

//que veut dire 'transcrire les instances'

public class Database extends BooleanDatabase{


    public Database(ArrayList<Transactions> liste_transactions, ArrayList<Variable> liste_variable) {
        super(liste_transactions, liste_variable);
    }

    public BooleanDatabase toBool(HashMap<Object,String> liste_transactions, HashMap<Object,String> liste_variable){

        HashMap<Object,String> bd_bool_tr=new HashMap();
        for (HashMap.Entry elt : liste_variable.entrySet()) {
            Pair p1=new Pair(elt.getKey(),elt.getValue());
            bd_bool_tr.put(p1,"true");


        }

        HashMap<Object,String> bd_bool_var=new HashMap();
        for (HashMap.Entry elt : liste_variable.entrySet()) {
            Pair p2=new Pair(elt.getKey(),elt.getValue());
            bd_bool_var.put(p2,"true");


        }

        //BooleanDatabase bd_bool_f=new BooleanDatabase(bd_bool_tr,bd_bool_var);
        //return bd_bool_f;
        return null;
    }
}
