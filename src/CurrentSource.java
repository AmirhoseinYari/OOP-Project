public class CurrentSource extends Towports {

    private double I;//value of the source
    private double f = 0;//frequency

    //constructor
    CurrentSource(String s) {

    }

    //functions
    @Override
    double getValue() {
        return I;
    }

    double getF() {
        return f;
    }

    @Override
    double calI() {
        return I;
    }

    @Override
    double calV() {
        return getNode1().getV() - getNode2().getV();
    }
}