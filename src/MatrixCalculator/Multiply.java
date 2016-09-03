package MatrixCalculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
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
import parser.XMLMenuLoader;

/**
 *
 * @author Игорь
 */
public class Multiply implements Runnable {

    private UIManager.LookAndFeelInfo l[];

    private JFrame frame;
    private JPanel pan1;
    private JPanel pan2;
    private JPanel pan1_1;
    private JPanel pan1_2;
    private JPanel pan1_1_1;
    private JPanel pan1_2_1;
    private JPanel pan1_1_2;
    private JPanel pan1_2_2;
    private JPanel pan2_1;
    private JPanel pan2_2;
    private JPanel pan2_1_1;
    private JPanel pan2_2_1;
    private JPanel pan2_1_2;
    private JPanel pan2_2_2;
    private JSpinner sp1;
    private JSpinner sp2;
    private JSpinner sp3;
    private JSpinner sp4;
    private JSpinner sp2_1;
    private JSpinner sp2_2;
    private JSpinner sp2_3;
    private JSpinner sp2_4;
    private JTable t1;
    private JTable t2;

    private SpinnerNumberModel snm1;
    private SpinnerNumberModel snm2;
    private SpinnerNumberModel snm3;
    private SpinnerNumberModel snm4;
    private SpinnerNumberModel snm2_1;
    private SpinnerNumberModel snm2_2;
    private SpinnerNumberModel snm2_3;
    private SpinnerNumberModel snm2_4;

    private int count1 = 1;
    private int count2 = 1;
    private int count3 = 1;
    private int count4 = 1;

    private int count2_1 = 1;
    private int count2_2 = 1;
    private int count2_3 = 1;
    private int count2_4 = 1;

    private Model1 dtm1;
    private Model2 dtm2;
    private Model2_1 dtm2_1;
    private Model2_2 dtm2_2;

    private int n3 = 1;

    private JButton jb2;

    public Multiply() {

        l = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(l[1].getClassName());
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
        frame.setSize(750, 335);

        try {
            String str = "/resources/menucalc.xml";
            InputStream stream = getClass().getResourceAsStream(str);
            //InputStream stream = new FileInputStream(str);
            XMLMenuLoader loader = new XMLMenuLoader(stream);
            loader.parse();
            frame.setJMenuBar(loader.getMenuBar("mainMenu"));
            loader.addActionListener("addition", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jb2.setText("Добавить");
                    n3 = 2;
                    pan1.setVisible(false);
                    pan2.setVisible(true);
                    frame.add(pan2);
                }
            });

