public class Resistor extends Towports {

    private double R;

    //constructor
    Resistor(String s) {//R1 1 0 3k

    }

    //functions
    @Override
    double getValue() {
        return R;
    }

    @Override
    double calI() {
        return getV()/R;
    }

    @Override
    double calV() {
        return R*getI();
    }
}