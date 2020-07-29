import java.util.ArrayList;

public class Solve {

    //properties
    double t;//present time
    double dV=-1,dI=-1,dT=-1,MaxT=-1;//MaxT is .tran time
    int itr;//itr
    ArrayList<Union> unions = new ArrayList<>();

    //constructors
    Solve(){
        //System.out.println("trying to solve circuit");
        t = 0;
        dV = Data.dV;
        dI = Data.dI;
        dT = Data.dT;
        MaxT = Data.MaxT;
        itr = 0;// t/dT
        createUnions();
        error2check();
        solve();
    }

    //functions
    void createUnions(){
        //System.out.println("making unions" + "   all nodes : "+Data.nodesAL.size());
        unions.add(new Union(Data.nodes.get("0")));
        for(Node n : Data.nodesAL){
            if(n.added == false&&!n.name.equals("0")) {
                unions.add(new Union(n));
            }
        }

        //for test
//        for (Union u : unions)
//            u.print();
    }

    void solve(){
        System.out.println(unions.get(1).inCurrent(0));
    }

    void error2check(){//for checking -2 error  (current source problems)
        for(Node n : Data.nodesAL)
            if(n.error2>1) Data.flag=2;
         if(Data.flag==2) System.out.println("-2 error");
    }

    void error3check(){//for checking -3 error  (voltage source problems)

    }

    void error4check(){//for checking -4 error  (ground problems)

    }

    void error5check(){//for checking -5 error  (ground other problems :) (emtiazi) )

    }
}
