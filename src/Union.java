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
                    main.error3++;
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

    void solve(double t){
        if(mainNode.name.equals("0")) setAllVoltages(0,t);
        else {//not gnd
            double v, I1, I2;
            int itr = (int) (t / Data.dT);
            if(itr == 0) v = 0;
            else v = mainNode.v[itr-1];
            setAllVoltages(v,t);
            I1 = inCurrent(t);
            setAllVoltages(v + Data.dV, t);
            I2 = inCurrent(t);
            while (Math.abs(I2)>Data.dI){
                //System.out.println("while solve");
                setAllVoltages(v,t);
                I1 = inCurrent(t);
                setAllVoltages(v + Data.dV, t);
                I2 = inCurrent(t);
                v += (Math.abs(I1) - Math.abs(I2)) / Data.dI * Data.dV;
            }
            setAllVoltages(v, t);
        }
    }

    void setAllVoltages(double v,double t){//set other Nodes voltages at time t
        int itr = (int)(t/Data.dT);
        mainNode.v[itr] = v;
        for(Towports el : mainNode.elements){
            if(el.getClass().equals(VoltageSource.class)) {
                //System.out.println("voltage source");//for testing
                if(el.node1.equals(mainNode))
                    setNextNodeVoltage(el,el.node2,mainNode,t);
                else
                    setNextNodeVoltage(el,el.node1,mainNode,t);
            }
        }
    }

    void setNextNodeVoltage(Towports v, Node n, Node b, double t){//set next Node(Node n) voltage from last one(Node b)
        int itr = (int)(t/Data.dT);
        if(v.node1.name.equals(n.name))
            n.v[itr] = b.v[itr] + v.calV(t);
        else
            n.v[itr] = b.v[itr] - v.calV(t);
        for (Towports el : n.elements){
            if(el.getClass().equals(VoltageSource.class)) {
                if(el.node1.equals(n)&&!el.node2.equals(b))
                    setNextNodeVoltage(el,el.node2,n,t);
                else if(!el.node1.equals(b)&&el.node2.equals(n))
                    setNextNodeVoltage(el,el.node1,n,t);
            }
        }
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
                    n.error3++;//normal is 1 (if more then 1, error -2 happened)
                }
            }
        }
    }

    void print(double t){//for debugging
        System.out.println("Union:"+name+"  v:"+mainNode.v[(int)(t/Data.dT)]);
    }

}
