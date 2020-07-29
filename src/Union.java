import java.util.ArrayList;

public class Union {
    String name;
    Node mainNode;
    ArrayList<Node> nodes = new ArrayList<>();//all nodes in the union
    //ArrayList<Union> unions = new ArrayList<>();//neighbor unions

    boolean visited;

    //constructor
    Union(Node main){//seting the mainNode
        main.union = main.name;
        main.added = true;
        mainNode = main;
        name = main.name;
        nodes.add(mainNode);
        for (Towports el : main.elements){
            if(el.getClass().equals(VoltageSource.class)) {
                //System.out.println("voltage source");//for testing
                if (el.node1.added == false)//node2 == mainNode
                    addNode(el.node1);
                else if(el.node2.added == false)//node1 == mainNode
                    addNode(el.node2);
                else{
                    main.error2++;
                }
            }
        }
    }

    //functions
    double inCurrent(double t){//input current
        int itr = (int)(t/Data.dT);
        double i = 0;
        for(Node n : nodes)
            i += n.inCurrent(t);
        return i;
    }

    void addNode(Node n){//adding another node
        n.union = name;
        n.added = true;
        nodes.add(n);
        for (Towports el : n.elements){
            if(el.getClass().equals(VoltageSource.class)) {
                if (el.node1.added == false)//node2 == mainNode
                    addNode(el.node1);
                else if(el.node2.added == false)//node1 == mainNode
                    addNode(el.node2);
                else{//both true (both nodes added)
                    n.error2++;//normal is 1 (if more then 1, error -2 happened)
                }
            }
        }
    }

    void print(){//for debugging
        System.out.println("Union:"+name+" nodes:"+nodes.size());
    }

}
