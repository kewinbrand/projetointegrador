package projetoIntegrador;

import java.awt.Color;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.border.*;

public class CellEditorInteger extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	
	private static final Border red = new LineBorder(Color.red);
	
	public CellEditorInteger(JTextField textField) {
		super(textField);
		this.textField = textField;
	}
	
	@Override
    public boolean stopCellEditing() {
        @SuppressWarnings("unused")
		int v = 0;
		try {
            v = Integer.valueOf(textField.getText());
        } catch (NumberFormatException e) {
            textField.setBorder(red);
            return false;
        }
        return super.stopCellEditing();
    }

}
