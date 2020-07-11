public class Inductor extends Towports {

    double L;

    //constructor
    Inductor(String s) {
        super(s);
        int i = s.indexOf(" ",s.indexOf(" ",s.indexOf(" ")+1)+1);//space before value
        L = Data.getNumber(s.substring(i));
        //System.out.println("name:"+name+" value:"+L+"  node+ "+node1.name+"  node- "+node2.name);//just for testing
    }

    //functions
    @Override
    double getValue(double t) {
        return L;
    }

    @Override
    double calI(double t) {
        //to do
        return 0;//integral of V/L
    }

    @Override
    double calV(double t) {
        //to do
        return 0;//L*dI/dt
    }
}