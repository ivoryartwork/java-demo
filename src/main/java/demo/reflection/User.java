package demo.reflection;

/**
 * Created by Yaochao on 2016/2/24.
 */
public class User {

    public String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
