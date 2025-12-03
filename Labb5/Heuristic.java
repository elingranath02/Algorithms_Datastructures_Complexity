    import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


    public class Heuristic{
        Kattio io;


        public Heuristic(){
            io = new Kattio(System.in, System.out);
            int n = io.getInt();
            int s = io.getInt();
            int k = io.getInt();

            List<Integer>[] rolesWithActors =  (List<Integer>[]) new List[n+1];
            for (int i = 1; i <= n; i++){
                int m = io.getInt();
                rolesWithActors[i] = new ArrayList<>();
                for (int j = 1; j<=m; j++){
                    int p = io.getInt();
                    rolesWithActors[i].add(p);
                }
                rolesWithActors[i].sort(null);
            }

            Set<Integer>[] rolesEdges =  (Set<Integer>[]) new HashSet[n+1];
            for (int i = 0; i < n+1; i++) {
                rolesEdges[i] = new HashSet<>();
            }
            for (int i = 1; i<=s; i++){
                int m = io.getInt();
                List<Integer> tempRoles = new ArrayList<>();
                for (int j = 1; j<= m; j++){
                    tempRoles.add(io.getInt());
                }
                for (Integer integer : tempRoles) {
                    for (Integer integer2 : tempRoles) {
                        if (integer != integer2){
                            rolesEdges[integer].add(integer2);
                        }
                    }
                }
            }

            int[] finalRoles = new int[n+1];

            boolean hasActor = false;
            int superActor = k+1;
            for(int i = 1; i < n+1; i++){
                for(int j = 0; j < rolesWithActors[i].size(); j++){
                    hasActor = false;
                    int actor = rolesWithActors[i].get(j);
                    for(Integer role : rolesEdges[i]){
                        if(finalRoles[role] == actor){
                            hasActor = true;
                            break;
                        }
                        if(finalRoles[role] == 1 && actor==2){
                            hasActor = true;
                            break;
                        }
                        if(finalRoles[role] == 2 && actor==1){
                            hasActor = true;
                            break;
                        }
                    }
                    if(hasActor == false){
                        finalRoles[i] = actor;
                        break;
                    }

                    if(j == rolesWithActors[i].size()-1){
                        finalRoles[i] = superActor;
                        superActor++;
                    }

                }
            }
            List<Integer>[] finalActors = (List<Integer>[]) new ArrayList[superActor];
            int totalActors = 0;
            for (int i = 1 ; i < finalRoles.length; i++){
                if(finalActors[finalRoles[i]] == null){
                    totalActors++;
                    finalActors[finalRoles[i]] = new ArrayList<>();
                }
                finalActors[finalRoles[i]].add(i);
            }
            
            io.println(totalActors);
            //System.out.println(totalActors);

            for(int i = 1; i < finalActors.length; i++){
                if(finalActors[i] != null){
                    io.print(i + " " + finalActors[i].size());
                    //System.out.print(i + " " + finalActors[i].size());
                    for (Integer roles: finalActors[i]) {
                        io.print(" " + roles);
                        //System.out.print(" " + roles);
                    }
                    io.println("");
                    //System.out.println("");
                    
                }
            }
            

            io.flush();
            io.close();
        }

        // public static void checkActorPermission(){


        // }

        public static void main(String [] args){
            new Heuristic();

        }
    }