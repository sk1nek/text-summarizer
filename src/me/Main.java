package me;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String article = JOptionPane.showInputDialog(null, "Please, input fragment to summarize.");
        TextSummarizer ts = new TextSummarizer();
        System.out.println(ts.summarizeText(article, 5));
    }

}
