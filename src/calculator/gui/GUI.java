package calculator.gui;

import calculator.intermediate.InfixToPostfix;
import calculator.analytics.*;
import calculator.executors.Executor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author Игорь
 */
public class GUI implements ActionListener {

    private UIManager.LookAndFeelInfo l[];
    private JTextField field = null;
    private JButton[] squares = new JButton[20];
    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private JFrame frame = new JFrame("Calculator");
    Border border = null;

    ParserImpl p = new ParserImpl();

    public GUI() {
        l = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(l[3].getClassName());
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
        }
        initComponents();
    }

    public void startGUI() {
        frame.setVisible(true);
    }

    private void createTextField() {
        field = new JTextField();
        border = field.getBorder();
    }

    private void initComponents() {
        createTextField();
        createButton();
        createButtonPanel();
        createMainPanel();
        createFrame();
    }

    private void createFrame() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(pan);
        frame.pack();
    }

    private void createMainPanel() {
        pan.setLayout(new BorderLayout(5, 5));
        pan.add(BorderLayout.CENTER, pan2);
        pan.add(BorderLayout.NORTH, field);
        pan.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    private void createButtonPanel() {
        pan2.setLayout(new GridLayout(4, 5));
        for (JButton square : squares) {
            pan2.add(square);
        }
    }

    private void createButton() {
        createDigitButton();
        createAddButton();
        createSubstructButton();
        createMultButton();
        createDivideButton();
        createPowButton();
        createBraceButton();
        createPointButton();
        createSqrtButton();
        createResultButton();
    }

    private void createBraceButton() {
        squares[15] = new JButton();
        squares[15].addActionListener(this);
        squares[15].setText("(");
        squares[16] = new JButton();
        squares[16].addActionListener(this);
        squares[16].setText(")");
    }

    private void createPointButton() {
        squares[17] = new JButton();
        squares[17].addActionListener(this);
        squares[17].setText(".");
    }

    private void createSqrtButton() {
        squares[18] = new JButton();
        squares[18].addActionListener(this);
        char c = '\u221A';
        squares[18].setText(c + "");
    }

    private void createResultButton() {
        squares[19] = new JButton();
        squares[19].addActionListener(this);
        squares[19].setText("=");
    }

    private void createPowButton() {
        squares[14] = new JButton();
        squares[14].addActionListener(this);
        squares[14].setText("^");
    }

    private void createDivideButton() {
        squares[13] = new JButton();
        squares[13].addActionListener(this);
        squares[13].setText("/");
    }

    private void createMultButton() {
        squares[12] = new JButton();
        squares[12].addActionListener(this);
        squares[12].setText("*");
    }

    private void createSubstructButton() {
        squares[11] = new JButton();
        squares[11].addActionListener(this);
        squares[11].setText("-");
    }

    private void createAddButton() {
        squares[10] = new JButton();
        squares[10].addActionListener(this);
        squares[10].setText("+");
    }

    private void createDigitButton() {
        for (int i = 0; i < 10; i++) {
            squares[i] = new JButton();
            squares[i].addActionListener(this);
            squares[i].setText(i + "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton theButton = (JButton) e.getSource();

        if ("=".equals(theButton.getText())) {
            String expr = field.getText();
            if (p.accept(expr)) {
                field.setBorder(border);
                String postfix = InfixToPostfix.convert(field.getText());
                String result = null;
                try {
                    result = Executor.execute(postfix);
                } catch (ArithmeticException exp) {
                    field.setBorder(BorderFactory.createLineBorder(Color.RED));
                    return;
                }
                field.setText(result);
            } else {
                field.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        } else if (('\u221A' + "").equals(theButton.getText())) {
            String expr = field.getText();
            if (p.accept(expr)) {
                field.setBorder(border);
                String postfix = InfixToPostfix.convert(field.getText());
                String result = Executor.execute(postfix);
                Double temp2 = Math.sqrt(Double.valueOf(result));
                field.setText(temp2.toString());
            } else {
                field.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        } else {
            field.setText(field.getText() + theButton.getText());
        }
    }

}
