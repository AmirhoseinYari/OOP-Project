public class Resistor extends Towports {

    double R;

    //constructor
    Resistor(String s) {//R1 1 0 3k
        super(s);
        int i = s.indexOf(" ",s.indexOf(" ",s.indexOf(" ")+1)+1);//space before value
        R = Data.getNumber(s.substring(i));
        //System.out.println("name:"+name+" value:"+R+"  node+ "+node1.name+"  node- "+node2.name);//just for testing
    }

    //functions
    @Override
    double getValue(double t){
        return R;
    }

    @Override
    double calI(double t) {
        return calV(t)/R;
    }

    @Override
    double calV(double t) {
        int itr = (int)(t/Data.dT);
        return node1.v[itr]-node2.v[itr];
    }
}