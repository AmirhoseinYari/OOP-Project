public abstract class Towports { //element abstraction

    private String name;//name of element
    private Node node1;//posetive
    private Node node2;//negetive
    public double time = 0;//time !
    private double i;  //from 1 to 2                         (1 is + and 2 is -)
    private double v = node1.getV() - node2.getV();  //v1-v2 (1 is + and 2 is -)


    //default constructor
    Towports() {

    }

    //constructor
    Towports(String s) { //set name and nodes of each Towport element
        //to do
    }

    //functions
    String getName() {
        return name;
    }

    void setI() {
        i = calI();
    }

    double getI() {
        return i;
    }

    void setV() {
        v = calV();
    }
    double getV() {
        return v;
    }

    void setTime(double time) {
        this.time = time;
    }
    double getTime() {
        return time;
    }

    Node getNode1() {
        return node1;
    }

    Node getNode2() {
        return node2;
    }

    //abstract functions
    abstract double getValue();

    abstract double calI();//calculate I from V

    abstract double calV();//calculate V from I
}