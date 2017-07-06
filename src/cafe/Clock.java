
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class Clock {
private Label time;

private int minute;
private int hour;
private int second;


public void initialize() {

    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);
        //System.out.println(hour + ":" + (minute) + ":" + second);
        time.setText(hour + ":" + (minute) + ":" + second);
    }),
         new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
}
}