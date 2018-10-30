package representations;


import java.util.ArrayList;
import java.util.Map;

public class Rule implements Constraint {

    Map<Variable,String> premisse;
    Map<Variable,String> conclusion;

    public Rule(Map<Variable, String> premisse, Map<Variable, String> conclusion) {
        this.premisse = premisse;
        this.conclusion = conclusion;
    }

    public Map<Variable, String> getPremisse() {
        return premisse;
    }

    public Map<Variable, String> getConclusion() {
        return conclusion;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> mycar) {
        boolean p = true;
        boolean c = true;
        for (Map.Entry<Variable, String> entry : premisse.entrySet()) {
            String x = entry.getKey().getNom();
            if (mycar.get(x)==(null)){
                p=true;
            }
            else if (mycar.get(x).equals(entry.getValue())) {
                p = true;
            } else {
                p = false;
                break;
            }

        }

        if (p) {
            for (Map.Entry<Variable, String> entry : conclusion.entrySet()) {
                String y = entry.getKey().getNom();
                if (mycar.get(y)==(null)){
                    c=true;
                }
                else if (! mycar.get(y).equals(entry.getValue())) {
                    c=false;
                }else{
                    c=true;
                    break;
                }

            }
        }
        return (!p || c) ;
    }
}
