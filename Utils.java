import java.util.*;
import java.util.Formatter;
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
import java.io.*;
import java.util.logging.*;

public class Utils {

    public static String arrayListToFileString(ArrayList<String> al) {
        String s = "";

        for (int i = 0; i < al.size() - 1; i++) {
            s += al.get(i) + "\n";
        }
        s += al.get(al.size() - 1);

        return s;
    }

    public static String calculateSHA1(String text) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(text.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
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
