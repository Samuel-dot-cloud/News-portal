package dao;

import models.Departments;

import models.News;
import models.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {

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
    public void addNews() {
        Users users=setUpNewUsers();
        sql2oUsersDao.add(users);
        Departments departments=setUpDepartments();
        sql2oDepartmentsDao.add(departments);
        News news=new News("Strategic planning",1, "the way forward");
        sql2oNewsDao.add(news);

        assertEquals(users.getId(),sql2oNewsDao.findByiId(news.getId()).getId());
        assertEquals(news.getDpt_id(),sql2oNewsDao.findByiId(news.getId()).getDpt_id());
    }


   //helpers
    private Departments setUpDepartments() {
        return new Departments("sales","promotion of stock", 10);
    }
    private Users setUpNewUsers() {
        return new Users("Sam","Junior programmer","programmer", "company");
    }

}