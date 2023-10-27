import java.util.*;
import java.io.*;

public class Tree {
    private static ArrayList<String> contents;
    private String SHA1;

    public Tree() {
        contents = new ArrayList<String>();
    }

    public void add(String line) {
        if (containsLine(line))
            return;

        contents.add(line);
    }

    public void remove(String s) {
        for (int i = contents.size() - 1; i >= 0; i--) {
            if (contents.get(i).contains(s)) {
                contents.remove(i);
            }
        }
    }

    public String save() throws Exception {
        String fileContents = Utils.arrayListToFileString(contents);
        SHA1 = Utils.calculateSHA1(fileContents);

        Utils.writeToFile("./objects/" + SHA1, fileContents);

        return SHA1;

    }

    public String getSHA1() {
        return SHA1;
    }

    public boolean containsLine(String line) {
        for (String s : contents) {
            if (s.equals(line))
                return true;
        }
        return false;
    }
}
