package representations;


import planning.State;

import java.util.ArrayList;
import java.util.Map;

public class Rule implements Constraint {

    State premisse;
    State conclusion;

    public Rule(State premisse, State conclusion) {
        this.premisse = premisse;
        this.conclusion = conclusion;
    }

    public State getPremisse() {
        return premisse;
    }

    public State getConclusion() {
        return conclusion;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> mycar) {
        boolean p = true;
        boolean c = true;
        for (Map.Entry<Variable, String> entry : premisse.getEtat().entrySet()) {
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
            for (Map.Entry<Variable, String> entry : conclusion.getEtat().entrySet()) {
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
