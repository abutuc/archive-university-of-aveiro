package lab3;

import java.util.Objects;

public class Reserva {
    /*atributos da reserva*/
    private String classeT; //turistica
    private String classeE; //executiva
    private int numT;
    private int numE;

    /*construtores*/
    public Reserva(){}

    public Reserva(String classeT, String classeE, int numT, int numE) {
        this.classeT = classeT;
        this.classeE = classeE;
        this.numT = numT;
        this.numE = numE;
    }

    public Reserva(String classeT, int numT ){
        this.classeT = classeT;
        this.numT = numT;
    }

    /*getters e setters*/
    public String getClasseT() {
        return classeT;
    }

    public void setClasseT(String classeT) {
        this.classeT = classeT;
    }

    public String getClasseE() {
        return classeE;
    }

    public void setClasseE(String classeE) {
        this.classeE = classeE;
    }

    public int getNumT() {
        return numT;
    }

    public void setNumT(int numT) {
        this.numT = numT;
    }

    public int getNumE() {
        return numE;
    }

    public void setNumE(int numE) {
        this.numE = numE;
    }

    /*hashCode e equals*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return getNumT() == reserva.getNumT() && getNumE() == reserva.getNumE() && Objects.equals(getClasseT(), reserva.getClasseT()) && Objects.equals(getClasseE(), reserva.getClasseE());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClasseT(), getClasseE(), getNumT(), getNumE());
    }

    /*toString*/

    @Override
    public String toString() {
        return "Reserva{" +
                "classeT='" + classeT + '\'' +
                ", classeE='" + classeE + '\'' +
                ", numT=" + numT +
                ", numE=" + numE +
                '}';
    }
}
