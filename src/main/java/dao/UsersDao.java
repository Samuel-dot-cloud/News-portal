package dao;

import models.Departments;
import models.Users;

import java.util.List;

public interface UsersDao{
    //create
    void add(Users users);

    //Get and find all users ...by id
    List<Users>getAllUsers();
    Users findById(int id);

    //Add and get users to department
    void addUsersToDepartments(Users users, Departments departments);
    List<Departments>getAllDptBelongingToUsers(int user_id);
    //delete
    void deleteById(int id);

    //clear
    void clearAll();

}