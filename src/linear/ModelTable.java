package linear;

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
    
    double [][] res1;
    
    ModelTable(JFrame f, boolean b1, ArrayList a, ArrayList b) {
        
        super(f,b1);
        setTitle("Результат");
        setResizable(false);
        
        this.a = a;
        this.b = b;
        
        int m = a.size();
        int n = ((ArrayList)a.get(0)).size();

        res1 = new double[m][n + 1];
        
        resultTab(m, n, b, a);

        JTable t1 = new JTable(new Model3(met(res1)));
        t1.setPreferredScrollableViewportSize(new Dimension(250,160));
        
        add(new JScrollPane(t1));
        
        pack();
        setVisible(true);
  
    }

    private void resultTab(int m, int n, ArrayList b1, ArrayList a1) throws NumberFormatException {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == n) {
                    res1[i][j] += (Integer.valueOf((String) ((ArrayList) b1.get(i)).get(0)));
                } else {
                    res1[i][j] += (Integer.valueOf((String) ((ArrayList) a1.get(i)).get(j)));
                }
            }
        }
    }
    
    private static double[] met(double [][] res) {
        
 	double x[]=new double[res.length];
 	for (int i = 0; i < x.length; i++) {
            x[i] = res[i][res[i].length - 1];
 	}
 	double m;
 	for (int k = 1; k < res.length; k++) {
            for (int j = k; j < res.length; j++) {
 		m = res[j][k - 1] / res[k - 1][k - 1];
 		for (int i = 0; i < res[j].length; i++) {
                    res[j][i] = res[j][i] - m * res[k - 1][i];
 		}
                x[j] = x[j] - m * x[k - 1];
            }
 	}

 	for (int i=res.length-1;i>=0;i--) {
            for (int j=i+1;j<res.length;j++) x[i]-=res[i][j]*x[j];
            x[i] = x[i] / res[i][i];
 	}
 
// 	int t=(int) 'x';
// 	for (int i = 0; i < x.length; i++) {
//          System.out.println(((char)t++)+" = "+x[i]);
// 	}
                
        return x;

    }
    
}

