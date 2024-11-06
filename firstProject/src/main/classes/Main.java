package main.classes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a new Book object
        Book book = new Book();
        Scanner sc = new Scanner(System.in);

        // Prompt and set book details
        System.out.print("Enter Book Title: ");
        book.setDocumentTitle(sc.nextLine());

        System.out.print("Enter Book Author: ");
        book.setDocumentAuthor(sc.nextLine());

        System.out.print("Enter Book Description: ");
        book.setDocumentDescription(sc.nextLine());

        System.out.print("Enter Book Language: ");
        book.setDocumentLanguage(sc.nextLine());

        System.out.print("Enter Book Page Count: ");
        book.setDocumentPage(sc.nextInt());
        sc.nextLine(); // Consume newline

        System.out.print("Enter Book ISBN: ");
        book.setDocumentISBN(sc.nextLine());

        System.out.print("Enter Book Genre: ");
        book.setBookGenre(sc.nextLine());

        System.out.print("Enter Book Publisher: ");
        book.setBookPublisher(sc.nextLine());

        // Print book information
        book.printDocInfo();

        // Generate QR code for the book
        MyQr qrGenerator = new MyQr();
        qrGenerator.generateQRDocument(book);

        System.out.println("QR code generated successfully.");

        sc.close();
    }
}
