package test.lab6;

import lab6.java.Main;
import org.junit.Test;
import org.junit.contrib.java.lang.system.*;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void main() throws Exception {
        SystemOutRule rule = new SystemOutRule().enableLog();
        Main.main(null);
        assertEquals(false, rule.getLog().contains("ERROR"));
    }

}