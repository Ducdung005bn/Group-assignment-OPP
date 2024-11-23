package library.management.system;
import main.classes.LibraryManagementSystem;
import java.util.Vector;
import java.io.*;
import main.classes.*;


public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.loadData();





        PendingUser pendingUser = new PendingUser();

        new LogInJFrame(pendingUser, libraryManagementSystem).setVisible(true);

        libraryManagementSystem.saveData();
    }

}
