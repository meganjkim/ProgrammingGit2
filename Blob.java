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

    private String SHA;

    public Blob(String fileName) throws Exception {
        SHA = createBlob(fileName);
    }

    public String getSHA() {
        return SHA;
    }

    private String createBlob(String inputFile) throws IOException, NoSuchAlgorithmException {
        String fileContent = Utils.readFile(inputFile);
        String hash = Utils.calculateSHA1(fileContent);

        File folder = new File("./objects");

        if (!folder.exists()) {
            folder.mkdir();
        }

        File newBlob = new File("./objects/" + hash);
        newBlob.createNewFile();

        Utils.writeToFile("./objects/" + hash, fileContent);

        return hash;
    }
}
