import java.io.File;

public class AppMergeDir {
    private static final Merger MERGER = new Merger();

    public static void main(String[] args) {
        File destinationFolder = new File(System.getProperty("user.dir"));
        switch (args.length) {
            case 2:
                break;
            case 3:
                destinationFolder = new File(args[2]);
                break;
            default:
                printHelpCommand();
                return;
        }
        File sourceFolder1 = new File(args[0]);
        File sourceFolder2 = new File(args[1]);

        if (!sourceFolder1.exists()) {
            System.out.printf("source folder %s does not exist.", sourceFolder1);
            return;
        }

        if (!sourceFolder2.exists()) {
            System.out.printf("source folder %s does not exist.", sourceFolder2);
            return;
        }
        moveContentFolder(sourceFolder1, destinationFolder);
        moveContentFolder(sourceFolder2, destinationFolder);
    }

    private static void printHelpCommand() {
        System.out.println("usage: AppMergeDir sourceDir1 sourceDir2 [targetDir]");
    }

    private static void moveContentFolder(File sourceFolder, File destinationFolder) {
        MERGER.moveContentFolder(sourceFolder, destinationFolder);
        System.out.println("Files copied from " + sourceFolder + " to " + destinationFolder);
    }
}