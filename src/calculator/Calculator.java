package calculator;

import calculator.gui.GUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author Игорь
 */
public class Calculator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().startGUI();
            }
        });
    }

}
