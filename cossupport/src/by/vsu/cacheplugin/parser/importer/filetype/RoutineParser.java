package by.vsu.cacheplugin.parser.importer.filetype;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RoutineParser extends BaseParser {
    private boolean isStart;

    public RoutineParser(String workspaceURL, String XMLName) {
        super(workspaceURL, XMLName);
    }

    @Override
    public void startElement(String uri, String local_name, String raw_name, Attributes amap) throws SAXException {
        currentElement.push(local_name);
        if (currentElement.peek().equals("Routine")) {
            fileName = File.separator + amap.getValue("name").replace(".", File.separator) + "." + amap.getValue("type").toLowerCase();
            try {
                fw = new FileWriter(workspaceURL + fileName);
                bw = new BufferedWriter(fw);
                isStart = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentElement.peek().equals("Routine")) {
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
        if (currentElement.peek().equals("Routine")) {
            String entity;
            if (isStart) {
                entity = new String(ch, start + 1, length - 1);
                isStart = false;
            } else entity = new String(ch, start, length);
            try {
                bw.write(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}