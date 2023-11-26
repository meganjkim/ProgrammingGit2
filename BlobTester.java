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

public class BlobTester {

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

    // @Test
    // @DisplayName("[8] Test if initialize and objects are created correctly")
    // void testInitialize() throws Exception {

    // // Run the person's code
    // // TestHelper.runTestSuiteMethods("testInitialize");

    // // check if the file exists
    // File file = new File("index");
    // Path path = Paths.get("objects");

    // assertTrue(file.exists());
    // assertTrue(Files.exists(path));
    // }

    @Test
    @DisplayName("[15] Test if adding a blob works.  5 for sha, 5 for file contents, 5 for correct location")
    void testCreateBlob() throws Exception {

        Blob b = new Blob("./milo.txt");

        String sha = Utils.calculateSHA1(Utils.readFile("./milo.txt"));

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + sha);
        assertTrue("Blob file to add not found", file_junit1.exists());

        // Read file contents
        assertEquals("File contents of Blob don't match file contents pre-blob creation", testFileContents,
                Files.readString(Paths.get("objects/" + sha)));
    }
}
