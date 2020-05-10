package dao;

import models.Departments;
import models.Users;
import models.News;

import java.util.List;

public interface DepartmentsDao {
    //create
    void add(Departments departments);

    //list and read all departments
    List<Departments>getAllDepartments();

    //M:M r-ship
    void addDptToUsers(Departments departments, Users users);
    List<Users> getAllUsersBelongingToDepartment(int user_id);

    //find and get news of a department by id
    Departments findById(int id);
    List<News>getAllNews(int dpt_id);

    //Delete
    void deleteById(int id);
    void clearAll();
}