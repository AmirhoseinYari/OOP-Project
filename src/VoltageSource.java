public class VoltageSource extends Towports {

    double V;//value of the source
    double A;//domain of the AC part
    double f;//frequency
    double phi;//sin(2*pi*f*t + phi)

    int flag;//0 is normal / 1 is E / 2 is H
    int a;//for dependent voltage sources (E & H)
    Node n1,n2;//for dependent voltage source E
    Towports element;//for dependent current source H

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
        return 0;//to do (KCL)
    }

    @Override
    double calV(double t) {
        int itr = (int)(t/Data.dT);
        if(flag == 0)//independent
            if(A == 0)//DC
                v[itr] = V;
            else//AC
                v[itr] = V + A * Math.sin(2*Math.PI*f*t+phi);
        if(flag == 1)//E
            v[itr] = a*(n1.v[itr]-n2.v[itr]);
        if(flag == 2)//H
            v[itr] = a*element.calI(t);

        return v[itr];
    }
}