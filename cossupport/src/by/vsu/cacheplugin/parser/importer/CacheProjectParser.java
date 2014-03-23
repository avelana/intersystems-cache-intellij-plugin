package by.vsu.cacheplugin.parser.importer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;

public class CacheProjectParser extends DefaultHandler {

    private String workspaceURL;
    private boolean hasClass = false;
    private boolean hasRoutine = false;

    public boolean isHasClass() {
        return hasClass;
    }

    public boolean isHasRoutine() {
        return hasRoutine;
    }

    public CacheProjectParser(String workspaceURL) {
        this.workspaceURL = workspaceURL;
    }

    public void buildFileTree(String XMLName) {
        try {
            Class c = Class.forName("org.apache.xerces.parsers.SAXParser");
            XMLReader reader = (XMLReader) c.newInstance();
            File root = new File(workspaceURL);
            root.mkdir();
            reader.setContentHandler(this);
            reader.parse(XMLName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String local_name, String raw_name, Attributes amap) throws SAXException {
        String filename = null;
        if (!hasClass && local_name.equalsIgnoreCase("Class")) {
            hasClass = true;
        }
        if (!hasRoutine && local_name.equalsIgnoreCase("Routine")) {
            hasRoutine = true;
        }
        if (local_name.equalsIgnoreCase("ProjectItem")) {
            if (amap.getValue("type").equalsIgnoreCase("cls")) {
                filename = amap.getValue("name") + "." + amap.getValue("type").toLowerCase();
            } else {
                filename = amap.getValue("name");
            }
            createFile(filename);
        }
    }

    private void createFile(String name) {
        String filename = name.replace(".", ":");
        String[] folders = filename.split(":");
        filename = workspaceURL;
        for (int i = 0; i < folders.length - 2; i++) {
            filename += File.separatorChar + folders[i];
            File folder = new File(filename);
            folder.mkdir();
        }
        filename += File.separatorChar + folders[folders.length - 2] + "." + folders[folders.length - 1];
        File f = new File(filename);
        try {
            if (!f.createNewFile()) {
                throw new Exception("File " + filename + " can't be created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}