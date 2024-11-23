package main.classes;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Librarian extends User implements Serializable {
    private int librarianSalary = 200; //dollars per month

    public int getLibrarianSalary() {
        return librarianSalary;
    }

    public void setLibrarianSalary(int librarianSalary) {
        this.librarianSalary = librarianSalary;
    }

}
