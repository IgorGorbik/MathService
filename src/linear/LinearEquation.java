package linear;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Игорь
 */
public class LinearEquation implements Runnable {

    JFrame frame;

    JPanel pan1;
    JPanel pan1_1;
    JPanel pan1_2;
    JPanel pan1_1_1;
    JPanel pan1_2_1;
    JPanel pan1_1_2;
    JPanel pan1_2_2;
    JSpinner sp1;
    JSpinner sp2;
    JTable t1;
    JTable t2;
    Model1 dtm1;
    Model2 dtm2;
    SpinnerNumberModel snm1;
    SpinnerNumberModel snm2;

    int count1 = 1;
    int count2 = 1;

    public LinearEquation() {
        UIManager.LookAndFeelInfo[] l = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(l[2].getClassName());
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
        }
        frame = new JFrame("Расчет");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Model1.a1 = new ArrayList();
                Model2.a1 = new ArrayList();
            }

        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 320);
        initComponents();
        frame.add(pan1);
        frame.setResizable(false);
    }

    @Override
    public void run() {
        frame.setVisible(true);
    }

    private void initComponents() {
        initSpinnerModel();
        initModel();
        initPan1_1();
        initPan1();
    }

    private void initSpinnerModel() {
        snm1 = new SpinnerNumberModel(1, 1, 10, 1);
        snm2 = new SpinnerNumberModel(1, 1, 10, 1);
    }

    private void initModel() {
        dtm1 = new Model1();
        dtm2 = new Model2();
    }

    private void initPan1() {
        pan1 = new JPanel();
        pan1.add(pan1_1);
        JButton jb1 = new JButton("Расчет");
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModelTable(frame, true, Model1.a1, Model2.a1);
            }
        });
        pan1.add(jb1);
    }

    private void initPan1_1() {
        initSpinners();
        initPan1_1_1();
        initTables();
        initPan1_1_2();
        pan1_1 = new JPanel();
        pan1_1.setLayout(new BorderLayout());
        pan1_1.add(BorderLayout.NORTH, pan1_1_1);
        pan1_1.add(BorderLayout.CENTER, pan1_1_2);
    }

    private void initPan1_1_2() {
        pan1_1_2 = new JPanel();
        pan1_1_2.add(new JScrollPane(t1));
        pan1_1_2.add(new JScrollPane(t2));
    }

    private void initTables() {
        t1 = new JTable(dtm1);
        t1.setPreferredScrollableViewportSize(new Dimension(250, 160));
        t2 = new JTable(dtm2);
        t2.setPreferredScrollableViewportSize(new Dimension(35, 160));
    }

    private void initSpinners() {
        sp1 = new JSpinner(snm1);
        sp1.addChangeListener(new Handler1());
        sp2 = new JSpinner(snm2);
        sp2.addChangeListener(new Handler2());
    }

    private void initPan1_1_1() {
        pan1_1_1 = new JPanel();
        pan1_1_1.add(new JLabel("Количество строк"));
        pan1_1_1.add(sp1);
        pan1_1_1.add(new JLabel("Количество столбцов"));
        pan1_1_1.add(sp2);
    }

    class Handler1 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            if ((int) sp1.getValue() >= count1) {
                dtm1.addRow();
                dtm2.addRow();
                count1++;
            } else {
                dtm1.removeRow();
                dtm2.removeRow();
                count1--;
            }
        }
    }

    class Handler2 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if ((int) sp2.getValue() >= count2) {
                dtm1.addColumn();
                count2++;
            } else {
                dtm1.removeColumn();
                count2--;
            }
        }
    }

}
