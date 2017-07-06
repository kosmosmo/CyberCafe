public class Price {
    public Price(){    
    }
    public double timeCharge(int arg){
        double charge = 0;
        if (arg>60){
            charge = (arg - 60) *1.5 + 120;
        }
        else{
            charge = arg *2;
        }
        return charge;
    }
}
