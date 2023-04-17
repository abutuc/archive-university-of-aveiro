package redis;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;


public class MessageSystem
{
    public static String USERS_SET_KEY = "users";
    public static Jedis jedis = new Jedis();

    public static boolean addUser(String user){
        jedis.sadd(USERS_SET_KEY, user);
        System.out.println(user + " is alive!");
        return true;
    }

    public static boolean removeUser(String user){
        jedis.srem(USERS_SET_KEY, user);
        System.out.println(user + " left the premisses :(");
        return true;
    }

    // User 1 follows User 2
    public static boolean followUser(String user1, String user2){
        jedis.sadd(user2 + "Followers", user1);
        System.out.println(user1 + " is now following " + user2 + "! ");
        return true;
    }

    // User 1 unfollows User 2
    public static boolean unfollowUser(String user1, String user2){
        jedis.srem(user2 + "Followers",  user1);
        System.out.println(user2 + " unfollowed " + user1 + "!");
        return true;
    }

    public static boolean storeMessage(String user, String message){
        jedis.rpush(user+"MessageLog", message);
        notify(user, message);
        return true;
    }

    // User 2 is asking for User 1 messages
    public static String getUserMessages(String user1, String user2){
        String s_return;
        if (isFollower(user1, user2)){
            List<String> messages = jedis.lrange(user1+"MessageLog", 0, -1);
            s_return = user1 + "' Messages\n";
            for (int i = 0; i<messages.size(); i++){
            s_return += "Message " + i+1 + ": " + messages.get(i) + "\n";
            }
        }
        else {
            s_return = user2 + " isn't following " + user2;
        }
        return s_return;
    }

    // Is user2 following user1?
    public static boolean isFollower(String user1, String user2){
        return jedis.sismember(user1+"Followers", user2);
    }

    public static Set<String> getUsers(){
        return jedis.smembers(USERS_SET_KEY);
    }
    public static Set<String> getFollowers(String user){
        return jedis.smembers(user+"Followers");
    }

    public static boolean notify(String user, String message){
        Set<String> followers = getFollowers(user);
        for (String follower: followers){
            System.out.println(user + " to " + follower + ": " + message);
        }
        return true;
    }

    public static boolean quit(){
        System.out.println("System is exiting.");
        jedis.flushDB();
        return true;
    }
    
    public static void main( String[] args )
    {
        System.out.println("MESSAGE EXCHANGE SYSTEM");
        System.out.println("\n----------TESTING ADDING USERS -------\n");
        addUser("André");
        addUser("Gabriel");

        System.out.println("\n----------TESTING FOLLOWING USERS -------\n");
        followUser("André", "Gabriel");

        System.out.println("\n----------TESTING SENDING MESSAGE USERS -------\n");
        storeMessage("Gabriel", "Hi followers!");
        storeMessage("André", "Nobody will see this.");
        storeMessage("André", "Nobody will see this v2.");


        System.out.println("\n----------BASIC INFO -------\n");
        System.out.println(getUserMessages("André", "Gabriel"));
        System.out.println();
        System.out.println(getUserMessages("Gabriel", "André"));
        System.out.println();
        followUser("Gabriel", "André");
        System.out.println(getUserMessages("André", "Gabriel"));
        System.out.println();
        unfollowUser("André", "Gabriel");

        quit();
    }
}
