package mathservice;

import calculator.gui.GUI;
import MatrixCalculator.Multiply;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import linear.LinearEquation;

/**
 *
 * @author Игорь
 */
public class MathService {

    private PopupMenu popup;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private MenuItem menuItem3;
    private MenuItem exitItem;
    private SystemTray systemTray;
    private ImageIcon iIcon;
    private TrayIcon trayIcon;

    public MathService() throws AWTException {
        initComponents();
    }

    private void initComponents() throws AWTException, HeadlessException {
        createMenu();
        createPopupMenu();
        createImage();
        createTrayIcon();
        createSystemTray();
    }

    public void start() {
        trayIcon.displayMessage("MathService", "Приложение \"Математика\" ", TrayIcon.MessageType.INFO);
    }

    private void createSystemTray() throws AWTException {
        systemTray = SystemTray.getSystemTray();
        systemTray.add(trayIcon);
    }

    private void createTrayIcon() {
        trayIcon = new TrayIcon(iIcon.getImage(), "MathService", popup);
        trayIcon.setImageAutoSize(true);
    }

    private void createImage() {
        iIcon = new ImageIcon(getClass().getResource("/resources/calc.gif"));
    }

    private void createMenu() throws HeadlessException {
        createMenuItem1();
        createMenuItem2();
        createMenuItem3();
        createExitMenuItem();
    }

    private void createExitMenuItem() throws HeadlessException {
        exitItem = new MenuItem("Выход");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void createMenuItem3() throws HeadlessException {
        menuItem3 = new MenuItem("Матричный калькулятор");
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Multiply().run();
            }
        });
    }

    private void createMenuItem2() throws HeadlessException {
        menuItem2 = new MenuItem("Линейные уравнения");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LinearEquation().run();
            }
        });
    }

    private void createMenuItem1() throws HeadlessException {
        menuItem1 = new MenuItem("Калькулятор");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI().startGUI();
            }
        });
    }

    private void createPopupMenu() throws HeadlessException {
        popup = new PopupMenu();
        popup.add(menuItem1);
        popup.add(menuItem2);
        popup.add(menuItem3);
        popup.add(exitItem);
    }

    public static void main(String[] args) throws AWTException {
        new MathService().start();
    }

}
