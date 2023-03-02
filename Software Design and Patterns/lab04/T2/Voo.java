package lab3;

import java.util.Objects;

public class Voo {
    /*atributos*/
    private String codigo_voo;
    private String classe_turistica;
    private String classe_executiva;

    /*construtores*/
    public Voo(){}

    public Voo(String codigo_voo, String classe_turistica, String classe_executiva){
        this.codigo_voo = codigo_voo;
        this.classe_turistica = classe_turistica;
        this.classe_executiva = classe_executiva;
    }

    //classe executiva opcional
    public Voo(String codigo_voo, String classe_turistica){
        this.codigo_voo = codigo_voo;
        this.classe_turistica = classe_turistica;
    }

    /*getters e setters*/
    public String getCodigo_voo() {
        return codigo_voo;
    }

    public void setCodigo_voo(String codigo_voo) {
        this.codigo_voo = codigo_voo;
    }

    public String getClasse_turistica() {
        return classe_turistica;
    }

    public void setClasse_turistica(String classe_turistica) {
        this.classe_turistica = classe_turistica;
    }

    public String getClasse_executiva() {
        return classe_executiva;
    }

    public void setClasse_executiva(String classe_executiva) {
        this.classe_executiva = classe_executiva;
    }

    /*equals e hashcode*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voo)) return false;
        Voo voo = (Voo) o;
        return getCodigo_voo() == voo.getCodigo_voo() && getClasse_turistica() == voo.getClasse_turistica() && getClasse_executiva() == voo.getClasse_executiva();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo_voo(), getClasse_turistica(), getClasse_executiva());
    }

    /*toString*/
    @Override
    public String toString() {
        return "Voo{" +
                "codigo_voo=" + codigo_voo +
                ", classe_turistica=" + classe_turistica +
                ", classe_executiva=" + classe_executiva +
                '}';
    }
}
