package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CourseManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignmentTest {

    //Adding Assignment
    //Removing Assignment
    //Adding Course
    //Removing Course
    //Creating new Account
    //Display List of Courses
    Assignment a1;
    @BeforeEach
    public void setup(){
        a1 = new Assignment("Phase0","CPSC210",20221016);
    }

    @Test
    public void testGetCourse(){
        assertEquals("CPSC210",a1.getCourse() );
    }

    @Test
    public void testGetDueDate(){
        assertEquals(20221016,a1.getDueDate());
    }
    @Test
    public void testGetName(){
        assertEquals("Phase0",a1.getName());
    }
    @Test
    public void testChangeDueDate(){
        a1.changeDueDate(20221017);
        assertEquals(20221017,a1.getDueDate());
    }
}
