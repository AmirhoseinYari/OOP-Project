public class CurrentSource extends Towports {

    double I;//value of the DC source
    double A;//domain of the AC part
    double f;//frequency
    double phi;//sin(2*pi*f*t + phi)

    int flag;//0 is normal / 1 is G / 2 is F
    int a;//for dependent current sources (G & F)
    Node n1,n2;//for dependent current source G
    Towports element;//for dependent current source F

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
        int itr = (int)(t/Data.dT);
        if(flag == 0)//independent
            if(A == 0)//DC
                i[itr] = -I;
            else//AC
                i[itr] = -(I + A * Math.sin(2*Math.PI*f*t+phi));
        if(flag == 1)//G
            i[itr] = -a*(n1.v[itr]-n2.v[itr]);
        if(flag == 2)//F
            i[itr] = -a*element.calI(t);

        return i[itr];
    }

    @Override
    double calV(double t) {
        int itr = (int)(t/Data.dT);
        v[itr] = node1.v[itr]-node2.v[itr];
        return v[itr];
    }
}