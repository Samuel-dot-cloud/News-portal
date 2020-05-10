package dao;



import models.Departments;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUsersDao implements UsersDao {
    private final Sql2o sql2o;
    public Sql2oUsersDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Users users) {
        String sql = "INSERT INTO users(name, position, role, details) VALUES (:name, :position, :role, :details)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(users)
                    .executeUpdate()
                    .getKey();
            users.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public Users findById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Users.class);
        }
    }

    @Override
    public void addUsersToDepartments(Users users, Departments departments) {
        String sql = "INSERT INTO departments_users(dpt_id, user_id) VALUES (:dpt_id, :user_id)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("dpt_id", departments.getId())
                    .addParameter("user_id", users.getId())
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Departments> getAllDptBelongingToUsers(int user_id) {
        ArrayList<Departments> allDepartments = new ArrayList<>();
        String joinQuery = "SELECT dpt_id FROM departments_users WHERE user_id =:user_id";
        try(Connection conn = sql2o.open()){
            List<Integer> allDepartmentIds = conn.createQuery(joinQuery)
                    .addParameter("user_id", user_id)
                    .executeAndFetch(Integer.class);
            for(Integer dpt_id : allDepartmentIds){
                String departmentsQuery = "SELECT * FROM departments WHERE id=:dpt_id";
                allDepartments.add(conn.createQuery(departmentsQuery)
                        .addParameter("dpt_id", dpt_id)
                        .executeAndFetchFirst(Departments.class));
            }
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return allDepartments;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}