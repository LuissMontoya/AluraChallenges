package challenges;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterGUI {
    private JFrame frame;
    private JTextField amountTextField;
    private JComboBox<String> conversionComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    private static final Map<String, Double> exchangeRates = new HashMap<>();
    static {
        exchangeRates.put("Pesos a Dólar", 0.00026);
        exchangeRates.put("Pesos a Euro", 0.00022);
        exchangeRates.put("Pesos a Libras", 0.00019);
        exchangeRates.put("Pesos a Yen", 0.029);
        exchangeRates.put("Pesos a Won coreano", 0.31);
        exchangeRates.put("Dólar a Pesos", 3827.0);
        exchangeRates.put("Euro a Pesos", 4545.0);
        exchangeRates.put("Libras a Pesos", 5255.0);
    }

    public CurrencyConverterGUI() {
        frame = new JFrame("Convertidor de Divisas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        centerFrameOnScreen(frame);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        amountTextField = new JTextField(10);
        conversionComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        convertButton = new JButton("Convertir");
        resultLabel = new JLabel("", SwingConstants.CENTER);

        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        convertButton.setBackground(Color.BLUE);
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));

        resultLabel.setForeground(Color.BLUE);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        inputPanel.add(new JLabel("Cantidad:"));
        inputPanel.add(amountTextField);
        inputPanel.add(new JLabel("Conversión:"));
        inputPanel.add(conversionComboBox);
        inputPanel.add(new JLabel());
        inputPanel.add(convertButton);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(resultLabel, BorderLayout.SOUTH);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String selectedConversion = (String) conversionComboBox.getSelectedItem();
            double exchangeRate = exchangeRates.get(selectedConversion);

            double convertedAmount = amount * exchangeRate;

            resultLabel.setText("<html><div style='font-size:14px;'>Cantidad convertida: <b>" + convertedAmount + "</b></div></html>");
        } catch (NumberFormatException ex) {
            resultLabel.setText("<html><div style='color:red; font-size:14px;'>Error: Ingresa una cantidad válida</div></html>");
        }
    }

    private void centerFrameOnScreen(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CurrencyConverterGUI converterGUI = new CurrencyConverterGUI();
                converterGUI.show();
            }
        });
    }
}
