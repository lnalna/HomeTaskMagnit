package magnit.test.model;



public class DatabaseProperties {

    public static final DatabaseProperties DATABASE_PROPERTIES = new DatabaseProperties();

    public static final int BATCH_SIZE = 25000;
    public static final int POOL_SIZE = 5000;

    private String dataBaseDriver;
    private String url;
    private String userName;
    private String password;
    private int entryCount;

    public String getDataBaseDriver() {
        return dataBaseDriver;
    }

    public void setDataBaseDriver(String dataBaseDriver) {
        this.dataBaseDriver = dataBaseDriver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    @Override
    public String toString() {
        return "DatabaseProperties{" +
                "dataBaseDriver='" + dataBaseDriver + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", entryCount='" + entryCount + '\'' +
                '}';
    }
}
