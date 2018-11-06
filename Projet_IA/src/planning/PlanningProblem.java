package planning;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeArray.*;


public class PlanningProblem {
    public PlanningProblem() {
    }

    public Stack<State> DFS (Problem problem, State etat, Stack<State> plan, ArrayList<State> closed) {

        if (Action.satisfies(etat, problem.getFinal_states())) {
            return plan;
        } else {

        //on parcourt problem (je ne sais pas ce que c est)
            ArrayList aucuneideevrmt=new ArrayList();
            for (Action act : problem.getOuverts()) {
                if (Action.satisfies(act.getPremisse(), etat)) {
                    aucuneideevrmt.add(act);
                    State next = Action.apply(aucuneideevrmt, etat);
                    if (!closed.contains(next)) {
                        //on agit sur un STACK(pile) avec push pour ajouter en haut, pop pour retirer en haut
                        push(plan, act);
                        closed.add(next);
                        Stack<State> subplan = DFS(problem, next, plan, closed);
                        if (!(subplan == null)) {
                            return subplan;
                        } else {
                            plan.pop();
                        }
                    }
                }
        }
            return null;
        }
    }

    public Object BFS(Problem problem){
        HashMap<State,State> father=new HashMap();
        HashMap<State,Action> plan=new HashMap();
        ArrayList<State> closed=new ArrayList<>();
        Queue<State> open=new ArrayDeque<>();
        while (!(open.isEmpty())){
            //on agit sur une File avec offer pour ajouter en haut, poll pour retirer en haut
            State etat=open.poll();
            closed.add(etat);
            ArrayList aled=new ArrayList();
            for (Action act:problem.getOuverts()){
                if (Action.satisfies(act.getPremisse(),etat)){
                    aled.add(act);
                    State next = Action.apply(aled, etat);
                    if(!(closed.contains(next)) && !(open.contains(next))){
                        father.put(next,etat);
                        plan.put(next,act);
                        if(Action.satisfies(next,problem.getFinal_states())){
                            return get_bfs_plan(father,plan,next);
                        }
                        else{
                            open.offer(next);
                        }
                    }
                }
            }
        }

        return null;
    }

    public Object get_bfs_plan(HashMap<State,State> father,HashMap<State,Action> linconnu,State goal){
        while (goal!=null){
            push(linconnu,linconnu.get(goal));
            goal=father.get(goal);
        }
        return reverse(linconnu);
    }


}
//test pour github
//2eme test pour github