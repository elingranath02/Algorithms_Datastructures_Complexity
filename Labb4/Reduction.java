
public class Reduction{
    Kattio io;


    public Reduction(){
        io = new Kattio(System.in, System.out);
        int V = io.getInt();
        io.println(V + 2);
        int E = io.getInt();
        io.println(E+2*V);
        int m = io.getInt();
        if (m > V){
            m = V;
        }
        io.println(m+2);

        int a;
        int b;

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
        new Reduction();

    }
}