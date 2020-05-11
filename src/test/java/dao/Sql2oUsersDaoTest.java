package dao;

import models.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oUsersDaoTest {
    private static Connection conn;
    private  static Sql2oDepartmentsDao departmentDao;
    private static Sql2oUsersDao userDao;
    private static Sql2oNewsDao newsDao;
    @Before
    public void setUp() throws Exception {
//        String connectionString = "jdbc:postgresql://localhost:5432/news_api_test";
//        Sql2o sql2o = new Sql2o(connectionString, "samuel", "1234");

        String connectionString = "jdbc:postgres://ec2-52-202-22-140.compute-1.amazonaws.com:5432/deog4jj1naqp25";
        Sql2o sql2o = new Sql2o(connectionString, "ymcidiynjsffzs", "2eecb1c869ad09934d91ac2cdda05526991ea6bbb6ab94e45a5e9cb9d530dfa2");

        departmentDao = new Sql2oDepartmentsDao(sql2o);
        userDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll();
        userDao.clearAll();
        newsDao.clearAll();
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
    @Test
    public void addingUserSetsId() throws Exception {
        Users testUser = setupNewUser();
        int originalUserId = testUser.getId();
        userDao.add(testUser);
        assertNotEquals(originalUserId, testUser.getId());
    }

    @Test
    public void addedUsersAreReturnedFromGetAll() throws Exception {
        Users testUser = setupNewUser();
        userDao.add(testUser);
        assertEquals(1, userDao.getAllUsers().size());
    }

    @Test
    public void noUserReturnsEmptyList() throws Exception {
        assertEquals(0, userDao.getAllUsers().size());
    }

    @Test
    public void deleteByIdDeletesUser_true() throws Exception {
        Users user = setupNewUser();
        userDao.add(user);
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAllUsers().size());
    }

    @Test
    public void clearAll() throws Exception {
        Users testUser = setupNewUser();
        Users otherUser = setupNewUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAllUsers().size());
    }

    //helpers
    public Users setupNewUser(){
        return new Users("Sam", "Junior programmer", "programmer","company");
    }
}