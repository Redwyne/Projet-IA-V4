package planning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Problem {
    State init_state;
    ArrayList<Action> ouverts;
    State final_states;

    public Problem(State init_state, ArrayList<Action> ouverts, State final_states) {
        this.init_state = init_state;
        this.ouverts = ouverts;
        this.final_states = final_states;
    }

    public State getInit_state() {
        return init_state;
    }

    public ArrayList<Action> getOuverts() {
        return ouverts;
    }

    public State getFinal_states() {
        return final_states;
    }
}
