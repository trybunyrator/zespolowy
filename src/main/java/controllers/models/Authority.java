package controllers.models;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    private String username;
    private String authority;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    public Authority() {
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
