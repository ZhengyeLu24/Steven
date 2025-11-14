class User{
    private String userName;
    private String password;
    // "Watchlist" is another class, watchlist is an attribute of User
    private Watchlist watchlist;
    // "History" is another class, history is an attribute of User
    private History history;

    public User(){}
    public User(String userName, String password, String watchlist, String history) {
        this.userName = userName;
        this.password = password;
        this.watchlist = new Watchlist();
        this.history = new History();
    }

    public String getUserName() {return userName;}
    // This is a method,which user can change their username
    public String setUserName(String userName) {
        this.userName = userName;
        return this.userName;
    }

    public String getPassword() {return password;}
    // This is a method,which user can change their username
    public String setPassword(String password) {
        this.password = password;
        return this.password;
    }

    public Watchlist getWatchlist() {return watchlist;}
    // This is a method that user can change their watchlist.
    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public History getHistory() {return history;}
    // This is a method that user can change their watchlist.
    public void setHistory(History history) {
        this.history = history;
    }
}
