import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LibraryManagementSystem extends JFrame {

    // Book elements
    class Book {
        int bookId;
        String bookName;
        boolean isIssued;

        Book(int bookId, String bookName) {
            this.bookId = bookId;
            this.bookName = bookName;
            this.isIssued = false;
        }

        @Override
        public String toString() {
            return bookId + " | " + bookName + " | " + (isIssued ? "Issued" : "Available");
        }
    }

    // Store books
    ArrayList<Book> bookList = new ArrayList<>();

    // User interface elements
    JTextField idInput, nameInput;
    JTextArea outputArea;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(520, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel 
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 8, 8));

        topPanel.add(new JLabel("Book ID:"));
        idInput = new JTextField();
        topPanel.add(idInput);

        topPanel.add(new JLabel("Book Name:"));
        nameInput = new JTextField();
        topPanel.add(nameInput);

        JButton addButton = new JButton("Add Book");
        JButton issueButton = new JButton("Issue Book");
        JButton returnButton = new JButton("Return Book");

        topPanel.add(addButton);
        topPanel.add(issueButton);
        topPanel.add(returnButton);

        add(topPanel, BorderLayout.NORTH);

        // visualization of Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(e -> addNewBook());
        issueButton.addActionListener(e -> issueBook());
        returnButton.addActionListener(e -> returnBook());
    }

    // Condition for Add book
    void addNewBook() {
        try {
            int id = Integer.parseInt(idInput.getText());
            String name = nameInput.getText();

            for (Book b : bookList) {
                if (b.bookId == id) {
                    showMessage("Book ID already exists");
                    return;
                }
            }

            bookList.add(new Book(id, name));
            showBooks("Book added successfully");
            clearInputs();

        } catch (Exception e) {
            showMessage("Please enter valid book details");
        }
    }

    // Condition for Issue book
    void issueBook() {
        try {
            int id = Integer.parseInt(idInput.getText());

            for (Book b : bookList) {
                if (b.bookId == id && !b.isIssued) {
                    b.isIssued = true;
                    showBooks("Book issued successfully");
                    clearInputs();
                    return;
                }
            }
            showMessage("Book not available for issue");

        } catch (Exception e) {
            showMessage("Invalid Book ID");
        }
    }

    // Condition for Return book 
    void returnBook() {
        try {
            int id = Integer.parseInt(idInput.getText());

            for (Book b : bookList) {
                if (b.bookId == id && b.isIssued) {
                    b.isIssued = false;
                    showBooks("Book returned successfully");
                    clearInputs();
                    return;
                }
            }
            showMessage("Book is not issued");

        } catch (Exception e) {
            showMessage("Invalid Book ID");
        }
    }

    // Condition for Display books
    void showBooks(String message) {
        outputArea.setText(message + "\n\n");
        for (Book b : bookList) {
            outputArea.append(b.toString() + "\n");
        }
    }

    void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    void clearInputs() {
        idInput.setText("");
        nameInput.setText("");
    }

    public static void main(String[] args) {
        new LibraryManagementSystem().setVisible(true);
    }
}