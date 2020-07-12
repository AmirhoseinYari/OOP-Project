public class Diode extends Towports { //ideal diode

    //constructor
    Diode(String s) {
        super(s);
        //System.out.println("diode constructor called");//just for test
    }

    //functions
    @Override
    double getValue(double t) { //ideal diode doesn't have any value
        return 0;
    }

    @Override
    double calI(double t) {
        int itr = (int)(t/Data.dT);
        if(v[itr]<=0) {
            i[itr] = 0;
            return 0;
        }
        else
            return -0;//to do (KCL)
    }

    @Override
    double calV(double t) {
        int itr = (int)(t/Data.dT);
        v[itr] = node1.v[itr] - node2.v[itr];
        if(i[itr]>0)
            return 0;
        else
            return v[itr];//!!
    }
}