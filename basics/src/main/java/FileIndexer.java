import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIndexer {
    private static final String FILE_NAME = ".index";

    public static void main(String[] args) {
        if (args.length == 0) {
            File destinationFolder = new File(System.getProperty("user.dir"));
            indexFilesIn(destinationFolder);
            return;
        }
        if ("-h".equals(args[0]) || "--help".equals(args[0])) {
            System.out.println("Valid usage: MyApp [-r] [Directory]");
            System.out.println("-r - recursive");
            System.out.println("Directory - which needs to be indexed");
        }
        if ("-r".equals(args[0])) {
            if (!args[1].isEmpty()) {
                File file = new File(args[1]);
                if (file.isDirectory()) {
                    indexFilesRecursivelyIn(file);
                }
                return;
            }
            File destinationFolder = new File(System.getProperty("user.dir"));
            indexFilesRecursivelyIn(destinationFolder);
        }

        File directory = new File(args[0]);
        if (directory.isDirectory()) {
            indexFilesIn(directory);
        } else {
            System.out.println("cann not index file " + directory);
        }
    }

    public static void indexFilesRecursivelyIn(File directory) {
        File[] files = directory.listFiles();
        try (FileOutputStream fos = new FileOutputStream(directory.getAbsolutePath() + File.separator + FILE_NAME)) {
            for (int i = 0; i < files.length; i++) {
                writeFile(files[i], fos);
            }
        } catch (IOException e) {
            throw new IllegalStateException("failed to write in file .index", e);
        }
    }

    public static void indexFilesIn(File directory) {
        File[] files = directory.listFiles();
        try (FileOutputStream fos = new FileOutputStream(directory.getAbsolutePath() + File.separator + FILE_NAME)) {
            for (int i = 0; i < files.length; i++) {
                fos.write(getFileName(files[i]).getBytes());
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            throw new IllegalStateException("failed to write in file .index", e);
        }
    }

    public static String getFileName(File f) {
        String fileName = f.toString();
        if (f.isDirectory()) {
            fileName = fileName + File.separator;
        }
        return fileName;
    }

    public static void writeFile(File f, FileOutputStream fos) throws IOException {
        fos.write(getFileName(f).getBytes());
        fos.write("\n".getBytes());
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                writeFile(files[i], fos);
            }
        }
    }
}