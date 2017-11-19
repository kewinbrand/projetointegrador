package projetoIntegrador;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class Estoque extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable table;
	
	
	public static Estoque abrirEstoque() {
		Estoque frame = new Estoque();
		frame.setResizable(false);
		frame.setSize(500,550);
		return frame;
	}

	/**
	 * Create the frame.
	 */
	public Estoque() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estoque.class.getResource("arquivos/carrinhoicon.jpg")));
		setTitle("Estoque");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setFont(new Font("Candara", Font.BOLD, 14));
		button.setBackground(SystemColor.scrollbar);
		button.setBounds(56, 421, 131, 25);
		contentPane.add(button);
		
		JScrollPane scrlPaneTable = new JScrollPane();
		scrlPaneTable.setBounds(48, 126, 420, 220);
		contentPane.add(scrlPaneTable);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneTable.setViewportView(table);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Estoque.class.getResource("arquivos/estoque.png")));
		lblNewLabel.setBounds(0, 0, 494, 529);
		contentPane.add(lblNewLabel);
	}
}
