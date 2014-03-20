package by.vsu.cacheplugin.parsers.importers.filetypes;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Created by Admin on 14.03.14.
 */
public class AbstractObjectSax extends DefaultHandler {
    protected Stack<String> currentElement = new Stack<String>();
    protected String workspaceURL;
    protected String fileName;
    protected String XMLName;
    protected FileWriter fw;
    protected BufferedWriter bw;

    public void run() {
        try {
            Class c = Class.forName("org.apache.xerces.parsers.SAXParser");
            XMLReader reader = (XMLReader) c.newInstance();
            reader.setContentHandler(this);
            reader.parse(XMLName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AbstractObjectSax(String workspaceURL, String XMLName) {
        this.workspaceURL = workspaceURL;
        this.XMLName = XMLName;
    }

}
