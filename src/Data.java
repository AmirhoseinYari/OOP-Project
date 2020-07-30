import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Data {

    public ArrayList<String> s = new ArrayList<>();//inputs from file

    static double dV=-1,dI=-1,dT=-1,MaxT=-1;//MaxT is .tran time
    static int flag = 0;//for errors (-1, -2, ...)

    static ArrayList<Towports> elements = new ArrayList<>();//all elements of the circuit
    static HashMap<String,Towports> elementsHM = new HashMap<>();//name of the element, element

    static HashMap<String,Node> nodes = new HashMap<>();//name of the node, node
    static ArrayList<Node> nodesAL = new ArrayList<>();//nodes in a array list


    Data() throws IOException {
        File file = new File("input.txt");
        initial(file);//read the inputs from input.txt
        createCircuit();//making the circuit
    }

    void initial(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine()) {
            String t = scanner.nextLine().replaceAll("[ ]+"," ");//removing extra spaces
            //System.out.println("line "+(i+1)+"  "+t);//just for debugging
            s.add(i++,t);
        }
        //got input.txt as an ArrayList<String>
    }

    void createCircuit() {//creating nodes and elements of the circuit
        int i = 0;
        while (i<s.size()&&flag==0) {//not a comment ('*' is a comment sign)
            if(s.get(i).charAt(0)!='*') {
                char x = s.get(i).charAt(0);//x is the first char of the line
                switch (x) {
                    case 'd'://dI || dV || dT
                        setDIVT(s.get(i));
                        break;
                    case 'I':
                        setI(s.get(i),0);
                        break;
                    case 'G':
                        setI(s.get(i),1);
                        break;
                    case 'F':
                        setI(s.get(i),2);
                        break;
                    case 'V':
                        setV(s.get(i),0);
                        break;
                    case 'E':
                        setV(s.get(i),1);
                        break;
                    case 'H':
                        setV(s.get(i),2);
                        break;
                    case 'R':
                        setR(s.get(i));
                        break;
                    case 'C':
                        setC(s.get(i));
                        break;
                    case 'L':
                        setL(s.get(i));
                        break;
                    case 'D':
                        setD(s.get(i));
                        break;
                    case '.':
                        setMaxT(s.get(i));
                        //System.out.println("Max time is "+MaxT);//for testing
                        break;
                    default://not a normal comment
                        flag = 1;
                        break;
                }
            }

            i++;
        }
        //out of while maybe because of an error (flag == 1)
        if(flag == 1)
            System.out.println("not a normal comment at line "+(i));//some comment is wrong (negative number or String to number problem)
        else if(dV==-1||dI==-1||dT==-1||MaxT==-1) {
            System.out.println("-1 error");
            flag = 1;//shouldn't run the code in solve class
        }

    }

    void setI(String s, int f){//f =   0 is normal / 1 is G / 2 is F
        Towports I = new CurrentSource(s,f);
        if(I.getValue(0)==-1)
            flag = 1;//just the DC part
        I.node1.addElement(I);
        I.node2.addElement(I);
        elements.add(I);
        elementsHM.put(I.name,I);
    }

    void setV(String s, int f){//f =   0 is normal / 1 is E / 2 is H
        Towports V = new VoltageSource(s,f);
        if(V.getValue(0)==-1)
            flag = 1;//just the DC part
        V.node1.addElement(V);
        V.node2.addElement(V);
        elements.add(V);
        elementsHM.put(V.name,V);
    }

    void setR(String s){//R.....
        Towports R = new Resistor(s);
        if(R.getValue(0)==-1)
            flag = 1;
        R.node1.addElement(R);
        R.node2.addElement(R);
        elements.add(R);
        elementsHM.put(R.name,R);
    }

    void setC(String s){
        Towports C = new Capacitor(s);
        if(C.getValue(0)==-1)
            flag = 1;
        C.node1.addElement(C);
        C.node2.addElement(C);
        elements.add(C);
        elementsHM.put(C.name,C);
    }

    void setL(String s){
        Towports L = new Capacitor(s);
        if(L.getValue(0)==-1)
            flag = 1;
        L.node1.addElement(L);
        L.node2.addElement(L);
        elements.add(L);
        elementsHM.put(L.name,L);
    }

    void setD(String s){
        Towports D = new Diode(s);
        D.node1.addElement(D);
        D.node2.addElement(D);
        elements.add(D);
        elementsHM.put(D.name,D);
    }

    void setDIVT(String s){//seting dI or dV or dT
        char y = s.charAt(1);//y is V or I or T
        //System.out.println("setDIVT called  "+getNumber(s.substring(s.indexOf(" "))));//for checking the method
        double x = getNumber(s.substring(s.indexOf(" ")+1));
        if(x!=-1) {//numbers are positive and not a String
            switch (y) {
                case 'I'://dI
                    dI = x;
                    break;
                case 'V'://dV
                    dV = x;
                    break;
                case 'T'://dT
                    dT = x;
                    break;
                default://d*
                    flag = 1;//not a normal comment (dH... , H doesn't even exist)
                    break;
            }
        }
        else
            flag = 1;//number problem
    }

    void setMaxT(String s){//seting .tran time  "MaxT"
        double x = getNumber(s.substring(s.indexOf(" ")+1));
        if(x!=-1){
            if(s.substring(0,s.indexOf(" ")).equals(".tran"))
                MaxT = x;
            else//not a normal comment ".nadfnn"
                flag = 1;
        }
        else
            flag = 1;//number problem
    }

    static double getNumber(String s){//for handeling p n u m k M G (s format is "1234M") [if the number is negative or String returns "-1" as an error]
        double x;
        try {
            if(s.length()>1)
                x = Double.parseDouble(s.substring(0, s.length() - 1));
            else
                x = Double.parseDouble(s);
        }
        catch (Exception e){
            //System.out.println("exception "+s.substring(0, s.length() - 1));//just for testing
            return -1;//not an number
        }

        if(x<0) return -1;//not a positive number

        switch (s.charAt(s.length()-1)){
            case 'p':
                x *= 1e-12;
                break;
            case 'n':
                x *= 1e-9;
                break;
            case 'u':
                x *= 1e-6;
                break;
            case 'm':
                x *= 1e-3;
                break;
            case 'k':
                x *= 1e3;
                break;
            case 'M':
                x *= 1e6;
                break;
            case 'G':
                x *= 1e9;
                break;
            default:
                x = Double.parseDouble(s);
                break;
        }
        return x;
    }

}
