// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Database db = new Database("tmp.txt");
        db.addRecord("a", "b", "c", "+380");
        System.out.println(db.getName("+380"));
        System.out.println(db.getPhoneNumber("a"));
        db.addRecord("a1", "b", "c", "+380");
        db.addRecord("a", "b", "c", "+380");
        System.out.println(db.removeRecord("a", "b", "c", "+380"));

    }
}