import java.util.*;
import java.io.*;

public class Tree {
    static ArrayList<String> contents;

    public Tree() {
        contents = new ArrayList<String>();
    }

    public void add(String line) {
        if (contains(line))
            return;

    }

    public void remove(String s) {
        for (int i = contents.size() - 1; i >= 0; i--) {
            if (contents.get(i).contains(s)) {
                contents.remove(i);
            }
        }
    }

    public void save() throws Exception {
        String fileContents = Utils.arrayListToFileString(contents);
        String hash = Utils.calculateSHA1(fileContents);

        Utils.writeToFile("./objects/" + hash, fileContents);
    }

    public boolean contains(String line) {
        for (String s : contents) {
            if (s.equals(line)) {
                return true;
            }
        }
        return false;

    }
}
