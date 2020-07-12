import java.util.ArrayList;

public class Union {
    String name;
    Node mainNode;
    ArrayList<Node> nodes = new ArrayList<>();//all nodes in the union
    ArrayList<Union> unions = new ArrayList<>();//neighbor unions

    boolean visited;

    //constructor
    Union(){

    }

    //functions
    double inCurrent(double t){//input current
        int itr = (int)(t/Data.dT);
        double i = 0;
        for(Node n : nodes)
            i += n.inCurrent(t);
        return i;
    }
}
