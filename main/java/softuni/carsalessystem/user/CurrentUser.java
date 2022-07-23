package softuni.carsalessystem.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import softuni.carsalessystem.enums.UserRoleEnum;
import softuni.carsalessystem.models.entities.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private boolean isLoggedIn;
    private String username;
    private String firstName;
    private String lastName;
    private List<UserRoleEnum> roles = new ArrayList<>();

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public CurrentUser addRole(UserRoleEnum role) {
        roles.add(role);
        return this;
    }

    public CurrentUser clearRoles(){
        roles.clear();
        return this;
    }
    public boolean isAdmin(){
        return roles.contains(UserRoleEnum.ADMIN);
    }

    public void cleanUserInfo(){
       this.setLoggedIn(false);
       this.setUsername(null);
       this.setFirstName(null);
       this.setLastName(null);
       this.clearRoles();
    }
}
