import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField userInputField;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton;

    private double firstNumber, secondNumber;
    private char operation;

    public SimpleCalculator() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input field
        userInputField = new JTextField();
        userInputField.setEditable(false);
        add(userInputField, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // Create number buttons
        for (int i = 1; i <= 9; i++) {
            JButton numberButton = new JButton(String.valueOf(i));
            numberButton.addActionListener(this);
            buttonPanel.add(numberButton);
        }
        addButton = new JButton("+");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(this);
        buttonPanel.add(zeroButton);

        JButton dotButton = new JButton(".");
        dotButton.addActionListener(this);
        buttonPanel.add(dotButton);

        subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        buttonPanel.add(subtractButton);

        multiplyButton = new JButton("*");
        multiplyButton.addActionListener(this);
        buttonPanel.add(multiplyButton);

        divideButton = new JButton("/");
        divideButton.addActionListener(this);
        buttonPanel.add(divideButton);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "+":
            case "-":
            case "*":
            case "/":
                if (userInputField.getText().isEmpty()) {
                    // Do nothing if the input field is empty
                    return;
                }
                operation = command.charAt(0);
                firstNumber = Double.parseDouble(userInputField.getText());
                userInputField.setText(userInputField.getText() + command);
                break;
            case "=":
                if (userInputField.getText().isEmpty()) {
                    // Do nothing if the input field is empty
                    return;
                }
                String[] parts = userInputField.getText().split("[" + operation + "]");
                if (parts.length < 2) {
                    // Do nothing if there are not enough parts
                    return;
                }
                secondNumber = Double.parseDouble(parts[1]);
                double result = performOperation();
                userInputField.setText(String.valueOf(result));
                break;
            case "Clear":
                userInputField.setText("");
                break;
            default:
                userInputField.setText(userInputField.getText() + command);
                break;
        }
    }

    private double performOperation() {
        double result = 0;
        switch (operation) {
            case '+':
                result = firstNumber + secondNumber;
                break;
            case '-':
                result = firstNumber - secondNumber;
                break;
            case '*':
                result = firstNumber * secondNumber;
                break;
            case '/':
                result = firstNumber / secondNumber;
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}
