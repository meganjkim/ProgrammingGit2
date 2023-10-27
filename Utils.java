import java.util.*;
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

}
