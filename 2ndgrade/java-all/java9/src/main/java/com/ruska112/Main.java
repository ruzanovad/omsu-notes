package com.ruska112;

import javax.swing.*;

class Main {
    public static void main(String[] args) {

        //Creating the Frame
        JFrame frame = new JFrame("bb");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        var txt = new JTextField("Hello, World!");
        txt.setEditable(false);

        frame.getContentPane().add(txt);
        frame.setVisible(true);
    }
}
