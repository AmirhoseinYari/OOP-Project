import java.util.ArrayList;

public class Node { //normal node

    String name;//node name
    double[] v;//voltage of node
    //double t;//time at the moment
    ArrayList<Towports> elements = new ArrayList<>();//elements of neighbor nodes

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
}
