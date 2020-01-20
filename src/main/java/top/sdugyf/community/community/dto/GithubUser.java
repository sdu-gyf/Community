package top.sdugyf.community.community.dto;

public class GithubUser {
    private String login;
    private  Long id;
    private String bio;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
