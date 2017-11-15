package projetoIntegrador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tamanho extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tamanho frame = new Tamanho();
					frame.setResizable(false); //Bloqueia o maximizar 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tamanho() {
		setTitle("TAMANHO DO CARRINHO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tamanho.class.getResource("arquivos/carrinhoicon.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 432, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
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
		button.setBounds(144, 215, 131, 25);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Tamanho.class.getResource("arquivos/tamanhocarrinho.png")));
		lblNewLabel.setBounds(0, 0, 432, 275);
		contentPane.add(lblNewLabel);
	}
}
