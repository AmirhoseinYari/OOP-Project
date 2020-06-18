public class Resistor extends Towports {

    private double R;

    //constructor
    Resistor(String s) {

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