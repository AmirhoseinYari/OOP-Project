public class Capacitor extends Towports {

    double C;

    //constructor
    Capacitor(String s) {
        super(s);
        int i = s.indexOf(" ",s.indexOf(" ",s.indexOf(" ")+1)+1);//space before value
        C = Data.getNumber(s.substring(i));
        //System.out.println("name:"+name+" value:"+C+"  node+ "+node1.name+"  node- "+node2.name);//just for testing
    }

    //functions
    @Override
    double getValue() {
        return C;
    }

    @Override
    double calI(double t) {
        //to do
        return 0;//C*dv/dt
    }

    @Override
    double calV(double t) {
        //to do
        return 0;//integral of di/C + V0
    }
}