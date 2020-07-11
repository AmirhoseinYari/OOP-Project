public class VoltageSource extends Towports {

    double V;//value of the source
    double A;//domain of the AC part
    double f;//frequency
    double phi;//sin(2*pi*f*t + phi)
    int flag;//0 is normal / 1 is E / 2 is H

    //constructor
    VoltageSource(String s, int flag) {//V1 i j 5  0 0 0
        super(s);//setting name and nodes
        this.flag = flag;

        int i = s.indexOf(" ",s.indexOf(" ",s.indexOf(" ")+1)+1);//third space
        V = Data.getNumber(s.substring(i+1,s.indexOf(" ",i+1)));
        i = s.indexOf(" ",i+1);
        A = Data.getNumber(s.substring(i+1,s.indexOf(" ",i+1)));
        i = s.indexOf(" ",i+1);
        f = Data.getNumber(s.substring(i+1,s.indexOf(" ",i+1)));
        i = s.indexOf(" ",i+1);
        phi = Data.getNumber(s.substring(i+1));
        //System.out.println("name:"+name+" DC:"+V+"  node+"+node1.name+" node-"+node2.name+"  AC:"+A+" f:"+f+" phi:"+phi);
    }

    //functions
    @Override
    double getValue(double t) {
        return V;
    }


    @Override
    double calI(double t) {
        return -1;//to do (KCL)
    }

    @Override
    double calV(double t) {
        return V;
    }
}