public class Test {
    public static void main(String[] args) {
        /*FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
        JFileChooser fileChooser = new JFileChooser("");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Choose .xml file to import");
        int retval = fileChooser.showOpenDialog(fileChooser.getParent());
        if (retval == JFileChooser.APPROVE_OPTION) {
            String xmlURL = fileChooser.getSelectedFile().getPath();
            File home = fileChooser.getSelectedFile().getParentFile();
            fileChooser = new JFileChooser("");
            fileChooser.setDialogTitle("Choose home directory for Cache project");
            fileChooser.setCurrentDirectory(home);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            retval = fileChooser.showOpenDialog(fileChooser.getParent());
            if (retval == JFileChooser.APPROVE_OPTION) {
                String workspaceURL = fileChooser.getSelectedFile().getPath();
                CacheProjectParser listSax = new CacheProjectParser(workspaceURL);
                listSax.buildFileTree(xmlURL);
                if (listSax.isHasRoutine()) {
                    BaseParser routineSax = new RoutineParser(workspaceURL, xmlURL);
                    routineSax.run();
                }
                if (listSax.isHasClass()) {
                    BaseParser classSax = new ClassParser(workspaceURL, xmlURL);
                    classSax.run();
                }
            }
        }*/
    }
}