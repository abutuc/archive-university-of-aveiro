
public class Catalogo
{
    private Algoritmo algoritmo;
    
    public Catalogo(Algoritmo algoritmo)
    {
        this.algoritmo = algoritmo;
    }
    
    public void setAlgoritmo(Algoritmo algoritmo)
    {
        this.algoritmo = algoritmo;
    }
    
    public Telemovel[] ordenar(Telemovel[] lista, String atributo)
    {
        return algoritmo.ordenar(lista, atributo);
    }
}
