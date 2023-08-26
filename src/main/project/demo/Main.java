package src.main.project.demo;

import src.main.project.GUI.IntroMenu;

import javax.swing.*;

/**
 * @author Samuel Malec
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            IntroMenu introMenu = new IntroMenu();
        });
    }
}
