package models;

public class Users{
    private String name;
    private int id;
    private String position;
    private String role;
    private String details;

    public Users(String name, String position, String role, String details){
        this.name = name;
        this.position = position;
        this.role = role;
        this.details = details;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

}