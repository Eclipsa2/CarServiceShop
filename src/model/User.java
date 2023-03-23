package model;

public class User
{
    private String username;
    private String password;

    @Override
    public boolean equals(Object o)
    {
        return (this.username.equals(((User)o).getUsername())
                &&
                this.password.equals(((User)o).getPassword()));
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
