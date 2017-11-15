package projetoIntegrador;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class Carrinho extends JFrame {

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
					Carrinho frame = new Carrinho();
					frame.setResizable(false); //Bloqueia o maximizar 
					frame.setVisible(true);
					frame.setSize(500,550);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Carrinho() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Carrinho.class.getResource("/proejto/itegrador/carrinhoicon.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		button.setBounds(68, 435, 131, 25);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Carrinho.class.getResource("/proejto/itegrador/carrinho.png")));
		lblNewLabel.setBounds(0, 0, 493, 530);
		contentPane.add(lblNewLabel);
	}
}
