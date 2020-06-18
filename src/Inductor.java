public class Inductor extends Towports {

    private double L;

    //constructor
    Inductor(String s) {

    }

    //functions
    @Override
    double getValue() {
        return L;
    }

    @Override
    double calI() {
        //to do
        return 0;//integral of V/L
    }

    @Override
    double calV() {
        //to do
        return 0;//L*dI/dt
    }
}