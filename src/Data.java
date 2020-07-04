import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Data {
    public ArrayList<String> s = new ArrayList<String>();//inputs from file
    double dV,dI,dT;

    Data() throws IOException {
        File file = new File("input.txt");
        initial(file);//read the inputs
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
        while (i<s.size()) {//not a comment ('*' is a comment sign)
            if(s.get(i).charAt(0)!='*') {
                char x = s.get(i).charAt(0);//x is the first char of the line
                switch (x) {
                    case 'd':
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
                    default:
                        break;
                }
            }

            i++;
        }

    }

    void setDIVT(String s){//seting dI or dV or dT
        char y = s.charAt(1);//y is V or I or T
        //System.out.println("setDIVT called  "+getNumber(s.substring(s.indexOf(" "))));//for checking the method
        switch (y){
            case 'I'://dI
                dI = getNumber(s.substring(s.indexOf(" ")));
                break;
            case 'V'://dV
                dV = getNumber(s.substring(s.indexOf(" ")));
                break;
            case 'T'://dT
                dT = getNumber(s.substring(s.indexOf(" ")));
                break;
            default:
                break;
        }
    }

    double getNumber(String s){//for handeling p n u m k M G (s format is "1234M")
        double x;
        switch (s.charAt(s.length()-1)){
            case 'p':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e-12;
                break;
            case 'n':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e-9;
                break;
            case 'u':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e-6;
                break;
            case 'm':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e-3;
                break;
            case 'k':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e3;
                break;
            case 'M':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e6;
                break;
            case 'G':
                x = Double.parseDouble(s.substring(0,s.length()-1));
                x *= 1e9;
                break;
            default:
                x = Double.parseDouble(s.substring(0,s.length()-1));
                break;
        }
        return x;
    }

}
