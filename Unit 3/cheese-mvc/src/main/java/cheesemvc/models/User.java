package cheesemvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min = 5, max = 15)
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull(message = "Passwords do not match.")
    private String verifyPassword;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
        this.username = "";
        this.email = "";
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    private void checkPassword() {
        if (!this.getPassword().equals(this.getVerifyPassword()) && this.getPassword() != null && this.getVerifyPassword() != null) {
            this.verifyPassword = null;
        }
    }
}
