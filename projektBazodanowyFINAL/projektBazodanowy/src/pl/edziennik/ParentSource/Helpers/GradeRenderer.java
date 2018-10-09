package pl.edziennik.ParentSource.Helpers;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class GradeRenderer extends JLabel implements TableCellRenderer
{

    public GradeRenderer()
    {
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column)
    {

String val = (String) value;
if(val!=null) {
    if (val.charAt(1) == 'O') {
        super.setBackground(Color.GREEN);
        super.setText(val);


    } else {
        super.setBackground(Color.cyan);
        super.setText(val);
    }
}
else {
    super.setBackground(Color.WHITE);
    super.setText("");
}


        return this;
    }

}