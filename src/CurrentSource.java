public class CurrentSource extends Towports {

    double I;//value of the DC source
    double A;//domain of the AC part
    double f;//frequency
    double phi;//sin(2*pi*f*t + phi)
    int flag;//0 is normal / 1 is G / 2 is F

    //constructor
    CurrentSource(String s, int flag) {//Iin i j 5 0 0 0  || G || F
        super(s);//setting name and nodes
        this.flag = flag;

        int i = s.indexOf(" ",s.indexOf(" ",s.indexOf(" ")+1)+1);//third space
        I = Data.getNumber(s.substring(i+1,s.indexOf(" ",i+1)));
        i = s.indexOf(" ",i+1);
        A = Data.getNumber(s.substring(i+1,s.indexOf(" ",i+1)));
        i = s.indexOf(" ",i+1);
        f = Data.getNumber(s.substring(i+1,s.indexOf(" ",i+1)));
        i = s.indexOf(" ",i+1);
        phi = Data.getNumber(s.substring(i+1));
        //System.out.println("name:"+name+" DC:"+I+"  node+"+node1.name+" node-"+node2.name+"  AC:"+A+" f:"+f+" phi:"+phi);
    }

    //functions
    @Override
    double getValue(double t) {
        return I;
    }


    @Override
    double calI(double t) {
        return I;
    }

    @Override
    double calV(double t) {
        return -1;
    }
}