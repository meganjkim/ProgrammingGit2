import java.util.*;
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
import java.io.*;

public class Utils {

    public static String arrayListToFileString(ArrayList<String> al) {
        String s = "";

        for (int i = 0; i < al.size() - 1; i++) {
            s += al.get(i);
        }
        s += al.get(al.size() - 1);

        return s;
    }

    public static String calculateSHA1(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
        try (InputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                sha1Digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] sha1Hash = sha1Digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : sha1Hash) {
            String hex = Integer.toHexString(0xFF & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String readFile(String inputFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        StringBuilder sb = new StringBuilder();

        while (br.ready()) {
            sb.append((char) br.read());
        }

        br.close();
        return sb.toString();
    }

    public static void writeToFile(String filePath, String contents) throws IOException {
        File f = new File(filePath);
        f.createNewFile();

        FileWriter fw = new FileWriter(f);
        fw.write(contents);
        fw.close();
    }

}
