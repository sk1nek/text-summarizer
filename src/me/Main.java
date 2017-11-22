package me;

import javax.swing.*;

public class Main {

    private static String result;
    private static String input;

    public static void main(String[] args) {
        input = JOptionPane.showInputDialog(null, "Please, input fragment to summarize.");
        TextSummarizer ts = new TextSummarizer();
        result = ts.summarizeText(input, 3);

        //Making it safe
        SwingUtilities.invokeLater(() -> prepareGUI());

    }


    static void prepareGUI(){
        JFrame jf = new ResultDisplayFrame(result, input);
    }

}
