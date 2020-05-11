//package models;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class DepartmentsTest {
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//    @Test
//    public void departmentsInstantiatesCorrectly_true() {
//        Departments newDepartments = setupNewDepartments();
//        assertEquals(true, newDepartments instanceof Departments);
//    }
//
//    @Test
//    public void getDepartmentNameReturnsNameCorrectly_String() {
//        Departments newDepartments = setupNewDepartments();
//        assertEquals("public relations", newDepartments.getName());
//    }
//
//    @Test
//    public void setDepartmentNameSetsCorrectDepartmentName() {
//        Departments newDepartment = setupNewDepartments();
//        newDepartment.setName("public relations");
//        assertEquals("public relations", newDepartment.getName());
//    }
//
//    @Test
//    public void getDescriptionReturnsDescriptionCorrectly_String() {
//        Departments newDepartments = setupNewDepartments();
//        assertEquals("promoting organization", newDepartments.getDescription());
//    }
//
//    @Test
//    public void setDescriptionSetsDescription_String() {
//        Departments newDepartments = setupNewDepartments();
//        newDepartments.setDescription("responsible for promotion of business model");
//        assertEquals("responsible for promotion of business model", newDepartments.getDescription());
//    }
//
//    @Test
//    public void getNumbersOfUsersReturnsNumberOfUsersCorrectly_int() {
//        Departments newDepartments = setupNewDepartments();
//        assertEquals(5, newDepartments.getUser());
//    }
//
//    @Test
//    public void setNumbersOfUsersSetsCorrectNumberOfUsers_int() {
//        Departments newDepartments = setupNewDepartments();
//        newDepartments.setUser(3);
//        assertEquals(3, newDepartments.getUser());
//    }
//
//    @Test
//    public void departmentReturnsTrueIfDepartmentNameAndDescriptionAndNumberOfUsersAreSame() {
//        Departments newDepartments = setupNewDepartments();
//        Departments anotherDepartments = setupNewDepartments();
//        assertEquals(true, newDepartments.equals(anotherDepartments));
//    }
//
//    public Departments setupNewDepartments(){
//        return new Departments("public relations", "promoting organization", 5);
//    }
//}