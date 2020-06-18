public class Diode extends Towports { //ideal diode

    //constructor
    Diode(String s) {

    }

    //functions
    @Override
    double getValue() { //ideal diode doesn't have any value
        return 0;
    }

    @Override
    double calI() {
        if(getV()<0)
            return 0;
        else
            return -1;//to do (KCL)
    }

    @Override
    double calV() {
        if(getI()>0)
            return 0;
        else
            return getNode1().getV() - getNode2().getV();
    }
}