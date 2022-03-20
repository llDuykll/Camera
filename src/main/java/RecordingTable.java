import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class RecordingTable extends JTable {

    private DefaultTableModel model;

    public RecordingTable() {
        this.model = new DefaultTableModel();
        this.model.addColumn("Time Started");
        this.model.addColumn("Duration");
        this.model.addColumn("Time Stopped");
        this.model.addRow(new String[] {"E", "$", "F"});
        this.setModel(model);

    }

}
