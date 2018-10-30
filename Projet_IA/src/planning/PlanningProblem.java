package planning;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeArray.*;


public class PlanningProblem {
    public PlanningProblem() {
    }

    public Stack<State> DFS (Object probleme, State etat, Stack<State> plan, ArrayList<State> closed){

        if (Action.satisfies(etat,(Map) probleme)){
            return plan;
        }else{

            //on parcourt problem (je ne sais pas ce que c est)
        }   for(ArrayList<Action> act: probleme.actions){
            if(Action.is_applicable(act,etat)){
                State next=Action.apply(act,etat);
                if(!closed.contains(next)){
                    //on agit sur un STACK(pile) avec push pour ajouter en haut, pop pour retirer en haut
                    push(plan,act);
                    closed.add(next);
                    Stack<State> subplan=DFS(probleme,next,plan,closed);
                    if (!(subplan==null)){
                        return subplan;
                    }else{
                            plan.pop();
                    }
                }
            }
        }
        return null;
    }

    public Object BFS(Object problem){
        HashMap<State,State> father=new HashMap();
        HashMap<State,ArrayList<Action>> plan=new HashMap();
        ArrayList<State> closed=new ArrayList<>();
        ArrayList<State> open=new ArrayList<>();
        while (open!=null){
            State etat=dequeue(open);
            closed.add(etat);
            for (ArrayList<Action> act:problem.actions){
                if (Action.is_applicable(act,etat)){
                    State next=Action.apply(act,etat);
                    if(!(closed.contains(next)) && !(open.contains(next))){
                        father.put(next,etat);
                        plan.put(next,act);
                        if(Action.satisfies(next,problem.goal)){
                            return get_bfs_plan(father,plan,next);
                        }
                        else{
                            enqueue(open,next);
                        }
                    }
                }
            }
        }

        return null;
    }

    public Object get_bfs_plan(HashMap<State,State> father,HashMap<State,ArrayList<Action>> linconnu,State goal){
        while (goal!=null){
            push(linconnu,linconnu.get(goal));
            goal=father.get(goal);
        }
        return reverse(linconnu);
    }


}
