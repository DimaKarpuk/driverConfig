import java.util.ArrayList;
import java.util.List;

public class Main {
    static public void main(String[] args) {
         List<String> isbns = new ArrayList<>();
        isbns.add("9781449325862");
        isbns.add("9781449331818");
        isbns.add("9781449337711");
        isbns.add("9781449365035");
        isbns.add("9781491904244");
        isbns.add("9781491950296");
        isbns.add("9781593275846");
        isbns.add("9781593277574");
        int random = (int) (Math.random() * isbns.size());
        String isbn = isbns.get(random);
        System.out.println(isbn);
    }

}
