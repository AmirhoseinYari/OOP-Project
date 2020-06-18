public class Capacitor extends Towports {

    private double C;

    //constructor
    Capacitor(String s) {

    }

    //functions
    @Override
    double getValue() {
        return C;
    }

    @Override
    double calI() {
        //to do
        return 0;//C*dv/dt
    }

    @Override
    double calV() {
        //to do
        return 0;//integral of di/C + V0
    }
}