public class Flight {
    private String code;
    private Aviao plane;

    public Flight(String code, Aviao plane){
        this.code = code;
        this.plane = plane;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setPlane(Aviao plane) {
        this.plane = plane;
    }

    public String getCode() {
        return code;
    }
    public Aviao getPlane() {
        return plane;
    }


    @Override
    public String toString() {
        String str = "";
        if (plane.getLugares_executivos() == null){
            str = "Código de voo " + code + ". Lugares disponíveis: " + plane.getLugares_turisticos_disponiveis() + " lugares em classe Turística.";
        }
        else {
            str = "Código de voo " + code + ". Lugares disponíveis: " + plane.getLugares_executivos_disponiveis() + " lugares em classe Executiva; " + 
            plane.getLugares_turisticos_disponiveis() + " lugares em classe Turística.";
        }
        return str;
    }

}
