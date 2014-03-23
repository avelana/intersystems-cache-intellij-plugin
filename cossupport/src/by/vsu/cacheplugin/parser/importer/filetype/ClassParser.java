package by.vsu.cacheplugin.parser.importer.filetype;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Admin on 13.03.14.
 */
public class ClassParser extends BaseParser {
    private boolean isStartDef;

    public ClassParser(String workspaceURL, String XMLName) {
        super(workspaceURL, XMLName);
    }

    @Override
    public void startElement(String uri, String local_name, String raw_name, Attributes amap) throws SAXException {
        currentElement.push(local_name);
        if ("Class".equals(currentElement.peek())) {
            fileName = File.separator + amap.getValue("name").replace(".", File.separator) + ".cls";
            try {
                fw = new FileWriter(workspaceURL + fileName);
                bw = new BufferedWriter(fw);
                isStartDef = false;
                bw.write("Class " + amap.getValue("name") + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("Class".equals(currentElement.peek())) {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        currentElement.pop();
    }

    @Override
    public synchronized void characters(char[] ch, int start, int length) throws SAXException {
        if ("Super".equals(currentElement.peek())) {
            try {
                bw.write("Extends ( " + new String(ch, start, length) + ") ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("IncludeCode".equals(currentElement.peek())) {
            try {
                bw.write("Include " + new String(ch, start, length) + " \r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}