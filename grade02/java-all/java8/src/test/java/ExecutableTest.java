import com.ruska112.Human;
import org.junit.Test;

import static org.junit.Assert.*;

import com.ruska112.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static com.ruska112.Executable.getCountExecutableAndExecute;

class Exec1 implements Executable {
    @Override
    public void execute() {
    }
}

class Exec2 extends Exec1 {
}

public class ExecutableTest {
    @Test
    public void getCountExecutableAndExecuteTest0() {
        var list = new ArrayList<>();
        Collections.addAll(list, new Exec1(), new Exec2(), new Human("q", "w", LocalDate.of(2015, 6, 6)));
        assertEquals(2, getCountExecutableAndExecute(list));
    }

    @Test
    public void getCountExecutableAndExecuteTest1() {
        var list = new ArrayList<>();
        Collections.addAll(list, new Human("qwe", "rty", LocalDate.of(2020, 2, 2)), new Exec2(), new Human("q", "w", LocalDate.of(2015, 6, 6)));
        assertEquals(1, getCountExecutableAndExecute(list));
    }

    @Test
    public void getCountExecutableAndExecuteTest2() {
        var list = new ArrayList<>();
        Collections.addAll(list, new Human("qwe", "rty", LocalDate.of(2020, 2, 2)), new Human("q", "w", LocalDate.of(2015, 6, 6)));
        assertEquals(0, getCountExecutableAndExecute(list));
    }
}
