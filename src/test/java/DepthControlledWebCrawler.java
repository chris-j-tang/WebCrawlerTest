import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class DepthControlledWebCrawler{
    // Constructor
    public DepthControlledWebCrawler() {
        links = new HashSet<String>();
    }

    // Max depth control
    private static final int MAX_DEPTH = 2;

    private HashSet<String> links;

    public void getPageLinks(String URL, int depth) {
        // Ensure non-prior visit and max-depth not reached
        if (!(links.contains(URL)) && (depth < MAX_DEPTH)) {
            System.out.println("Depth: " + depth + " [" + URL + "]");
            try {
                // Append URL to visit history
                links.add(URL);

                // Retrieve HTML and scrape for URL links
                Document document = Jsoup.connect(URL).get();   // Why IOException here?
                Elements linksOnPage = document.select("a[href]");

                depth++;

                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }



    public static void main(String[] args) {
        new DepthControlledWebCrawler().getPageLinks("https://www.nytimes.com/", 0);
    }
}
