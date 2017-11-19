package projetoIntegrador;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VendasGravadas extends JDialog {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTable table;

	public static VendasGravadas mostrarVendasGravadas() {
		VendasGravadas frame = new VendasGravadas();
		frame.setResizable(false);
		frame.setSize(500,550);
		return frame;
	}
	
	public VendasGravadas() {
		setTitle("Carrinho");
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VendasGravadas.class.getResource("arquivos/carrinhoicon.jpg")));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 	//Esse dispose fecha a janela	
			}
		});
		button.setFont(new Font("Candara", Font.BOLD, 14));
		button.setBackground(SystemColor.scrollbar);
		button.setBounds(56, 421, 131, 25);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 126, 420, 220);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VendasGravadas.class.getResource("arquivos/carrinho.png")));
		lblNewLabel.setBounds(0, 0, 493, 530);
		contentPane.add(lblNewLabel);
	}
}
