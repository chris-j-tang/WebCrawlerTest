import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class BasicWebCrawler {

    // Using hash for O(1) and set for the unique elements
    private HashSet<String> links;

    // Constructor
    public BasicWebCrawler() {
        links = new HashSet<String>();
    }

    // Method for retrieving page links in given page
    public void getPageLinks(String URL) {
        // Case checking for previous visited URL
        if (!links.contains(URL)) {
            try {
                // Add URL to website history
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                // Get the HTML from the document
                Document document = Jsoup.connect(URL).get();
            } catch (IOException e){
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}
