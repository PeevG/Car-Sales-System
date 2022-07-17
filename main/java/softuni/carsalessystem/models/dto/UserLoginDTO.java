package softuni.carsalessystem.models.dto;

public class UserLoginDTO {
    String username;
    String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
