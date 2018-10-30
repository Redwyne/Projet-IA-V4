package representations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllEqualConstraint implements Constraint {
    Map<Variable,String> AEC;
    public AllEqualConstraint() {
        this.AEC=AEC;
    }

    public void setAEC(Map<Variable, String> AEC) {
        this.AEC = AEC;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> mp){
        for (Map.Entry<Variable,String> x : mp.entrySet()) {
            for (Map.Entry<Variable, String> entry : mp.entrySet()) {
                if (!(x.getValue().equals(entry.getValue()))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}