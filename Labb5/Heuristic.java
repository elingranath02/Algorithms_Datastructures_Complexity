
public class Heuristic{
    Kattio io;


    public Heuristic(){
        io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int s = io.getInt();
        int k = io.getInt();

        for (int i = 1; i <= n; i++){
            int j = 
        }

        StringBuilder s = new StringBuilder();
        s.append(m);
        io.println(1 + " " + 1);
        io.println(1 + " " + 2);
        for(int i = 3; i <= m+2; i++){
            s.append(" ");
            s.append(i);
        }

        for(int i = 3; i <= V+2; i++){
            io.println(s);
        }

        for(int i = 3; i<= V+2; i++){
            io.println(2 + " " + 1 + " " + i);
            io.println(2 + " " + 2 + " " + i);
        }

        for(int i = 0; i < E; i++){
            a = io.getInt() + 2;
            b = io.getInt() + 2;
            io.println(2 + " " + a + " " + b);
        }

        io.flush();
        io.close();
    }

    public static void main(String [] args){
        new Heuristic();

    }
}