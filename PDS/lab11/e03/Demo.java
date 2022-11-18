import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {

        Livro[] livros = { 
            new Livro("Java Anti-Stress", "978-5-7150-3787-9", 2002, "Omodionah"),
            new Livro("A Guerra dos Padrões", "978-8-2845-2614-0", 2005, "Jorge Omel"),
            new Livro("A Procura da Luz", "978-8-7395-9515-8", 2006, "Khumatkli")
        };

        Scanner sc = new Scanner(System.in); // Fechado ao terminar o programa
        boolean sucesso = true;

        while (true) {
            if (sucesso) {
                printMenu(livros);
            }
            else {
                System.out.println("Operação não disponível");
            }
            System.out.println();
            System.out.print(">> ");
            
            String[] input = sc.nextLine().split(",");  // Supor que sintaxe é correta
            int index = Integer.parseInt(input[0]) - 1; // Supor que índice é válido
            int opera = Integer.parseInt(input[1]);
            
            switch (opera) {
                case 1: sucesso = livros[index].regista(); break;
                case 2: sucesso = livros[index].requisita(); break;
                case 3: sucesso = livros[index].devolve(); break;
                case 4: sucesso = livros[index].reserva(); break;
                case 5: sucesso = livros[index].cancela(); break;
                default: sucesso = false;
            }
        }
    }

    public static void printMenu(Livro[] livros) {
        System.out.println("*** Biblioteca ***");
        for (int i = 0; i < livros.length; i++) {
            System.out.println(String.format("%2d\t%-20s\t%-12s\t[%s]", 
                i + 1,
                livros[i].getTitulo(),
                livros[i].getAutor(),
                livros[i].getState())
            );
        }
        System.out.println(">> <livro>, <operação: (1)regista, (2)requisita, (3)devolve, (4)reserva, (5)cancela");
    }
}
