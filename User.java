import java.io.*;
import java.util.*;

public class User {
    private String userName;
    private String password;
    // "Watchlist" is another class, watchlist is an attribute of User
    private Watchlist watchlist;
    // "History" is another class, history is an attribute of User
    private History history;

    // Static Variable : Store all users
    private static Map<String, User> allUsers = new HashMap<>();

    public User() {
    }

    public User(String userName, String password, String watchlist, String history) {
        this.userName = userName;
        this.password = password;
        this.watchlist = new Watchlist();
        this.history = new History();
    }

    public String getUserName() {
        return userName;
    }

    // This is a method,which user can change their username
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    // This is a method,which user can change their username
    public void setPassword(String password) {
        this.password = password;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    // This is a method that user can change their watchlist.
    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public History getHistory() {
        return history;
    }

    // This is a method that user can change their watchlist.
    public void setHistory(History history) {
        this.history = history;
    }


    public static HashMap<String, User> loadUsers(String path) {
        HashMap<String, User> users = new HashMap<>(); // Create a new map to store all users
        File file = new File(path);
        if (!file.exists()) {return users};
        BufferedReader br = null; // Declare a "Reader" variable,it will be initialized in "try"
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            boolean first = true; // this is used to skip "username,password,watchlist,history"
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                String[] parts = line.split(",", -1); //"-1" is used to save the blank field
                if (parts.length < 4) {continue};
                String username = parts[0].trim();
                String password = parts[1].trim();
                String watchlistCsv = parts[2].trim();
                String historyCsv = parts[3].trim();

                User user = new User(username, password);// Create User's object

                Watchlist wl = new Watchlist();
                wl.fromCsv(watchlistCsv); //eg: Add "M008;M015;M071;M048;M056" to the list

                History his = new History();
                his.fromCsv(historyCsv);

                user.setWatchlist(wl);
                user.setHistory(his);

                users.put(username, user); // Store in the map
            }
        } catch (IOException e) {
            System.out.println("[WARN] Failed to read users: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ignored) {
            }
        }
        return users;
    }

    // save to CSV
    public static boolean saveUsers(HashMap<String, User> users, String path) {
        File file = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("Username,Password,Watchlist,History");
            bw.newLine();
            for (User u : users.values()) {
                String wl = u.getWatchlist() != null ? u.getWatchlist().toCsv() : "";
                String his = u.getHistory() != null ? u.getHistory().toCsv() : "";
                // "toCsv"method (In class "Watchlist" and "History") makes the elements in "Watchlist"and "History" transfer to the String type
                bw.write(u.getUsername() + "," + u.getPassword() + "," + wl + "," + his);
                bw.newLine();
            }
            bw.flush();
            return true;
        } catch (IOException e) {
            System.out.println("[WARN] Failed to save users: " + e.getMessage());
            return false;
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException ignored) {
            }
        }
    }
}
