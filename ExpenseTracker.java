import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ExpenseTracker extends JFrame {
    private JTextField categoryField, descriptionField, amountField;
    private JTextArea displayArea;
    private DatabaseManager db;

    public ExpenseTracker() {
        super("Expense Tracker");

        db = new DatabaseManager();

        setLayout(new FlowLayout());

        categoryField = new JTextField(10);
        descriptionField = new JTextField(10);
        amountField = new JTextField(5);

        JButton addButton = new JButton("Add Expense");
        JButton viewButton = new JButton("View All");

        displayArea = new JTextArea(15, 30);
        displayArea.setEditable(false);

        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(addButton);
        add(viewButton);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(e -> {
            String category = categoryField.getText();
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());

            db.addExpense(new Expense(category, description, amount));
            categoryField.setText("");
            descriptionField.setText("");
            amountField.setText("");
        });

        viewButton.addActionListener(e -> {
            List<Expense> expenses = db.getExpenses();
            displayArea.setText("");
            for (Expense exp : expenses) {
                displayArea.append(exp.getCategory() + " | " + exp.getDescription() + " | ₹" + exp.getAmount() + "\n");
            }
        });

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ExpenseTracker();
    }
}
