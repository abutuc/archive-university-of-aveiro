package cbd.lab3;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraClient {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static void main(String[] args) {
        CassandraConnector client = new CassandraConnector();
        client.connect("127.0.0.1", 9042);
        Session session = client.getSession();
        System.out.println("Connected to cluster: " + session.getCluster().getClusterName());
        session.execute("USE cbd_103530_ex2");
        System.out.println("Connected to keyspace: " + session.getLoggedKeyspace());
        
        // Commented code for testing purposes

        // Insert a new user Example
        //insertUser(session, "java_driver", "rumo_ao_vinte", "take.it.easy@ua.pt");
        //System.out.println(session.execute("SELECT * FROM users").all());

        // Update a user Example
        //System.out.println(session.execute("SELECT * FROM users WHERE user_id=11").all());
        //updateUser(session, null, null, "thisismyemail@ua.pt", 11);
        //System.out.println(session.execute("SELECT * FROM users WHERE user_id=11").all());

        // Select a user Example
        //System.out.println((selectUser(session, 0)).all());

        // Query EX:D:4
        //System.out.println("Query EX:D:4");
        //System.out.println(ex_D_4(session, 1, 4).all());
    
        // Query EX:D:7
        //System.out.println("Query EX:D:7");
        //System.out.println(ex_D_7(session, 7));

        // Query EX:D:12
        //System.out.println("Query EX:D:12");
        //System.out.println(ex_D_12(session, 1));

        // Query EX:D:13
        //System.out.println("Query EX:D:13");
        //System.out.println(ex_D_13(session, 3));
        
        client.close();
    }


    private static void insertUser(Session session, String name, String username, String email) {
        int max_id = session.execute("SELECT MAX(user_id) FROM users").one().getInt(0);
        max_id++;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(sdf.format(timestamp));
        session.execute("INSERT INTO users (user_id, email, name, registered_on, username) VALUES (" + max_id + ", '" + email + "', '" + name + "','" + (sdf.format(timestamp)).toString() + "', '" + username + "');");
    }

    public static void updateUser(Session session, String name, String username, String email, int user_id) {
        if (name != null) {
            session.execute("UPDATE users SET name = '" + name + "' WHERE user_id = " + user_id + ";");
        }
        if (username != null) {
            session.execute("UPDATE users SET username = '" + username + "' WHERE user_id = " + user_id + ";");
        }
        if (email != null) {
            session.execute("UPDATE users SET email = '" + email + "' WHERE user_id = " + user_id + ";");
        }
    }

    public static ResultSet selectUser(Session session, int user_id) {
        if (user_id != 0) {
            return session.execute("SELECT * FROM users WHERE user_id = " + user_id + ";");
        }
        else {
            return session.execute("SELECT * FROM users;");
        }
    }

    public static ResultSet ex_D_4(Session session, int video_id, int user_id) {
        return session.execute("SELECT * FROM events_by_video_user WHERE video_id = " + video_id + " AND user_id = " + user_id + " order by occured_on desc limit 5;");
    }

    public static List<Row> ex_D_7(Session session, int video_id){
        return session.execute("SELECT * FROM videos_followers where video_id = " + video_id + ";").all();
    }

    public static List<Row> ex_D_12(Session session, int video_id){
        return session.execute("SELECT max(rating) FROM ratings where video_id = " + video_id + ";").all();
    }

    public static List<Row> ex_D_13(Session session, int author_id){
        return session.execute("SELECT * FROM videos_by_author where author_id = " + author_id + " order by shared_on desc limit 1;").all();
    }


}
