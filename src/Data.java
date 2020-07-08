import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Data {
    public ArrayList<String> s = new ArrayList<>();//inputs from file
    static double dV=-1,dI=-1,dT=-1,MaxT=-1;//MaxT is .tran time
    static int flag = 0;

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
                        break;
                    case 'V':
                        break;
                    case 'R':
                        break;
                    case 'C':
                        break;
                    case 'L':
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
        else if(dV==-1||dI==-1||dT==-1||MaxT==-1)
            System.out.println("-1 error");

    }

    void setDIVT(String s){//seting dI or dV or dT
        char y = s.charAt(1);//y is V or I or T
        //System.out.println("setDIVT called  "+getNumber(s.substring(s.indexOf(" "))));//for checking the method
        double x = getNumber(s.substring(s.indexOf(" ")));
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
        double x = getNumber(s.substring(s.indexOf(" ")));
        if(x!=-1){
            if(s.substring(0,s.indexOf(" ")).equals(".tran"))
                MaxT = x;
            else//not a normal comment ".nadfnn"
                flag = 1;
        }
        else
            flag = 1;//number problem
    }

    double getNumber(String s){//for handeling p n u m k M G (s format is "1234M") [if the number is negative or String returns "-1" as an error]
        double x;

        try {
            x = Double.parseDouble(s.substring(0, s.length() - 1));
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
                break;
        }
        return x;
    }

}
