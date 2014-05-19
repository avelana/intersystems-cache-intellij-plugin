package by.vsu.cacheplugin.service;

import java.io.File;

/**
 * Created by mmaya on 15.04.2014.
 */
public class NamePathGenerator {
    public static String createPathFromName(String name, String workspaceURL) {
        String filename = name.replace(".", ":");
        String[] folders = filename.split(":");
        filename = workspaceURL;
        for (int i = 0; i < folders.length - 2; i++) {
            filename += File.separatorChar + folders[i];
            File folder = new File(filename);
            folder.mkdir();
        }
        filename += File.separatorChar + folders[folders.length - 2] + "." + folders[folders.length - 1];
        return filename;
    }

    public static String[] pathToName(String path) {
        String name = "", ext = "";
        path = path.replace("\\", File.separator);
        path = path.substring(path.lastIndexOf(File.separator));
        ext = path.substring(path.lastIndexOf(".") + 1);
        System.out.println(ext);
        name = path.substring(1, path.lastIndexOf("."));
        System.out.println(name);
        return new String[]{name, ext};
    }
}
