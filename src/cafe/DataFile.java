import java.io.*;
import java.util.*;
public class DataFile {
    private Formatter x;
    private Scanner y;
    public void openFile(){
        try{
            x = new Formatter(new File("log.txt"));
        }
        catch(Exception e){
            System.out.println("no file");
        }        
    }    
    public void openFile2(){
        try{            
            y = new Scanner(new File("log.txt"));
        }
        catch(Exception e){
            System.out.println("no file");
        }        
    }
    public void addRecords(String arg1,String arg2, Double arg3){
        x .format("%s %s %.2f"+"%n",arg1,arg2,arg3);     
    }
    
    public void readRecords(){
        
        
    }
    
    public void closeFile(){
        x.close();        
    }
    public void closeFile2(){        
        y.close();
    }
}
