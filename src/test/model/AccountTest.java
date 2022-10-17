package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {
    Account a1;
    Account a2;
    Account a3;


    @BeforeEach
    public void setup() {
        a1 = new Account("Jake", "12345", "Hello");
    }

     @Test
    public void testAddCourse() {
        assertTrue(a1.addCourse(new Course("CPSC210", "InPerson", "Bob")));
     }
     @Test
    public void testDropCourse() {
        Course test = new Course("CPSC210", "InPerson", "Bob");
         a1.addCourse(test);
         assertTrue(a1.dropCourse(test));
     }
     @Test
    public void testGetName(){
        assertEquals("Jake", a1.getName());
     }
    @Test
    public void testForgotPassword(){
        assertEquals("Your Password is: Hello",a1.forgotPassword("12345"));
    }
    @Test
    public void testEqualsAccount(){
        Account test = new Account("Jake", "12345", "Hello");
        assertTrue(a1.equalsAccount(test));
    }

    @Test
    public void testGetCourses(){
        ArrayList<Course> test = new ArrayList();
        test.add(new Course("CPSC210", "InPerson", "Bob"));
        a1.addCourse(new Course("CPSC210", "InPerson", "Bob"));
        ArrayList<Course> courses = a1.getCourses();
        boolean checker = true;
        for(int i = 0; i < courses.size(); i++){
            if (!courses.get(i).name.equals(test.get(i).name)) {

                checker = false;
            } else if (!courses.get(i).lectureType.equals(test.get(i).lectureType)) {
                checker = false;
            } else if (!courses.get(i).getProfessorName().equals(test.get(i).getProfessorName())) {
                checker = false;
            } else {
                checker = true;
            }
        }
        assertTrue(checker);
    }






}
