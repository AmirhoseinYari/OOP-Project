public class VoltageSource extends Towports {

    double V;//value of the source
    double f = 0;//frequency

    //constructor
    VoltageSource(String s) {

    }

    //functions
    @Override
    double getValue() {
        return V;
    }


    @Override
    double calI(double t) {
        return -1;//to do (KCL)
    }

    @Override
    double calV(double t) {
        return V;
    }
}