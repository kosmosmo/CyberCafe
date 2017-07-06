import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class HistoryFile {
    public void addRe(String arg1){
        try
        {
            String filename= "hisLog.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            
            fw.write(arg1);//appends the string to the file           
            fw.append("\r\n");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

}
}
