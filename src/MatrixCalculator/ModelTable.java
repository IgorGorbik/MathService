package MatrixCalculator;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Игорь
 */
public class ModelTable extends JDialog {
    
    ArrayList a;
    ArrayList b;
    
    ModelTable(JFrame f, boolean b1, ArrayList a, ArrayList b) {
        
        super(f,b1);
        setTitle("Результат");
        setResizable(false);
        
        this.a = a;
        this.b = b;
        
        int m = a.size();
        int n = ((ArrayList)b.get(0)).size();
        int o = b.size();
        
        int [][] res = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += (Integer.valueOf((String)((ArrayList)a.get(i)).get(k))) * 
                                (Integer.valueOf(((String)((ArrayList)b.get(k)).get(j))));
                }
            }
        }
        
        JTable t1 = new JTable(new Model3(res));
        t1.setPreferredScrollableViewportSize(new Dimension(250,160));
        
        add(new JScrollPane(t1));
        
        pack();
        setVisible(true);
  
    }
    
}
