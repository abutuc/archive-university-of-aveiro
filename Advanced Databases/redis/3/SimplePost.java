package redis;

import redis.clients.jedis.Jedis;

public class SimplePost {
    public static String USERS_LIST_KEY = "users_list"; // Key list for users' name
    public static String USER_HASHMAP_KEY = "user_hash"; // Key hashmap for users' name
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        // some users
        String[] users = {"Ana", "Pedro", "Maria", "Luis"};
        // jedis.del(USERS_KEY); // remove if exists to avoid wrong type
        for (int i = 0; i<users.length; i++){
            jedis.rpush(USERS_LIST_KEY, users[i]);
            jedis.hset(USER_HASHMAP_KEY, "user" + i, users[i]);
        }

        jedis.lrange(USERS_LIST_KEY, 0, -1).forEach(System.out::println);
        jedis.hgetAll(USER_HASHMAP_KEY).forEach((key, value) -> System.out.println(value));
        jedis.close();
    }
}
