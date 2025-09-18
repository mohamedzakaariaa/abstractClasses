import duckDuckGo.DuckHome;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DuckDuckTest extends TestBase {
    /**
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Search for [Selenium WebDriver]
     * Assert that the link from data file  is the first link on the search results page
     * Close Google Chrome
     */

//    MR MOHAB HOW TO MAKE IT TEST ALL DATA FROM THE FILE

    @Test
    public void searchTestUrl() throws IOException {

//       testdata


        List<String> lines = Files.readAllLines(Paths.get("C://Users//mzzzz//IdeaProjects//abstractClasses//src//main//resources//datafile"));
        String searchItem = "Selenium WebDriver";
        String expectedFirstLink = null;
        String actualUrl=new DuckHome(bot)
                .navigateHome()
                .searchFor(searchItem).getResultsLink(1);

        for (String line : lines) {
            if (line.startsWith(searchItem + "=")) {
                expectedFirstLink = line.split("=")[1];
                Assert.assertEquals(actualUrl,expectedFirstLink,"Links are not matching");
//            }
        }}


    }
    @Test
    public void searchTestFromXML() throws Exception {
        String searchItem = "Selenium WebDriver";
        File xmlFile = new File("C://Users//mzzzz//IdeaProjects//abstractClasses//src//main//resources//dataFileXml");
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        NodeList nodeList = doc.getElementsByTagName("test");

        String expectedUrl = null;
        for (int i = 0; i < nodeList.getLength(); i++){
            Element element = (Element) nodeList.item(i);
            if (element.getElementsByTagName("search").item(0).getTextContent().equals(searchItem)){
                expectedUrl = element.getElementsByTagName("url").item(0).getTextContent();
                break;
            }
            String actualUrl=new DuckHome(bot)
                    .navigateHome()
                    .searchFor(searchItem).getResultsLink(1);

        }

// for json  ObjectMapper mapper = new ObjectMapper();
//    JsonNode root = mapper.readTree(new File("src/main/resources/data.json"));









    }
}





















//   // Load file into Properties
//    Properties props = new Properties();
//    try (FileReader reader = new FileReader("C://Users//mzzzz//IdeaProjects//abstractClasses//src//main//resources//datafile.txt")) {
//        props.load(reader);
//    }