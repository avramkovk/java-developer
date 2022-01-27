import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Merger {
    public void moveContentFolder(File sourceFolder, File destinationFolder) {
        if (!destinationFolder.exists()) {
            destinationFolder.mkdir();
        }
        File[] contentSourceFolder = sourceFolder.listFiles();
        try {
            for (File file : contentSourceFolder) {
                if (file != null && file.isFile()) {
                    Path sourcePaths = Paths.get(file.getAbsolutePath());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
                    String date = String.format("%s-%s-%s-%sh-%sm-%ss-", dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(),dateTime.getSecond());
                    File destinationFile = new File(destinationFolder, date + file.getName());
                    Path destinationPaths = Paths.get(destinationFile.getParent() + File.separator + destinationFile.getName());
                    Files.copy(sourcePaths, destinationPaths, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}