package dao;

import models.Departments;
import models.Users;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao {
    private final Sql2o sql2o;
    public  Sql2oDepartmentsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Departments departments) {
        String sql = "INSERT INTO departments (name, description, user) VALUES (:name, :description, :user)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(departments)
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Departments> getAllDepartments() {
        String sql = "SELECT * FROM departments";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql).executeAndFetch(Departments.class);
        }
    }

    //M:M
    @Override
    public void addDptToUsers(Departments departments, Users users) {
        String sql = "INSERT INTO departments_users(dpt_id, user_id) VALUES (:dpt_id, :user_id)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("dpt_id", departments.getId())
                    .addParameter("user_id", users.getId())
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Users> getAllUsersBelongingToDepartment(int dpt_id) {
        ArrayList<Users> allUsers = new ArrayList<>();
        String joinQuery = "SELECT user_id FROM departments_users WHERE dpt_id =:dpt_id";
        try(Connection conn = sql2o.open()){
            List<Integer> allUsersIds = conn.createQuery(joinQuery)
                    .addParameter("dpt_id", dpt_id)
                    .executeAndFetch(Integer.class);
            for(Integer user_id: allUsersIds){
                String usersQuery = "SELECT * FROM users WHERE id = :user_id";
                allUsers.add(conn.createQuery(usersQuery)
                        .addParameter("user_id", user_id)
                        .executeAndFetchFirst(Users.class));
            }
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return allUsers;
    }

    @Override
    public Departments findById(int id) {
        String sql = "SELECT * FROM departments WHERE id=:id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Departments.class);
        }
    }

    @Override
    public List<News> getAllNews(int dpt_id) {
        String sql = "SELECT * FROM news WHERE dpt_id=:dpt_id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql).addParameter("dpt_id", dpt_id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String deleteJoin = "DELETE from departments_users WHERE dpt_id = :dpt_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("dpt_id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

}