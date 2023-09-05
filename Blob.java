import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Blob 
{
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
    public static byte[] readFile (File file) throws IOException
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        try (FileInputStream files = new FileInputStream (file))
        {
            byte [] br = new byte [1024];
            int bytesRead;
            while ((bytesRead = files.read(br)) != -1)
            {
                bytes.write (br, 0, bytesRead);
            }
        }
        return bytes.toByteArray();
    }
private static void writeFile (String fileName, byte[] data) throws IOException
{
    try (FileOutputStream fos = new FileOutputStream(fileName))
    {
        fos.write (data);
    }
}
}