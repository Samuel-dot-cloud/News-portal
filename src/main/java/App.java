import com.google.gson.Gson;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oUsersDao;
import dao.Sql2oNewsDao;
import exceptions.ApiException;

import models.Departments;
import models.Users;
import models.News;
import org.sql2o.Sql2o;

import org.sql2o.Connection;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartmentsDao departmentsDao;
        Sql2oNewsDao newsDao;
        Sql2oUsersDao usersDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/news_api";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");

        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        //create
        post("/departments/new", "application/json", (req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentsDao.add(departments);
            res.status(201);
            res.type("application/json");
            return gson.toJson(departmentsDao.getAllDepartments());
        });
        //read all

        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(departmentsDao.getAllDepartments());//send it back to be displayed
        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int dpt_id = Integer.parseInt(req.params("id"));
            Departments departmentsToFind = departmentsDao.findById(dpt_id);
            if (departmentsToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            res.type("application/json");
            return gson.toJson(departmentsDao.findById(dpt_id));
        });

        //news : dpt

        //Add news to department
        post("/departments/:id/news/new","application/json",(request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            News news = gson.fromJson(request.body(),News.class);
            news.setId(id);

            newsDao.add(news);
            response.type("application/json");
            response.status(201);
            return gson.toJson(news);
        });


        // access news for a certain department
        get("/departments/:id/dptNews", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            Departments departmentToFind = departmentsDao.findById(id);
            return gson.toJson(newsDao.getAllNews());
        });


        post("/departments/:dpt_id/users/new", "application/json", (req, res) -> {
            int dpt_id = Integer.parseInt(req.params("dpt_id"));
            Users users = gson.fromJson(req.body(), Users.class);
            users.setId(dpt_id);
            usersDao.add(users);
            res.status(201);
            res.type("application/json");
            return gson.toJson(users);
        });


        //Add a user
        post("/users/new", "application/json", (request, response) -> {
            Users users = gson.fromJson(request.body(), Users.class);
            usersDao.add(users);
            response.type("application/json");
            response.status(201);
            return gson.toJson(users);
        });


//        access all users
        get("/users", "application/json", (request, response) ->
        {
            response.type("application/json");
            return gson.toJson(usersDao.getAllUsers());
        });


//        Assign a department to a user
        post("/users/user_id/departments/:dpt_id","application/json",(request, response) -> {
            int user_id = Integer.parseInt(request.params("user_id"));
            int dpt_id = Integer.parseInt(request.params("dpt_id"));
            Users userFound = usersDao.findById(user_id);
            Departments dptFound = departmentsDao.findById(dpt_id);

            if (dptFound != null && userFound!= null){
                departmentsDao.addDptToUsers(dptFound,userFound);
                response.type("application/json");
                response.status(201);
                return gson.toJson(String.format("Users '%s' and Department '%s' successfully created",userFound.getName(), dptFound.getName()));
            }
            else {
                throw new ApiException(404, String.format("Unfortunately the User or Department has  not been found"));
            }
        });



        get("/users/:user_id/departments","application/json",(request, response) -> {
            int user_id = Integer.parseInt(request.params("user_id"));
            Users usersTofind = usersDao.findById(user_id);

            if (usersTofind == null){
                throw new Exception("User with that id does not exist");
            }else if(usersDao.getAllDptBelongingToUsers(user_id).size() == 0){
                return "{\"message\":\"Opps!the User is not associated with any of the departments\"}";
            }else {
                return gson.toJson(usersDao.getAllDptBelongingToUsers(user_id));
            }
        });


//        Add news
        post("/news/new","application/json",(request, response) -> {
            News news = gson.fromJson(request.body(),News.class);
            newsDao.add(news);
            response.type("application/json");
            response.status(201);
            return gson.toJson(news);
        });


//        Read all news
        get("/news","application/json",(request, response) -> {
            response.type("application/json");
            return gson.toJson(newsDao.getAllNews());
        });


// CUSTOM FILTER
        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });


        //      FILTERS
        after((req, res) ->{
            res.type("application/json");
        });

    }
}

/// USERS REFER TO ACTUAL EMPLOYEES!!! NOTE TO SELF