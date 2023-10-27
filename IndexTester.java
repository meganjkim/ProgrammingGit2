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

public class IndexTester {

    static String testFileContents = "Milo is adorable.";

    public static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        File objects = new File("objects");
        File[] contents = objects.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        objects.delete();

        File index = new File("index");
        index.delete();

        Path pathObject = Paths.get("./milo.txt");
        Files.createFile(pathObject);

        FileWriter fw = new FileWriter("./milo.txt");
        fw.write(testFileContents);
        fw.close();

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {

        File milo = new File("milo.txt");
        milo.delete();

        File objects = new File("objects");
        File[] contents = objects.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        objects.delete();

        File index = new File("index");
        index.delete();

    }

    @Test
    @DisplayName("[8] Test if initialize and objects are created correctly")
    void testInitialize() throws Exception {

        Index index = new Index();
        index.initProject();
        File file = new File("index");
        Path path = Paths.get("objects");

        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

}
