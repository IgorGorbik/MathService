package MatrixCalculator;

import java.util.ArrayList;
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
public class Model2_1 extends DefaultTableModel {

    static ArrayList a1 = new ArrayList();
    
    Model2_1() {
        a1.add(new ArrayList());
        ((ArrayList)a1.get(0)).add("0");
        fireTableStructureChanged();
    }

    @Override
    public int getColumnCount() {
        return ((ArrayList)a1.get(0)).size();
    }

    @Override
    public int getRowCount() {
        return a1.size();
 
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        ((ArrayList)a1.get(row)).set(column, aValue);
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return ((ArrayList)a1.get(row)).get(column);
    }
    
    public void addColumn() {
        for(int i = 0;i < getRowCount();i++) {
            ((ArrayList)a1.get(i)).add("0");
        }
        fireTableStructureChanged();
    }
    
    public void removeColumn() {        
        for(int i = 0;i < getRowCount();i++) {
            ((ArrayList)a1.get(i)).remove(this.getColumnCount()-1);
        }
        fireTableStructureChanged();
    }

    public void addRow() {
        a1.add(new ArrayList());
        for(int i = 0;i < getColumnCount();i++) {
            ((ArrayList)a1.get(a1.size()-1)).add("0");
        }
        fireTableStructureChanged();
    }

    public void removeRow() {
        a1.remove(a1.size()-1);
        fireTableStructureChanged();
    }

}


