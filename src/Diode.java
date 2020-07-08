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
    double calI(double t) {
        if(v[(int)(t/Data.dT)]<0)
            return 0;
        else
            return -1;//to do (KCL)
    }

    @Override
    double calV(double t) {
        if(i[(int)(t/Data.dT)]>0)
            return 0;
        else
            return v[(int)(t/Data.dT)];//!!
    }
}