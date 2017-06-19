import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
public class PageScraper {
    public static void main(String[] args) throws IOException {
    	Set<String> setMedia = new HashSet<String>();
    	Set<String> setLinks = new HashSet<String>();
    	Set<String> setImports = new HashSet<String>();
    	System.out.print("Please enter URL for PageScraper: ");
    	Scanner scan =new Scanner(System.in);
        String url =scan.nextLine();        
        
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

       // filling set with any media found on site
        for (Element src : media) {
            if (src.tagName().equals("img")) {
            	String tmp = "";
            	tmp +=(  src.attr("abs:src")+ src.attr("width")+ src.attr("height")+
                        src.attr("alt"));
            	if(!setMedia.contains(tmp))
            		setMedia.add(tmp);
            }
            	
            else {
            	String tmp = "";
            	tmp +=(src.attr("abs:src"));
            	if(!setMedia.contains(tmp))
            		setMedia.add(tmp);
               
            }
        }

       // filling set with imports
        for (Element link : imports) {
        	String tmp = "";
        	tmp += (link.attr("abs:href") + link.attr("rel"));
        	if(!setImports.contains(tmp))
        		setImports.add(tmp);
        }

       // filling set with all links found on site
        for (Element link : links) {
        	String tmp = "";
            tmp += (link.attr("abs:href") + link.text() );
            if(!setLinks.contains(tmp))
        		setLinks.add(tmp);
        }
       
        Iterator iter = setImports.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
       
    }
    
    
}