public class Solve {

    //properties
    double t;//present time
    double dV=-1,dI=-1,dT=-1,MaxT=-1;//MaxT is .tran time

    //constructors
    Solve(){
        System.out.println("trying to solve circuit");
    }
    Solve(Data data){

    }

    //functions

    void error2check(){//for checking -2 error  (current source problems)

    }

    void error3check(){//for checking -3 error  (voltage source problems)

    }

    void error4check(){//for checking -4 error  (ground problems)

    }

    void error5check(){//for checking -5 error  (ground other problems :) (emtiazi) )

    }
}
