import com.ruska112.Human;
import com.ruska112.Student;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ruska112.ReflectionDemo.*;
import static org.junit.Assert.*;

public class ReflectionDemoTest {
    @Test
    public void getCountHumansTest0() {
        ArrayList<Object> in = new ArrayList<>();
        Collections.addAll(in,
                new Student("1", "2", LocalDate.of(2000, 1, 1), "IMIT"),
                new Student("2", "3", LocalDate.of(2010, 1, 1), "IMIT"));
        assertEquals(2, getCountHumans(in));
    }

    @Test
    public void getCountHumansTest1() {
        ArrayList<Object> in = new ArrayList<>();
        Collections.addAll(in, new Object(), new Human("1", "2", LocalDate.of(2000, 1, 1)), new Student("2", "3", LocalDate.of(2010, 1, 1), "IMIT"));
        assertEquals(2, getCountHumans(in));
    }

    @Test
    public void getCountHumansTest2() {
        ArrayList<Object> in = new ArrayList<>();
        Collections.addAll(in, new Object(), new Object());
        assertEquals(0, getCountHumans(in));
    }

    @Test
    public void getListOfPublicMethodsNameTest0() {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "equals", "hashCode", "setFirstName", "setLastName", "setBirthday", "getFirstName", "getLastName", "getBirthday", "toString", "getClass", "notify", "notifyAll", "wait", "wait", "wait");
        assertEquals(strings, getListOfPublicMethodsName(new Human("1", "1", LocalDate.of(2022, 2, 24))));
    }

    @Test
    public void getListOfSuperclassesNamesTest0() {
        List<String> result = new ArrayList<>();
        Collections.addAll(result, "com.ruska112.Human", "java.lang.Object");
        assertEquals(result, getListOfSuperclassesNames(new Student("1", "2", LocalDate.of(2000, 1, 1), "IMIT")));
    }

    @Test
    public void getListOfGettersAndSettersTest0() {
        var result = new ArrayList<String>();
        Collections.addAll(result, "setFirstName", "setLastName", "setBirthday", "getFirstName", "getLastName", "getBirthday");
        assertEquals(result, getListOfGettersAndSetters(new Human("1", "1", LocalDate.of(2022, 2, 24))));
    }

    @Test
    public void getListOfGettersAndSettersTest1() {
        var result = new ArrayList<String>();
        Collections.addAll(result, "setFaculty", "getFaculty", "setFirstName", "setLastName", "setBirthday", "getFirstName", "getLastName", "getBirthday");
        assertEquals(result, getListOfGettersAndSetters(new Student("1", "1", LocalDate.of(2022, 2, 24), "fsociety")));
    }
}
