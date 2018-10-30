package representations;

import java.util.Map;

public class Disjunction implements Constraint {
    Map<Variable,String> djt;
    public Disjunction(Map<Variable, String> djt) {
        this.djt = djt;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> mycar) {
        boolean out=true;
        for (Map.Entry<Variable, String> entry : djt.entrySet()) {
            String x = entry.getKey().getNom();
            out=out || mycar.get(x).equals(entry.getValue());
        }


        return out;
    }
}
