package models;

public class News{
private int id;
private String content;
private int dpt_id;
private String name;

public News(String content, int dpt_id, String name){
    this.content =content;
    this.dpt_id = dpt_id;
    this.name = name;
}

public int getId(){
    return id;
}

public void setId(int id){
    this.id = id;
}

public String getContent(){
    return content;
}

public void setContent(String content){
    this.content = content;
}

public int getDpt_id(){
    return dpt_id;
}

public void setDpt_id(int dpt_id){
    this.dpt_id = dpt_id;
}

public String getName(){
    return name;
}

public void setName(String name){
    this.name = name;
}
}