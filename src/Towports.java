public abstract class Towports { //element abstraction

    String name;//name of element
    Node node1;//posetive
    Node node2;//negetive
    double[] i;  //from 1 to 2 (1 is + and 2 is -)
    double[] v;  //v1-v2 (1 is + and 2 is -)
    //double t;//time at the moment


    //default constructor
    Towports() {

    }

    //constructor
    Towports(String s) { //set name and nodes of each Towport element (C1 0 3 ....)
        int i = s.indexOf(" ");
        name = s.substring(0, i);
        String n1 = s.substring(i+1,s.indexOf(" ",i+1));//name of node1
        i = s.indexOf(" ",i+1);
        String n2 = s.substring(i+1,s.indexOf(" ",i+1));//name of node2
        if(!Data.nodes.containsKey(n1)){
            if(!Data.nodes.containsKey(n2)){//create node 1&2
                node1 = new Node(n1);
                node2 = new Node(n2);

                Data.nodes.put(n1,node1);
                Data.nodes.put(n2,node2);

                Data.nodesAL.add(node1);
                Data.nodesAL.add(node2);
            }
            else{//only create node 1
                node1 = new Node(n1);

                Data.nodes.put(n1,node1);

                Data.nodesAL.add(node1);

                node2 = Data.nodes.get(n2);
            }
        }
        else{
            if(!Data.nodes.containsKey(n2)){//only create node 2
                node2 = new Node(n2);

                Data.nodes.put(n2,node2);

                Data.nodesAL.add(node2);

                node1 = Data.nodes.get(n1);
            }
            else{//both nodes exist
                node1 = Data.nodes.get(n1);
                node2 = Data.nodes.get(n2);
            }
        }
        //name and node1 and node2 are OK
    }

    //functions

    //abstract functions
    abstract double getValue(double t);

    abstract double calI(double t);//calculate I from V

    abstract double calV(double t);//calculate V from I
}