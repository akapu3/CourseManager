package persistence;

import model.Account;
import model.Assignment;
import model.Course;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void testingWritingToInvalidFile(){
        try{
        Account ac = new Account("test1","t1","T");
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        writer.open();
        fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWritingAccountEmpty(){
        try{
            Account ac = new Account("test1","t1","T");
            JsonWriter writer = new JsonWriter("./data/testWritingAccountEmpty.json");
            writer.open();
            writer.write(ac);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWritingAccountEmpty.json");
            Account ac2 = reader.read();
            assertTrue(ac2.equals(ac));
            assertEquals(0, ac.getCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWritingGeneralAccount(){
        try{
            Account ac = new Account("test1","t1","T");
            JsonWriter writer = new JsonWriter("./data/testWritingGeneralAccount.json");
            Course c = new Course("CPSC210","Online","Joe");
            Assignment a = new Assignment("A1","CPSC210",10122022);
            ac.addCourse(c);
            ac.getCourses().get(0).addAssignment(a);
            writer.open();
            writer.write(ac);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWritingGeneralAccount.json");
            Account ac2 = reader.read();
            assertTrue(ac2.equals(ac));
            assertEquals(1,ac2.getCourses().size());
            assertEquals(1,ac2.getCourses().get(0).getAssignments().size());
            assertTrue(ac2.getCourses().get(0).equals(c));
            assertTrue(ac2.getCourses().get(0).getAssignments().get(0).equals(a));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }





}
