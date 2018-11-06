package ehcestlenouveaupackage;

import java.util.HashMap;
import representations.*;
//ok qu est ce qu un itemset
//que font ces classses?
//0 ou 1==>"true" "false"
//qu est ce qu un “support minimal"

public class BooleanDatabase {
    HashMap<Object,String> liste_transactions=new HashMap<>();
    HashMap<Object,String> liste_variable=new HashMap<>();

    public BooleanDatabase(HashMap<Object, String> liste_transactions, HashMap<Object, String> liste_variable) {
        this.liste_transactions = liste_transactions;
        this.liste_variable = liste_variable;
    }
}
