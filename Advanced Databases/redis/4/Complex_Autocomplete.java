package ies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class Complex_Autocomplete
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        String key_hashmap = "names";
        Jedis jedis = new Jedis();
        Scanner sc = new Scanner(new File("/home/andrebutuc/Desktop/CBD/G_1/nomes-pt-2021.csv"));
        String[] line_content;
        while (sc.hasNextLine()){
            line_content = sc.nextLine().split(";");
            jedis.hset(key_hashmap, line_content[0], line_content[1]);
        }
        sc.close();
        sc = new Scanner(System.in);
        String answer = "";
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        ArrayList<Integer> values = new ArrayList<Integer>();
        while (!answer.equals("quit")){
            System.out.print("Search for (Enter 'quit' to quit): ");
            answer = sc.next();
            if (!answer.equals("quit")){
                for (String name: jedis.hkeys(key_hashmap)){
                    if (answer.length() > name.length())
                        continue;
                    
                    else {
                        if (answer.equals(name.substring(0, answer.length()))){
                            result.put(name, Integer.parseInt(jedis.hget(key_hashmap, name)));
                        }
                    }
                }

                for (Integer value : result.values()){
                    values.add(value);;
                }

                Collections.sort(values, Collections.reverseOrder());
                
                for (Integer value: values){
                    for (String key: result.keySet()){
                        if (result.get(key) == value){
                            System.out.println(key);
                            result.put(key, -1);
                            break;
                        }
                    }
                }
                result.clear();
            }
        }
        jedis.del(key_hashmap);
        sc.close();
        jedis.close();
    }
}
