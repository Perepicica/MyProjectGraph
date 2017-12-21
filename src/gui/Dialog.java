package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog {
    public Dialog(JFrame Main,String string){
        super(Main,"You are an Invalid",true);
        add(new JLabel(string), BorderLayout.CENTER);
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        JPanel panel=new JPanel();
        panel.add(ok);
        panel.setBackground(Color.BLUE);
        add(panel,BorderLayout.SOUTH);
        setBounds(100,20,200,160);
       // setSize(200,160);
    }
}
