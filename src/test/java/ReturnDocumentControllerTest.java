import library.management.system.*;
import main.classes.other.opponents.*;
import main.classes.main.opponents.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import static org.junit.jupiter.api.Assertions.*;

public class ReturnDocumentControllerTest {
    private JPanel jpnView;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;
    private final String[] COLUMNS = {"Borrower ID", "Book ISBN", "Borrow Date", "Planned Return Date", "Status", "Return"};
    private DefaultTableModel model;
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void returnDocument(){

    }
}