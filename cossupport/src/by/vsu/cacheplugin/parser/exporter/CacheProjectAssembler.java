package by.vsu.cacheplugin.parser.exporter;

import by.vsu.cacheplugin.service.NamePathGenerator;
import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.sun.xml.internal.stream.writers.XMLStreamWriterImpl;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class CacheProjectAssembler {

    private File workspaceDir;
    private String xmlName;

    public CacheProjectAssembler(File workspaceDir, String xmlName) {
        this.workspaceDir = workspaceDir;
        this.xmlName = xmlName;
    }

    public void writeFile() throws IOException, XMLStreamException {
        StreamResult fw = new StreamResult(new FileOutputStream(xmlName));
        XMLStreamWriterImpl xmlStreamWriter = new XMLStreamWriterImpl(fw, "UTF-8", new PropertyManager(PropertyManager.CONTEXT_WRITER));
        xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
        xmlStreamWriter.writeStartElement("Export");
        xmlStreamWriter.writeAttribute("generator", "Cache");
        xmlStreamWriter.writeAttribute("version", "25");
        xmlStreamWriter.writeAttribute("zv", "Cache for Windows (x86-64) 2013.1.4 (Build 801U)");
        xmlStreamWriter.writeAttribute("ts", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis()));
        List<File> allFiles = Arrays.asList(workspaceDir.listFiles());
        for (File child : allFiles) {
            xmlStreamWriter.writeStartElement("Routine");
            String[] fullname = NamePathGenerator.pathToName(child.getPath());
            xmlStreamWriter.writeAttribute("name", fullname[0]);
            xmlStreamWriter.writeAttribute("type", fullname[1].toUpperCase());
            if (!fullname[1].toUpperCase().equals("INC")) {
                xmlStreamWriter.writeAttribute("languagemode", "0");
            }
            FileReader text = new FileReader(child);
            BufferedReader br = new BufferedReader(text);
            StringBuffer sb = new StringBuffer();
            while (br.ready()) {
                sb.append(br.readLine() + "\r\n");
            }
            xmlStreamWriter.writeCData(new String(sb.toString().getBytes(), Charset.forName("UTF-8")));
            xmlStreamWriter.writeEndElement();
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.close();
    }
}