public class Time {
    private String sTime;
    private String eTime;
    private double total;
    
    public Time(String sTime, String eTime, double total){
        this.eTime = eTime;
        this.sTime = sTime;
        this.total = total;
    }
    public String geteTime(){
        return this.eTime;
    }
    public String seteTime(String eTime){
        return (this.eTime = eTime);
    }
    public String getsTime(){
        return this.sTime;
    }
    public String setsTime(String sTime){
        return (this.sTime = sTime);
    }
    public double getTotal(){
        return this.total;
    }
    public double setTotal(double total){
        return (this.total = total);
    }
    public void viewLog(){
        System.out.println("start time "+sTime);
        System.out.println("end time "+eTime);
        System.out.println("subTotal "+total);
    }
    
}
