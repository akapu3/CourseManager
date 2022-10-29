package persistence;

import model.Account;
import model.Assignment;
import model.Course;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    //test when nothing to load
    // test loading when only thing to load
    //test loading when multiple things to load
    //test failing in each case <- catch the nullpointerexception or the name not found exception(check official name)

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testReadingEmptyAccount() {
        try {
            Account ac = new Account("Test1", "t1", "tt");

            JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
            Account ac2 = reader.read();
            assertTrue(ac2.equalsAccount(ac));
            assertEquals(0, ac2.getCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testReadingGeneralAccount(){
        try{

            Account ac = new Account("test1","t1","T");
            Course c = new Course("CPSC210","Online","Joe");
            Assignment a = new Assignment("A1","CPSC210",10122022);
            ac.addCourse(c);
            ac.getCourses().get(0).addAssignment(a);

            JsonReader reader = new JsonReader("./data/testReadingGeneralAccount.json");
            Account ac2 = reader.read();
            assertTrue(ac2.equalsAccount(ac));
            assertEquals(1,ac2.getCourses().size());
            assertEquals(1,ac2.getCourses().get(0).getAssignments().size());
            assertTrue(ac2.getCourses().get(0).equals(c));
            assertTrue(ac2.getCourses().get(0).getAssignments().get(0).equals(a));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
