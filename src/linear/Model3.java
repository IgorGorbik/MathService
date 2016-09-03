package linear;

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

    double [] a;
    
    Model3(double [] a) {
        this.a = a;
        fireTableStructureChanged();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        if(a == null) {return 0;}
        return a.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(column == 0) {
            return row + 1;
        } else {
        return a[row];
        }
    }
 
}



