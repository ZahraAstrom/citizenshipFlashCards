package zee.project.flashcards;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.imageio.*;
import javax.swing.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    ArrayList<Integer> questions = new ArrayList<Integer>();
    BufferedImage question;
    BufferedImage answer;
    Boolean isQuestion = true;
    Iterator<Integer> iter;

    private void initList() {
        for (int i = 1; i < 200; i = i+2) {
            questions.add(i);
        }
        Collections.shuffle(questions);
        iter = questions.iterator();
    }

    private File getFileFromResources(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filename);
        return new File(resource.getFile());
    }

    private void setQuestionAnswer(App app, int index) throws IOException {
        app.question = ImageIO.read(app.getFileFromResources("CivicsFlashCards-" + String.format("%03d", index) + ".jpg"));
        app.answer = ImageIO.read(app.getFileFromResources("CivicsFlashCards-" + String.format("%03d", index + 1) + ".jpg"));
    }
    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.initList();
        app.setQuestionAnswer(app, app.iter.next());
        JFrame frame = new JFrame("My First GUI");
        JButton nextBtn = new JButton("Next Question");
        JButton picBtn = new JButton();
        picBtn.setIcon(new ImageIcon(app.question));

        picBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                picBtn.setIcon(app.isQuestion ? new ImageIcon(app.answer) : new ImageIcon(app.question));
                app.isQuestion = !app.isQuestion;
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (!app.iter.hasNext()) {
                        app.questions.clear();
                        app.initList();
                        System.out.println(app.questions);
                    }
                    app.setQuestionAnswer(app, app.iter.next());
                    picBtn.setIcon(new ImageIcon(app.question));
                    app.isQuestion = true;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1400,850);
       frame.getContentPane().add(BorderLayout.CENTER, picBtn);
       frame.getContentPane().add(BorderLayout.SOUTH, nextBtn); // Adds Button to content pane of frame
       frame.setVisible(true);
    }
}
