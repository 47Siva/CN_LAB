import java.io.*;
import java.net.*;

public class Download {
    public static void main(String[] args) {
        String fileName = "digital_image_processing.jpg";
        String website = "http://tutorialspoint.com/java_dip/images/" + fileName;

        try {
            URL url = URI.create(website).toURL();

            try (InputStream inputStream = url.openStream();
                 OutputStream outputStream = new FileOutputStream(fileName)) {

                byte[] buffer = new byte[2048];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Download complete: " + fileName);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}