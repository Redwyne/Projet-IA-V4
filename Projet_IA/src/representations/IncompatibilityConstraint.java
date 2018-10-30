package representations;

import java.util.Map;

public class IncompatibilityConstraint implements Constraint  {
    Map<Variable,String> IC;
    public IncompatibilityConstraint(Map<Variable, String> IC) {
        this.IC = IC;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> mycar) {
        boolean out=true;
        for (Map.Entry<Variable, String> entry : IC.entrySet()) {
            String x = entry.getKey().getNom();
            out=out && mycar.get(x).equals(entry.getValue());
        }


        return !out;
    }
}
