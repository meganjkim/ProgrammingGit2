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
    static void setUpBeforeClass() throws Exception {
        File obj = new File("./objects");
        obj.mkdirs();
        File index = new File("./index");
        index.createNewFile();
    }

    @Test
    void testContains() throws Exception {
        Tree t = new Tree();
        t.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        t.add("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt");
        assertTrue(t.containsLine("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt"));
    }

    @Test
    void testAdd() throws Exception {
        Tree t = new Tree();
        t.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        t.add("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt");

        assertTrue(t.containsLine("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt"));
        assertTrue(t.containsLine("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt"));
    }

    @Test
    void testSave() throws Exception {
        Tree t = new Tree();

        t.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
        t.add("blob : 01d82591292494afd1602d175e165f94992f6f5f : file2.txt");
        t.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 : file3.txt");
        t.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        t.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");

        t.save();

        String fileName = "./objects/649a3d7f1b034f8ec9954b7411c63818475b2385";
        File f = new File(fileName);
        assertTrue(f.exists());
    }
}
