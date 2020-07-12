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
    double getValue(double t) {
        return C;
    }

    @Override
    double calI(double t) {
        int itr = (int)(t/Data.dT);
        if(itr == 0) return 0;
        i[itr] = C*(calV(t)-calV(t-Data.dT))/Data.dT;
        return i[itr];//C*dv/dt
    }

    @Override
    double calV(double t) {
        int itr = (int)(t/Data.dT);
        v[itr] = node1.v[itr] - node2.v[itr];
        return v[itr];//integral of di/C + V0
    }
}