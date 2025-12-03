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
            int[] nrOfActors = new int[n+1];

            List<Integer>[] rolesWithActors =  (List<Integer>[]) new List[n+1];
            for (int i = 1; i <= n; i++){
                int m = io.getInt();
                nrOfActors[i] = m;
                rolesWithActors[i] = new ArrayList<>();
                for (int j = 1; j<=m; j++){
                    int p = io.getInt();
                    rolesWithActors[i].add(p);
                }
                rolesWithActors[i].sort(null);
            }

            int[] rolesIndexOrder = new int[n+1];

            for(int i = 1; i < n+1; i++){
                int minIndex = i;

                for(int j = 1; j < n+1; j++){
                    if(nrOfActors[j] < nrOfActors[minIndex]){
                        minIndex = j;
                    }
                }
                nrOfActors[minIndex] = Integer.MAX_VALUE;
                rolesIndexOrder[i] = minIndex;
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

            ArrayList<Integer> rolesActorOne = new ArrayList<Integer>();
            ArrayList<Integer> rolesActorTwo = new ArrayList<Integer>();


            for(int i = 1; i < rolesWithActors.length; i++){
                if(rolesWithActors[i].contains(1)){
                    rolesActorOne.add(i);
                }
                if(rolesWithActors[i].contains(2)){
                    rolesActorTwo.add(i);
                }
            }

            int roleActorOne = -1;
            int roleActorTwo = -1;

            for(int role1 : rolesActorOne){

                for(int role2 : rolesActorTwo){
                    if(!rolesEdges[role1].contains(role2)){
                        roleActorOne = role1;
                        roleActorTwo = role2;
                        break;
                    }
                }
                if(roleActorOne > -1 && roleActorTwo > -1){
                    break;
                }

            }

            int[] finalRoles = new int[n+1];
            finalRoles[roleActorOne] = 1;
            finalRoles[roleActorTwo] = 2;

            boolean hasActor = false;
            int superActor = k+1;
            for(int i = 1; i < n+1; i++){
                if(finalRoles[rolesIndexOrder[i]] != 0){
                    continue;
                }
                for(int j = 0; j < rolesWithActors[rolesIndexOrder[i]].size(); j++){
                    hasActor = false;
                    int actor = rolesWithActors[rolesIndexOrder[i]].get(j);
                    for(Integer role : rolesEdges[rolesIndexOrder[i]]){
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
                        finalRoles[rolesIndexOrder[i]] = actor;
                        break;
                    }

                    if(j == rolesWithActors[rolesIndexOrder[i]].size()-1){
                        finalRoles[rolesIndexOrder[i]] = superActor;
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

            for(int i = 1; i < finalActors.length; i++){
                if(finalActors[i] != null){
                    io.print(i + " " + finalActors[i].size());
                    for (Integer roles: finalActors[i]) {
                        io.print(" " + roles);
                    }
                    io.println("");
                    
                }
            }

            io.flush();
            io.close();
        }


        public static void main(String [] args){
            new Heuristic();

        }
    }