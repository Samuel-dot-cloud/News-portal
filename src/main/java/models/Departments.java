package models;

public class Departments{
    private String name;
    private String description;
private int user;
private int id;

public Departments(String name, String description, int user){
    this.name = name;
    this.description =description;
    this.user =user;
}

public String getName(){
    return name;
}

public void setName(String name){
    this.name = name;
}

public String getDescription(){
    return description;
}

public void setDescription(String description){
    this.description = description;
}

public int getUser(){
    return user;
}

public void setUser(int user){
    this.user = user;
}

public int getId(){
    return id;
}

public void setId(int id){
    this.id = id;
}
}