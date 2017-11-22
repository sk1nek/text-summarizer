package me;

import javax.swing.*;
import java.awt.*;

public class ResultDisplayFrame extends JFrame{


    ResultDisplayFrame(String result, String input){

        setLayout(new BorderLayout());

        Double reduction = (result.length() * 1.0 / input.length());

        //displaying percent reduction
        JLabel jl = new JLabel();
        jl.setText("Size has been reduced by " + String.format("%1$,.2f", reduction) + '%');
        add(jl, BorderLayout.NORTH);

        //selectable text area with results
        JTextArea jta = new JTextArea();
        jta.setLineWrap(true);
        jta.setCaretPosition(0);
        jta.setEditable(false);
        jta.insert(result, 0);
        add(jta, BorderLayout.CENTER);

        setPreferredSize(new Dimension(300, 300));

        pack();
        setVisible(true);

    }
}
