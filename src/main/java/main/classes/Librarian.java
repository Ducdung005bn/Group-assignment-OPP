package main.classes;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.io.*;

public class Librarian extends User implements Serializable {
    private int librarianSalary = 200; //dollars per month
    private List<String> actionHistory;

    public Librarian() {
        super();
        actionHistory = new ArrayList<>();
    }

    public int getLibrarianSalary() {
        return librarianSalary;
    }

    public void setLibrarianSalary(int librarianSalary) {
        this.librarianSalary = librarianSalary;
    }

    public List<String> getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(List<String> actionHistory) {
        this.actionHistory = actionHistory;
    }

    public void addUserAction(User user) {
        String addUserInformation = "";
        String time = (LocalTime.now()).getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + time;
        if (user instanceof Librarian) {
            addUserInformation += date + " : Add the librarian with ID " + String.valueOf(user.getUserID());
        } else if (user instanceof Borrower) {
            addUserInformation += date + " : Add the borrower with ID " + String.valueOf(user.getUserID());
        }

        this.actionHistory.add(addUserInformation);
    }

    public void dealWithDocumentAction(String documentISBN, String kind) {
        String time = (LocalTime.now()).getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + time;
        this.actionHistory.add(date + " : " + kind + " the document with ISBN " + documentISBN);
    }

}
