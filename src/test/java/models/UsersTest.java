package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void userInstantiatesCorrectly() {
        Users newUser = setupNewUser();
        assertEquals(true, newUser instanceof Users);
    }

    @Test
    public void getsUserNameReturnsUserNameCorrectly_String() {
        Users newUser = setupNewUser();
        assertEquals("Sam", newUser.getName());
    }

    @Test
    public void setsUserNameSetsUserNameCorrectly_Annette() {
        Users newUser = setupNewUser();
        newUser.setName("Sam");
        assertEquals("Sam", newUser.getName());
    }


    @Test
    public void getsPositionReturnsRoleCorrectly_String() {
        Users newUser = setupNewUser();
        assertEquals("Junior programmer", newUser.getPosition());
    }

    @Test
    public void setsRoleSetsRoleCorrectly_String() {
        Users newUser = setupNewUser();
        newUser.setPosition("Junior programmer");
        assertEquals("Junior programmer", newUser.getPosition());
    }

    @Test
    public void userReturnsTrueIfUserNameAndDepartmentIdAndRoleAreSame() {
        Users newUser = setupNewUser();
        Users anotherUser = setupNewUser();
        assertEquals(true, newUser.equals(anotherUser));
    }


    public Users setupNewUser(){
        return new Users("Sam", "Junior programmer", "programmer","company");
    }

}