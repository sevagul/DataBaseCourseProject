package gui.holidayAdding;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    private JTextArea textArea;
    public TextPanel(){
        //// setting up Layout
        setLayout(new BorderLayout());

        //// setting up components /////
        textArea = new JTextArea();

        //// adding components //////
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    public void appendText(String text){
        textArea.append(text);
    }
    public  String getText(){
        return textArea.getText();
    }
    public void setText(String text){
        textArea.setText(text);
    }
}
