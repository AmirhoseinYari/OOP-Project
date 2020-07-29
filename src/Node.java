import java.util.ArrayList;
import java.util.HashMap;

public class Node { //normal node

    String name;//node name (just an integer number)
    double[] v;//voltage of node
    //double t;//time at the moment
    ArrayList<Towports> elements = new ArrayList<>();//elements of neighbor nodes
    HashMap<Towports,Node> neighborNodes = new HashMap<>();//only neighbor nodes in hash map
    ArrayList<Node> nodes = new ArrayList<>();//neighbor nodes in array list

    boolean added;
    String union;
    int error2;

    //constructors
    Node() {

    }

    Node(String s) {
        this.name = s;
        added = false;
        //union = Integer.parseInt(s);
        union = s;
        error2 = 0;
        v = new double[100000];
    }

    //functions
    void addElement(Towports element){
        elements.add(element);
        String n1 = element.node1.name, n2 = element.node2.name;
        if(n1.equals(name)){
            neighborNodes.put(element,Data.nodes.get(n2));
            nodes.add(Data.nodes.get(n2));
        }
        else {
            neighborNodes.put(element,Data.nodes.get(n1));
            nodes.add(Data.nodes.get(n1));
        }
    }

    double inCurrent(double t){//input current
        int itr = (int)(t/Data.dT);
        double i = 0;
        for(Towports x : elements)
            if(x.node2.name.equals(name))
                i += x.calI(t);
            else
                i -= x.calI(t);

        return i;//input current
    }

    void print(){//only for test
        System.out.println("node"+name+" N:"+elements.size()+" added:"+added+" Union:"+union);//node1 N:3
    }
}
