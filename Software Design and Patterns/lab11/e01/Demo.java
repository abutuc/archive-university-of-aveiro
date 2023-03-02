
public class Demo
{
    public static void main(String[] args)
    {
        Telemovel[] lista = {
            new Telemovel("Apple A14 Bionic", 999.99, 8192, 10),
            new Telemovel("Helio G90T", 699.99, 4096, 15),
            new Telemovel("Kirin 9000", 899.99, 4096, 40)};
        
        Catalogo c = new Catalogo(new BubbleSort());
        Telemovel[] listaA = c.ordenar(lista, "processador");
        c.setAlgoritmo(new MergeSort());
        Telemovel[] listaB = c.ordenar(lista, "preco");
        c.setAlgoritmo(new QuickSort());
        Telemovel[] listaC = c.ordenar(lista, "memoria");   
    }
}
