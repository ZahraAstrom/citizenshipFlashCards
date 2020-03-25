package zee.project.flashcards;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.*;
import javax.swing.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    private File getFileFromResources(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filename);
        return new File(resource.getFile());
    }
    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        App app = new App();
        JFrame frame = new JFrame("My First GUI");
        JButton kitBtn = new JButton();
        BufferedImage kitten = ImageIO.read(app.getFileFromResources("puppy.jpg"));
        kitBtn.setIcon(new ImageIcon(kitten));

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(300,300);
       JButton button = new JButton("Press");
       frame.getContentPane().add(BorderLayout.CENTER, kitBtn);
       frame.getContentPane().add(BorderLayout.SOUTH, button); // Adds Button to content pane of frame
       frame.setVisible(true);
    }
}
