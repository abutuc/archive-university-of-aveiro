package lab01.exe2;

public class WSGenerator {
	
	/* Program accepts 3 argument which are:
	 * arg[0] -> file containing list of words
	 * arg[1] -> size of puzzle
	 * arg[2] -> name to be given to solution file */
	
    public static void main(String[] args){
    	
    	HelperGenerator generator = new HelperGenerator(args[0], Integer.parseInt(args[1]), args[2]);
        
        generator.generateGame();
    }
}
