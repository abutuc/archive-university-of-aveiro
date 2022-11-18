import java.io.IOException;
import java.nio.file.*;

public class Main{
    public static void main(String[] args) throws IOException {
      boolean recursive = false;
      Path path = null;
      if (args.length == 0){
        System.err.println("Error. Must pass arguments in script call.");
        System.exit(1);
      }
      else if (args.length == 1){
        System.out.println("here " + args[0]);
        path = Paths.get(args[0]);
      }
      else if (args.length == 2){
        System.out.println("here " + args[1]);
        path = Paths.get(args[1]);
        if (args[0].equals("-r")){
          recursive = true;
        }
        else{
          System.err.println("Invalid passed argument.");
          System.exit(1);
        }
      }
      else{
        System.err.println("Invalid number of arguments passed.");
        System.exit(1);
      }

      System.out.println(DirSize.calcDirSize(path, recursive));
    }
}