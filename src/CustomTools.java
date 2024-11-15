import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class CustomTools {

    // This method loads an image from a given resource path (within the application) and returns it as a JLabel
    public static JLabel loadImage(String resource) {
        BufferedImage image;  // Declares a BufferedImage to store the loaded image
        try {
            // This line loads the resource (e.g., an image file) as a stream from the current class's location
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource);

            // Reads the image from the input stream
            image = ImageIO.read(inputStream);

            // Converts the BufferedImage to an ImageIcon and returns it inside a JLabel (a GUI component)
            return new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            // Prints an error message in case something goes wrong during image loading
            System.out.println("Error: " + e);
        }
        return null; // Returns null if the image couldn't be loaded
    }

    public static Font createFont(String resource) {
        // get the font file path
        String filePath = CustomTools.class.getClassLoader().getResource(resource).getPath();

        // check to see if the path contains a folder with spaces in them
        if (filePath.contains("%20")) {
            filePath = CustomTools.class.getClassLoader().getResource(resource).getPath().replaceAll("%20", " ");
        }

        // create font
        try {
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
