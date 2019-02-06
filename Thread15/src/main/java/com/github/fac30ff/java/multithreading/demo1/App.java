package com.github.fac30ff.java.multithreading.demo1;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("SwingWorkerDemo"));
    }
}