            loader.addActionListener("mult", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    n3 = 1;
                    pan2.setVisible(false);
                    pan1.setVisible(true);
                }
            });

            loader.addActionListener("subtraction", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jb2.setText("Вычесть");
                    n3 = 3;
                    pan1.setVisible(false);
                    pan2.setVisible(true);
                    frame.add(pan2);
                }
            });

            loader.addActionListener("exit", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        initComponents();

        frame.add(pan1);

        frame.setResizable(false);

    }

    private void initComponents() {

        initSpinnerModel();
        initModel();
        initPan1_1();
        initPan1_2();
        initPan2_1();
        initPan2_2();
        initPan1();
        initPan2();

    }

    private void initPan2() {
        pan2 = new JPanel();
        pan2.add(pan2_1);
        pan2.add(pan2_2);
        jb2 = new JButton();
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModelTable1(frame, true, Model2_1.a1, Model2_2.a1, n3);
            }
        });
        pan2.add(jb2);
    }

    private void initModel() {

        dtm1 = new Model1();
        dtm2 = new Model2();

        dtm2_1 = new Model2_1();
        dtm2_2 = new Model2_2();

    }

    private void initSpinnerModel() {

        snm1 = new SpinnerNumberModel(1, 1, 10, 1);
        snm2 = new SpinnerNumberModel(1, 1, 10, 1);
        snm3 = new SpinnerNumberModel(1, 1, 10, 1);
        snm4 = new SpinnerNumberModel(1, 1, 10, 1);

        snm2_1 = new SpinnerNumberModel(1, 1, 10, 1);
        snm2_2 = new SpinnerNumberModel(1, 1, 10, 1);
        snm2_3 = new SpinnerNumberModel(1, 1, 10, 1);
        snm2_4 = new SpinnerNumberModel(1, 1, 10, 1);

    }

    private void initPan1() {

        pan1 = new JPanel();
        pan1.add(pan1_1);
        pan1.add(pan1_2);
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

        sp1 = new JSpinner(snm1);
        sp1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ((int) sp1.getValue() < count1) {
                    dtm1.removeRow();
                    count1--;
                } else {
                    dtm1.addRow();
                    count1++;
                }
            }
        });

        sp2 = new JSpinner(snm2);
        sp2.addChangeListener(abc1);

        pan1_1_1 = new JPanel();
        pan1_1_1.add(new JLabel("Количество строк"));
        pan1_1_1.add(sp1);
        pan1_1_1.add(new JLabel("Количество столбцов"));
        pan1_1_1.add(sp2);

        t1 = new JTable(dtm1);
        t1.setPreferredScrollableViewportSize(new Dimension(250, 160));

        pan1_1_2 = new JPanel();
        pan1_1_2.add(new JScrollPane(t1));

        pan1_1 = new JPanel();
        pan1_1.setLayout(new BorderLayout());
        pan1_1.add(BorderLayout.NORTH, pan1_1_1);
        pan1_1.add(BorderLayout.CENTER, pan1_1_2);

    }

    private void initPan2_1() {

        sp2_1 = new JSpinner(snm2_1);
        sp2_1.addChangeListener(abc2_1);

        sp2_2 = new JSpinner(snm2_2);
        sp2_2.addChangeListener(abc2);

        pan2_1_1 = new JPanel();
        pan2_1_1.add(new JLabel("Количество строк"));
        pan2_1_1.add(sp2_1);
        pan2_1_1.add(new JLabel("Количество столбцов"));
        pan2_1_1.add(sp2_2);

        t1 = new JTable(dtm2_1);
        t1.setPreferredScrollableViewportSize(new Dimension(250, 160));

        pan2_1_2 = new JPanel();
        pan2_1_2.add(new JScrollPane(t1));

        pan2_1 = new JPanel();
        pan2_1.setLayout(new BorderLayout());
        pan2_1.add(BorderLayout.NORTH, pan2_1_1);
        pan2_1.add(BorderLayout.CENTER, pan2_1_2);

    }

    private void initPan1_2() {

        sp3 = new JSpinner(snm3);
        sp3.addChangeListener(abc1);

        sp4 = new JSpinner(snm4);
        sp4.addChangeListener(abc);

        pan1_2_1 = new JPanel();
        pan1_2_1.add(new JLabel("Количество строк"));
        pan1_2_1.add(sp3);
        pan1_2_1.add(new JLabel("Количество столбцов"));
        pan1_2_1.add(sp4);

        t2 = new JTable(dtm2);
        t2.setPreferredScrollableViewportSize(new Dimension(250, 160));

        pan1_2_2 = new JPanel();
        pan1_2_2.add(new JScrollPane(t2));

        pan1_2 = new JPanel();
        pan1_2.setLayout(new BorderLayout());
        pan1_2.add(BorderLayout.NORTH, pan1_2_1);
        pan1_2.add(BorderLayout.CENTER, pan1_2_2);

    }

    private void initPan2_2() {

        sp2_3 = new JSpinner(snm2_3);
        sp2_3.addChangeListener(abc2_1);

        sp2_4 = new JSpinner(snm2_4);
        sp2_4.addChangeListener(abc2);

        pan2_2_1 = new JPanel();
        pan2_2_1.add(new JLabel("Количество строк"));
        pan2_2_1.add(sp2_3);
        pan2_2_1.add(new JLabel("Количество столбцов"));
        pan2_2_1.add(sp2_4);

        t2 = new JTable(dtm2_2);
        t2.setPreferredScrollableViewportSize(new Dimension(250, 160));

        pan2_2_2 = new JPanel();
        pan2_2_2.add(new JScrollPane(t2));

        pan2_2 = new JPanel();
        pan2_2.setLayout(new BorderLayout());
        pan2_2.add(BorderLayout.NORTH, pan2_2_1);
        pan2_2.add(BorderLayout.CENTER, pan2_2_2);

    }

    A5A abc = new A5A();
    A5B abc1 = new A5B();

    A52A abc2 = new A52A();
    A52B abc2_1 = new A52B();

    @Override
    public void run() {
        frame.setVisible(true);
    }

    class A5A implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            if ((int) sp4.getValue() >= count4) {
                dtm2.addColumn();
                count4++;
            } else {
                dtm2.removeColumn();
                count4--;
            }
        }
    }

    class A52A implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            if (e.getSource() == sp2_2) {
                sp2_4.removeChangeListener(this);
                sp2_4.setValue(sp2_2.getValue());
            } else {
                sp2_2.removeChangeListener(this);
                sp2_2.setValue(sp2_4.getValue());
            }

            if ((int) sp2_4.getValue() >= count2_4) {
                dtm2_1.addColumn();
                dtm2_2.addColumn();
                count2_4++;
            } else {
                dtm2_1.removeColumn();
                dtm2_2.removeColumn();
                count2_4--;
            }

            if (e.getSource() == sp2_2) {
                sp2_4.addChangeListener(this);
            } else {
                sp2_2.addChangeListener(this);
            }

        }
    }

    class A5B implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            if (e.getSource() == sp2) {
                sp3.removeChangeListener(this);
                sp3.setValue(sp2.getValue());
            } else {
                sp2.removeChangeListener(this);
                sp2.setValue(sp3.getValue());
            }

            if ((int) sp3.getValue() >= count3) {
                dtm1.addColumn();
                dtm2.addRow();
                count3++;
            } else {
                dtm1.removeColumn();
                dtm2.removeRow();
                count3--;
            }

            if (e.getSource() == sp2) {
                sp3.addChangeListener(this);
            } else {
                sp2.addChangeListener(this);
            }

        }
    }

    class A52B implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            if (e.getSource() == sp2_1) {
                sp2_3.removeChangeListener(this);
                sp2_3.setValue(sp2_1.getValue());
            } else {
                sp2_1.removeChangeListener(this);
                sp2_1.setValue(sp2_3.getValue());
            }

            if ((int) sp2_3.getValue() >= count2_3) {
                dtm2_1.addRow();
                dtm2_2.addRow();
                count2_3++;
            } else {
                dtm2_1.removeRow();
                dtm2_2.removeRow();
                count2_3--;
            }

            if (e.getSource() == sp2_1) {
                sp2_3.addChangeListener(this);
            } else {
                sp2_1.addChangeListener(this);
            }

        }
    }

}
