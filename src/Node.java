import java.util.ArrayList;
import java.util.HashMap;

public class Node { //normal node

    String name;//node name (just an integer number)
    double[] v;//voltage of node
    //double t;//time at the moment
    ArrayList<Towports> elements = new ArrayList<>();//elements of neighbor nodes
    HashMap<Towports,Node> neighborNodes = new HashMap<>();//only neighbor nodes in hash map

    boolean added;
    int union;

    //constructors
    Node() {

    }
    Node(String s) {
        this.name = s;
    }

    //functions
    void addElement(Towports element){
        elements.add(element);
    }

    double inCurrent(double t){//input current
        return -1;
    }
}
