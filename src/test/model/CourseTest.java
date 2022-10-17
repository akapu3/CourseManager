package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {
    Course course;
    @BeforeEach
    public void setup(){
        course = new Course("CPSC210", "InPerson", "Bob");
    }

    @Test
    public void testGetName(){
        assertEquals("CPSC210",course.name);
    }
    @Test
    public void testGetProfessorName(){
        assertEquals("Bob",course.professorName);
    }
    @Test
    public void testGetLectureType(){
        assertEquals("InPerson",course.lectureType);
    }
    @Test
    public void testAddAssignment(){

        assertTrue(course.addAssignment(new Assignment("Phase1","CPSC210",20221101)));
    }
    @Test
    public void testRemoveAssignment(){
        Assignment a1 = new Assignment("Phase1","CPSC210",20221101);
        course.addAssignment(a1);
        assertTrue(course.removeAssignment(a1));
    }
    @Test
    public void testGetAssignment(){
        ArrayList<Assignment> test = new ArrayList<Assignment>();
        test.add(new Assignment("Phase1","CPSC210",20221101));
        course.addAssignment(new Assignment("Phase1","CPSC210",20221101));
        boolean checker = true;
        ArrayList<Assignment> courseAssignmentList = course.getAssignments();
        for(int i = 0; i < courseAssignmentList.size(); i++){
            if (!courseAssignmentList.get(i).getName().equals((test.get(i).getName()))) {
                checker = false;
            } else if (!courseAssignmentList.get(i).getCourse().equals((test.get(i).getCourse()))) {
                checker = false;
            } else if (!(courseAssignmentList.get(i).getDueDate() == test.get(i).getDueDate())){
                checker = false;
            } else {
                checker = true;
            }
        }
        assertTrue(checker);
    }

}
