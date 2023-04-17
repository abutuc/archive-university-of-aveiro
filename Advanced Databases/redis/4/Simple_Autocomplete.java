package ies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class Simple_Autocomplete
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        String key_list = "names";
        Jedis jedis = new Jedis();
        Scanner sc = new Scanner(new File("/home/andrebutuc/Desktop/CBD/G_1/names.txt"));
        String line = "";
        while (sc.hasNextLine()){
            line = sc.nextLine();
            jedis.rpush(key_list, line);
        }
        sc.close();
        sc = new Scanner(System.in);
        String answer = "";
        ArrayList<String> result = new ArrayList<String>();
        while (!answer.equals("quit")){
            System.out.print("Search for (Enter 'quit' to quit): ");
            answer = sc.next();
            if (!answer.equals("quit")){
                for (String name: jedis.lrange(key_list, 0, -1)){
                    if (answer.length() > name.length())
                        continue;
                    
                    else {
                        if (answer.equals(name.substring(0, answer.length()))){
                            result.add(name);
                        }
                    }
                }
                for (String name: result)
                    System.out.println(name);
                    
                result.clear();
            }
        }
        jedis.del(key_list);
        sc.close();
        jedis.close();
    }
}
