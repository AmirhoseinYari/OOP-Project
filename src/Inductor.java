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
        int itr = (int)(t/Data.dT);
        if(itr == 0) {
            i[0] = 0;
            return 0;
        }
        i[itr] = i[itr-1] + calV(t)/L*Data.dT;
        return i[itr];//integral of V/L (calV(t) or calV(t-dt) ?!!!)
    }

    @Override
    double calV(double t) {
        int itr = (int)(t/Data.dT);
        v[itr] = node1.v[itr]-node2.v[itr];
        return v[itr];
    }
}