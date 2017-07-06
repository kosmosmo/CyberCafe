import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.Timer;
public class Main extends Application {
    private int minute;
    private int hour;
    private int second;
    private int year;
    private int date;
    private int month;
    private String toLog;    
    private String nullTime = "-00:00:00";
    DataFile df = new DataFile();
    HistoryFile hf = new HistoryFile();
    TextField textField = new TextField ();
    Price price = new Price();
    TextArea textArea = new TextArea();
    ArrayList<Time> timeList = new ArrayList<Time>();
    TextField textField2 = new TextField ();
    public static void main(String[] args) {
        launch(args);           
       }
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        income();
        primaryStage.setTitle("Code");
        textField.setAlignment(Pos.TOP_RIGHT);
        textField.setDisable(true);
        StackPane lo1 = new StackPane();
        lo1.getChildren().add(textField);
        GridPane lo2 = new GridPane();                      
        Image image1 = new Image(getClass().getResourceAsStream("on.png"));
        Image image2 = new Image(getClass().getResourceAsStream("off.png"));
        int k = 1;
        for(int i=0; i <5; i++){
            for(int j=0; j <5; j++){
                ContextMenu ct = new ContextMenu();
                MenuItem it1 = new MenuItem("CheckIn");
                MenuItem it2 = new MenuItem("CheckOut");
                MenuItem it3 = new MenuItem("Soda $1");
                MenuItem it4 = new MenuItem("Water $1.5");
                MenuItem it5 = new MenuItem("Juice $3");
                ct.getItems().addAll(it1,it2, new SeparatorMenuItem(), it3,it4,it5);                
                Time entry = new Time(nullTime, nullTime,0);
                timeList.add(entry);                          
                Button btn = new Button(String.format("%03d", k));
                btn.setPrefWidth(120);
                btn.setPrefHeight(120);
                btn.setGraphic(new ImageView(image2));
                btn.setContentDisplay(ContentDisplay.TOP);
                btn.setContextMenu(ct);
                String k3 = toNumber(k);
                it2.setDisable(true);
                it3.setDisable(true);
                it4.setDisable(true);
                it5.setDisable(true);
                it1.setOnAction(e -> {
                    toLog = "check IN for " + k3+". Start at "+ this.getTime();
                    hf.addRe(toLog);
                    refreshArea();
                    btn.setGraphic(new ImageView(image1));
                    timeList.get(Integer.parseInt(k3)-1).setsTime(this.getTime());
                    resLog();
                    refreshArea();
                    income();
                    it2.setDisable(false);
                    it3.setDisable(false);
                    it4.setDisable(false);
                    it5.setDisable(false);
                    //System.out.println(timeList.get(Integer.parseInt(k3)-1).getsTime());
                 });
                it2.setOnAction(e -> {                    
                    btn.setGraphic(new ImageView(image2));
                    timeList.get(Integer.parseInt(k3)-1).seteTime(this.getTime());
                    String timeDura = timeDuration(timeList.get(Integer.parseInt(k3)-1).getsTime(),timeList.get(Integer.parseInt(k3)-1).geteTime());
                    int timeDiff = timeDifferent(timeList.get(Integer.parseInt(k3)-1).getsTime(),timeList.get(Integer.parseInt(k3)-1).geteTime());
                    timeList.get(Integer.parseInt(k3)-1).setTotal(price.timeCharge(timeDiff)+timeList.get(Integer.parseInt(k3)-1).getTotal());
                    saveLog();                    
                    toLog = "check OUT for " + k3+". End at "+ this.getTime() +". Total time is "+ timeDura;                     
                    hf.addRe(toLog);
                    toLog = "Total charge for "+ k3 +" will be ................$" + (timeList.get(Integer.parseInt(k3)-1).getTotal());
                    hf.addRe(toLog);
                    refreshArea();
                    income();
                    it2.setDisable(true);
                    it3.setDisable(true);
                    it4.setDisable(true);
                    it5.setDisable(true);
                 });
                it3.setOnAction(e -> {
                    double ntotal;
                    ntotal = timeList.get(Integer.parseInt(k3)-1).getTotal()+1;
                    timeList.get(Integer.parseInt(k3)-1).setTotal(ntotal);
                    toLog = "one SODA for " + k3 + " $1";
                    hf.addRe(toLog);
                    refreshArea();
                });
                it4.setOnAction(e -> {
                    double ntotal;
                    ntotal = timeList.get(Integer.parseInt(k3)-1).getTotal()+1.5;
                    timeList.get(Integer.parseInt(k3)-1).setTotal(ntotal);
                    toLog = "one WATER for " + k3 + " $1.5";
                    hf.addRe(toLog);
                    refreshArea();
                });
                it5.setOnAction(e -> {
                    double ntotal;
                    ntotal = timeList.get(Integer.parseInt(k3)-1).getTotal()+3;
                    timeList.get(Integer.parseInt(k3)-1).setTotal(ntotal);
                    toLog = "one JUICE for " + k3 + " $3";
                    hf.addRe(toLog);
                    refreshArea();
                });
                lo2.add(btn,j,i,1,1);
                this.initialize();
                btn.setOnAction(e -> {                 
                    System.out.println(k3);
                });                
             k++;  
            }
        }
        saveLog();
        VBox vb = new VBox();
        vb.getChildren().addAll(textField,lo2,textField2,textArea);
        Scene scene = new Scene(vb,680,950);
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    public void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            date = cal.get(Calendar.DATE);
            textField.setText(month +"-"+ date + "-" + year + "  " + hour + ":" + (minute) + ":" + second);
            }),
            new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
}
    public String toNumber(int k){
        String k2 = String.valueOf(k);
        return k2;
    }
    public String getTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }
    public String timeDuration(String arg1,String arg2){       
        String[] sp1 = arg1.split(":");
        String[] sp2 = arg2.split(":");
        int sec1 =  Integer.parseInt(sp1[0])*3600 + Integer.parseInt(sp1[1])* 60 + Integer.parseInt(sp1[2]);
        int sec2 =  Integer.parseInt(sp2[0])*3600 + Integer.parseInt(sp2[1])* 60 + Integer.parseInt(sp2[2]);
        int diff = sec2 - sec1;
        LocalTime timeOfDay = LocalTime.ofSecondOfDay(diff);
        String time = timeOfDay.toString();
        return time;        
    }
    public int timeDifferent(String arg1,String arg2){       
        String[] sp1 = arg1.split(":");
        String[] sp2 = arg2.split(":");
        int sec1 =  Integer.parseInt(sp1[0])*3600 + Integer.parseInt(sp1[1])* 60 + Integer.parseInt(sp1[2]);
        int sec2 =  Integer.parseInt(sp2[0])*3600 + Integer.parseInt(sp2[1])* 60 + Integer.parseInt(sp2[2]);
        int diff = sec2 - sec1;       
        return diff;        
    }
    
    public void saveLog(){        
        df.openFile();
        for (int i=0; i<timeList.size();i++){
            df.addRecords( timeList.get(i).getsTime(), timeList.get(i).geteTime(), timeList.get(i).getTotal());
        }
        df.closeFile();
    }
    
     public void resLog(){        
        df.openFile();
        for (int i=0; i<timeList.size();i++){
            df.addRecords( timeList.get(i).getsTime(), nullTime, 0.00);
        }
        df.closeFile();
    }
     
    public void refreshArea(){
        textArea.setText("Log......\n");
        try {
        Scanner s = new Scanner(new File("hisLog.txt"));
        while (s.hasNextLine()) {            
            textArea.appendText(s.nextLine() + "\n");
        }
        } catch (FileNotFoundException ex) {
        System.err.println(ex);
        }
    }
    public void view(){
        for (int i=0; i<timeList.size();i++){
            timeList.get(i).viewLog();
        }
    }
    public void income(){
        double income = 0;
        for (int i=0; i<timeList.size();i++){
            income = income + timeList.get(i).getTotal();            
        }
        textField2.setText("today income is $"+income);
        textField2.setAlignment(Pos.TOP_RIGHT);
    }
}