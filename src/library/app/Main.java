package library.app;

import gui.Login;
import library.app.LibrarySystem;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {

            LibrarySystem librarySystem =
                    new LibrarySystem();

            Login login =
                    new Login(librarySystem);

            login.setVisible(true);
        });
    }
}