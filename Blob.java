import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.*;

public class Blob {

    public static String createBlob(String inputFile) throws IOException, NoSuchAlgorithmException {
        byte[] data = Utils.readFile(inputFile);
        String hash = Utils.calculateSHA1(inputFile);

        File folder = new File("objects");

        if (!folder.exists()) {
            folder.mkdir();
        }
        String outputFile = "objects/" + hash;
        Files.write(Paths.get(outputFile), data);
        return hash;
    }
}
