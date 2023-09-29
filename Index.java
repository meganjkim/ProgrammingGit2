import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

    private static final String indexFile = "index";
    private Map<String, String> blobMap;

    public Index() {
        blobMap = new HashMap<>();
    }

    public void initProject() throws IOException {
        Path indexPath = Paths.get(indexFile);
        if (!Files.exists(indexPath)) {
            Files.createFile(indexPath);
        }
    }

    public void createBlobs(String originalFileName, String sha1Hash) throws IOException {
        blobMap.put(originalFileName, sha1Hash);

        String entry = originalFileName + " : " + sha1Hash;
        Files.write(Paths.get(indexFile), (entry + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
    }

    public void removeBlobs(String originalFileName) throws IOException {

        blobMap.remove(originalFileName);

        // Read the current content of the 'index' file
        List<String> lines = Files.readAllLines(Paths.get(indexFile));
        List<String> newLines = new ArrayList<>();

        // Iterate through the lines and exclude the entry with the specified
        // originalFileName
        for (String line : lines) {
            String[] parts = line.split(" : ");
            if (parts.length == 2 && !parts[0].equals(originalFileName)) {
                newLines.add(line);
            }
        }

        // Write the updated content back to the 'index' file
        Files.write(Paths.get(indexFile), newLines);
    }

    public Map<String, String> getBlobMap() {
        return blobMap;
    }
}