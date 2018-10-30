package representations;

import java.util.*;

public interface Constraint {
    //public void getScope();
    public boolean isSatisfiedBy(Map<Variable, String> a);
}
