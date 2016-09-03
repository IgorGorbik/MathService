package MatrixCalculator;

import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Игорь
 */
public class Model3 extends DefaultTableModel {

    int [][] a;
    
    Model3(int [][] a) {
        this.a = a;
        fireTableStructureChanged();
    }

    @Override
    public int getColumnCount() {
        return a[0].length;
    }

    @Override
    public int getRowCount() {
        if(a == null) {return 0;}
        return a.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return a[row][column];
    }
 
}


