package ua.tqs.belly;

public class Belly {
    int hours_waited;
    int cukes_ate;
    public Belly(){
        this.cukes_ate = 0;
        this.hours_waited = 0;
    }
    public void eat(int cukes) {
        this.cukes_ate += cukes;
    }

    public void wait(int hours){
        this.hours_waited += hours;
    }

    public void growl(){
        System.out.println("Grrrrr...");
    }
}
