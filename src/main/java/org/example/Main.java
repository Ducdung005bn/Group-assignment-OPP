package org.example;

import library.management.system.LogInJFrame;
import main.classes.main.opponents.*;
import main.classes.other.opponents.GoogleBooksAPI;

public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.loadData();

        PendingUser pendingUser = new PendingUser();

        new LogInJFrame(pendingUser, libraryManagementSystem).setVisible(true);

        libraryManagementSystem.saveData();
    }
}
