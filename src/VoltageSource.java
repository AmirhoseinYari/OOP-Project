public class VoltageSource extends Towports {

    private double V;//value of the source
    private double f = 0;//frequency

    //constructor
    VoltageSource(String s) {

    }

    //functions
    @Override
    double getValue() {
        return V;
    }

    double getF() {
        return f;
    }

    @Override
    double calI() {
        return -1;//to do (KCL)
    }

    @Override
    double calV() {
        return V;
    }
}