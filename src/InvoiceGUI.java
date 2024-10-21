import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceGUI extends JFrame {
    private JTextField productNameField, unitPriceField, quantityField;
    private JTextArea invoiceDisplayArea;
    private Invoice invoice;

    public InvoiceGUI() {
        invoice = new Invoice();
        createGUI();
    }

    private void createGUI() {
        setTitle("Invoice Generator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(10, 10, 100, 25);
        add(productLabel);

        productNameField = new JTextField();
        productNameField.setBounds(120, 10, 150, 25);
        add(productNameField);

        JLabel priceLabel = new JLabel("Unit Price:");
        priceLabel.setBounds(10, 50, 100, 25);
        add(priceLabel);

        unitPriceField = new JTextField();
        unitPriceField.setBounds(120, 50, 150, 25);
        add(unitPriceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 90, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(120, 90, 150, 25);
        add(quantityField);

        JButton addButton = new JButton("Add Item");
        addButton.setBounds(120, 130, 100, 25);
        add(addButton);

        invoiceDisplayArea = new JTextArea();
        invoiceDisplayArea.setBounds(10, 170, 350, 150);
        add(invoiceDisplayArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLineItem();
            }
        });

        setVisible(true);
    }

    private void addLineItem() {
        try {
            String productName = productNameField.getText();
            double unitPrice = Double.parseDouble(unitPriceField.getText()); // Changed to Double
            double quantity = Double.parseDouble(quantityField.getText());   // Changed to Double

            Product product = new Product(productName, unitPrice);
            LineItem item = new LineItem(product, quantity);  // Update LineItem to use double for quantity
            invoice.addLineItem(item);

            updateInvoiceDisplay();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for unit price and quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateInvoiceDisplay() {
        StringBuilder display = new StringBuilder("Invoice:\n");
        for (LineItem item : invoice.getLineItems()) {
            display.append(item.getProduct().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = $")
                    .append(String.format("%.2f", item.getTotal()))  // Format each line item total to 2 decimal places
                    .append("\n");
        }
        display.append("\nTotal Amount Due: $").append(String.format("%.2f", invoice.getTotalAmountDue()));  // Format the total amount due to 2 decimal places
        invoiceDisplayArea.setText(display.toString());
    }

    public static void main(String[] args) {
        new InvoiceGUI();
    }
}
