package persistence;

import model.Account;
import model.Assignment;
import model.Course;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
    Allows the caller method/class to read from a JSon file
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name: ");
        String accountID = jsonObject.getString("accountID: ");
        String password = jsonObject.getString("password: ");
        Account account = new Account(name, accountID, password);
        addCourses(account, jsonObject);
        return account;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addCourses(Account account, JSONObject jsonObject) {
        JSONArray jsonArrayName = jsonObject.getJSONArray("courses: ");
        for (Object json : jsonArrayName) {
            JSONObject nextThingy = (JSONObject) json;
            addCourse(account, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addCourse(Account acc, JSONObject jsonObject) {
        String name = jsonObject.getString("name: ");
        String lectureType = jsonObject.getString("lectureType: ");
        String professorName = jsonObject.getString("professorName: ");
        Course temp = new Course(name, lectureType, professorName);
        addAssignment(temp, jsonObject);
        acc.addCourse(temp);
    }

    private void addAssignment(Course x, JSONObject jsonObject) {
        JSONArray jsonArrayName = jsonObject.getJSONArray("assignments: ");
        for (Object json : jsonArrayName) {
            JSONObject nextThingy = (JSONObject) json;
            addAssignments(x, nextThingy);
        }
    }

    private void addAssignments(Course x, JSONObject o) {
        String name = o.getString("name : ");
        String course = o.getString("course: ");
        int dueDate = o.getInt("duedate: ");
        x.addAssignment(new Assignment(name, course, dueDate));
    }
}


//Implemented using workroom app
