import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeTester {

    @BeforeAll
    public void setUpBeforeClass() throws Exception {
        File obj = new File("./objects");
        obj.mkdirs();
        File index = new File("./index");
        index.createNewFile();
    }

    @Test
    public void testContains() throws Exception {
        Tree t = new Tree();
        t.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        t.add("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt");
        assertTrue(t.contains("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt"));
    }

    @Test
    public void testAdd() throws Exception {
        Tree t = new Tree();
        t.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        t.add("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt");

        assertTrue(t.contains("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt"));
        assertTrue(t.contains("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt"));
    }
}
