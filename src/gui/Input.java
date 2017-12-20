package gui;



import javax.swing.*;
import java.awt.*;


public class Input extends JPanel {
    private Button button = new Button("Жми!");
    private JTextArea jTextArea = new JTextArea();
    private JLabel jLabel = new JLabel("Введите путь к файлу");

    public Input(int width, int height, Main m) {
        setBackground(Color.gray);
        button.setBackground(Color.pink);
        jTextArea.setBackground(Color.lightGray);
        setSize(width, height);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(jLabel,BorderLayout.NORTH);
        add(jTextArea,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
        button.addActionListener(e -> {
            m.getInput(jTextArea.getText());
            jTextArea.setText("");
        });
        setVisible(true);
    }

}

