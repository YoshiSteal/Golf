import MG2D.*;
import MG2D.geometrie.*;

class Tir{
    double angle;
    double puissance;
    double t;
    
    public Tir(double angle, double puissance){
        
        this.angle = Math.toRadians(angle);
        this.puissance = puissance;
        this.t = 0;
        
    }
    //******* calcul position par rapport au temps *****************//
    public double getPosition(){
        
        return puissance*Math.cos(angle)*t;
    }
    //********* calcul hauteur par rapport au temps ************//
    public double getHauteur(){
        
        double poids = 4.0;
        return -0.5*9.81*poids*t*t+puissance*Math.sin(angle)*t;
    }
    //******* mise a jour de la date **************//
    public void upDate(double t){
        
        this.t=t;
    }
    
    public String toString(){
        
       return "La balle est à "+getHauteur()+" en hauteur "+getPosition()+" en position";
    }

    //************** test de la classe ************************//
   public static void main(String[] args){
        Tir t = new Tir(20.0, 100.0);
        for(int i = 0; i<20; i++){
            System.out.print(i);
            t.upDate(i);
            System.out.println(t);
        
        }
       
   }
    
}