package dao;

import models.Departments;


import models.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oDepartmentsDaoTest {
    private static Sql2oDepartmentsDao sql2oDepartmentsDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private static Sql2oNewsDao sql2oNewsDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {

        //String connectionString = "jdbc:postgresql://localhost:5432/news_api_test";
        //Sql2o sql2o = new Sql2o(connectionString, "samuel", "1234");


        String connectionString = "";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        sql2oDepartmentsDao=new Sql2oDepartmentsDao(sql2o);
        sql2oUsersDao=new Sql2oUsersDao(sql2o);
        sql2oNewsDao=new Sql2oNewsDao(sql2o);
        System.out.println("connected to database");
        conn=sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void idSetForAddedDepartment() {
        Departments department=setUpNewDepartments();
        int originalId=department.getId();
        sql2oDepartmentsDao.add(department);
        assertNotEquals(originalId,department.getId());
    }



    @Test
    public void addUserToDepartment() {
        Departments department=setUpNewDepartments();
        sql2oDepartmentsDao.add(department);
        Users user=setUpNewUsers();
        Users otherUser= new Users("Sam","junior developer","programming", "company");
        sql2oUsersDao.add(user);
        sql2oUsersDao.add(otherUser);
        sql2oDepartmentsDao.addDptToUsers(department, user);
        sql2oDepartmentsDao.addDptToUsers(department, otherUser);
        assertEquals(2,sql2oDepartmentsDao.getAllUsersBelongingToDepartment(department.getId()).size());
        assertEquals(2,sql2oDepartmentsDao.findById(department.getId()).getUser());
    }

    @Test
    public void getAll() {
        Departments departments=setUpNewDepartments();
        Departments otherDepartments=new Departments("sales","promotion of stock", 10);
        sql2oDepartmentsDao.add(departments);
        sql2oDepartmentsDao.add(otherDepartments);
        assertEquals(departments,sql2oDepartmentsDao.getAllDepartments().get(0));
        assertEquals(otherDepartments,sql2oDepartmentsDao.getAllDepartments().get(1));
    }

    @Test
    public void correctDepartmentIsReturnedFindById_true() {
        Departments departments=setUpNewDepartments();
        Departments otherDepartments=new Departments("sales","promotion of stock", 10);
        sql2oDepartmentsDao.add(departments);
        sql2oDepartmentsDao.add(otherDepartments);
        assertEquals(departments,sql2oDepartmentsDao.findById(departments.getId()));
        assertEquals(otherDepartments,sql2oDepartmentsDao.findById(otherDepartments.getId()));

    }

    @Test
    public void getAllUsersInDepartments() {
        Departments department=setUpNewDepartments();
        sql2oDepartmentsDao.add(department);
        Users user=setUpNewUsers();
        Users otherUser= new Users("Sam","Junior programmer","programming", "company");
        sql2oUsersDao.add(user);
        sql2oUsersDao.add(otherUser);
        sql2oDepartmentsDao.addDptToUsers(department, user);
        sql2oDepartmentsDao.addDptToUsers(department, otherUser);
        assertEquals(2,sql2oDepartmentsDao.getAllUsersBelongingToDepartment(department.getId()).size());
        assertEquals(2,sql2oDepartmentsDao.findById(department.getId()).getUser());
    }

    //helpers
    private Departments setUpNewDepartments() {
        return new Departments("Sales","promotion of stock", 10);
    }
    private Users setUpNewUsers() {
        return new Users("Sam","Junior programmer","programmer", "company");
    }
}