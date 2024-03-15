import java.io.IOException;
import java.nio.file.*;

public class File {
    public static void main(String[] args) {
        Path sourcePath = Paths
                .get("C:\\Users\\hp\\OneDrive - Lovely Professional University\\Desktop\\Java Trial\\Akash.txt");
        Path targetPath = Paths
                .get("C:\\Users\\hp\\OneDrive - Lovely Professional University\\Desktop\\AAH.txt");

        try {
            // Create the source file
            Files.createFile(sourcePath);

            // Move the file to the target location
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File moved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
