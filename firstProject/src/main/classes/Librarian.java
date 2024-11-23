package main.classes;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Librarian extends User implements Serializable {
    private int librarianSalary = 200; //dollars per month

    @Override
    public void printUserInfo() {
        super.printUserInfo();
        System.out.println("Salary: " + librarianSalary);
    }

    public int getLibrarianSalary() {
        return librarianSalary;
    }

    public void setLibrarianSalary(int librarianSalary) {
        this.librarianSalary = librarianSalary;
    }

    public void removeDocument(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String iSBNInput = scanner.nextLine();
        Document removedDocument = libraryManagementSystem.findDocumentByISBN(iSBNInput);
        if (removedDocument == null) {
            System.out.println("Document not found");
        } else {
            if (removedDocument instanceof Book) {
                libraryManagementSystem.bookList.remove(removedDocument);
            } else if (removedDocument instanceof Thesis) {
                libraryManagementSystem.thesisList.remove(removedDocument);
            } else if (removedDocument instanceof Magazine) {
                libraryManagementSystem.magazineList.remove(removedDocument);
            }
        }
    }

    public void updateDocument(LibraryManagementSystem libraryManagementSystem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String iSBNInput = scanner.nextLine();
        Document documentToUpdate = libraryManagementSystem.findDocumentByISBN(iSBNInput);
        boolean continueUpdating = true;

        while (continueUpdating) {
            System.out.println("[0] Finish updating");
            System.out.println("[1] Quantity");
            System.out.println("[2] Title");
            System.out.println("[3] Author");
            System.out.println("[4] Description");
            System.out.println("[5] Language");
            System.out.println("[6] Number of Pages");

            if (documentToUpdate instanceof Book) {
                System.out.println("[7] Genre");
                System.out.println("[8] Publisher");
            } else if (documentToUpdate instanceof Thesis) {
                System.out.println("[7] Subject");
                System.out.println("[8] Degree");
                System.out.println("[9] University");
            } else if (documentToUpdate instanceof Magazine) {
                System.out.println("[7] Subject");
                System.out.println("[8] Frequency");
                System.out.println("[9] Issue Number");
            }

            System.out.print("Select the attribute you want to update: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new Quantity: ");
                        documentToUpdate.documentQuantity = LibraryManagementSystem.setValidInteger();
                    }
                    case 2 -> {
                        System.out.print("Enter new Title: ");
                        documentToUpdate.setDocumentTitle(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Enter new Author: ");
                        documentToUpdate.setDocumentAuthor(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Enter new Description: ");
                        documentToUpdate.setDocumentDescription(scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Enter new Language: ");
                        documentToUpdate.setDocumentLanguage(scanner.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Enter new Number of Pages: ");
                        documentToUpdate.setDocumentPage(LibraryManagementSystem.setValidInteger());
                    }
                    case 7 -> {
                        if (documentToUpdate instanceof Book) {
                            System.out.print("Enter new Genre: ");
                            ((Book) documentToUpdate).setBookGenre(scanner.nextLine());
                        } else if (documentToUpdate instanceof Thesis) {
                            System.out.print("Enter new Subject: ");
                            ((Thesis) documentToUpdate).setThesisSubject(scanner.nextLine());
                        } else if (documentToUpdate instanceof Magazine) {
                            System.out.print("Enter new Subject: ");
                            ((Magazine) documentToUpdate).setMagazineSubject(scanner.nextLine());
                        }
                    }
                    case 8 -> {
                        if (documentToUpdate instanceof Book) {
                            System.out.print("Enter new Publisher: ");
                            ((Book) documentToUpdate).setBookPublisher(scanner.nextLine());
                        } else if (documentToUpdate instanceof Thesis) {
                            System.out.print("Enter new Degree: ");
                            ((Thesis) documentToUpdate).setThesisDegree(scanner.nextLine());
                        } else if (documentToUpdate instanceof Magazine) {
                            System.out.print("Enter new Frequency: ");
                            ((Magazine) documentToUpdate).setMagazineFrequency(LibraryManagementSystem.setValidInteger());
                        }
                    }
                    case 9 -> {
                        if (documentToUpdate instanceof Book) {
                            System.out.println("Invalid choice. Please try again.");
                        } else if (documentToUpdate instanceof Thesis) {
                            System.out.print("Enter new University: ");
                            ((Thesis) documentToUpdate).setThesisUniversity(scanner.nextLine());
                        } else if (documentToUpdate instanceof Magazine) {
                            System.out.print("Enter new Issue Number: ");
                            ((Magazine) documentToUpdate).setMagazineIssueNumb(LibraryManagementSystem.setValidInteger());
                        }
                    }
                    case 0 -> {
                        continueUpdating = false;
                        System.out.println("Updating has finished.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


}
