public class Node { //normal node

    private String name;//node name
    private double v;//voltage of node
    private Towports[] elements;//elements of neighbor nodes

    boolean added;
    int union;

    //constructors
    Node() {

    }
    Node(String s) {
        this.name = s;
    }

    //functions
    String getName() { //we set name in constructor
        return name;
    }

    void setV(double voltage) {
        this.v = voltage;
    }

    double getV() {
        return v;
    }

    void setElements(Towports[] neighbors) {
        this.elements = neighbors.clone();
    }

    Towports[] getElements() {
        return elements;
    }
}
