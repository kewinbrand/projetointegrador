package projetoIntegrador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
 

public class Menu extends JFrame {
	
	
	//Fun��o de tocar som
	public void tocarSom(String somCaixa)
	 {
	 java.net.URL url = getClass().getResource(somCaixa+".wav");
	 AudioClip audio = Applet.newAudioClip(url);
	 audio.play();
	 }

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
					 
					Menu frame = new Menu();
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
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("arquivos/carrinhoicon.jpg")));
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setSize(500,550);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Realizar Venda");
		btnNewButton.setForeground(SystemColor.activeCaptionText);
		btnNewButton.setBackground(SystemColor.scrollbar);
		btnNewButton.setFont(new Font("Candara", Font.BOLD, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Compra.main(null);
			}
		});
		btnNewButton.setBounds(60, 140, 155, 50);
		contentPane.add(btnNewButton);
		
		JButton btnCarrinhoDeCompras = new JButton("Carrinho");
		btnCarrinhoDeCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Carrinho.main(null);
			}
		});
		btnCarrinhoDeCompras.setBackground(SystemColor.scrollbar);
		btnCarrinhoDeCompras.setFont(new Font("Candara", Font.BOLD, 19));
		btnCarrinhoDeCompras.setBounds(60, 205, 155, 50);
		contentPane.add(btnCarrinhoDeCompras);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque.main(null);
			}
		});
		btnEstoque.setBackground(SystemColor.scrollbar);
		btnEstoque.setFont(new Font("Candara", Font.BOLD, 19));
		btnEstoque.setBounds(274, 140, 155, 50);
		contentPane.add(btnEstoque);
		
		JButton btnTamanhoDoCarrinho = new JButton("Tamanho");
		btnTamanhoDoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tamanho.main(null);
			}
		});
		btnTamanhoDoCarrinho.setBackground(SystemColor.scrollbar);
		btnTamanhoDoCarrinho.setFont(new Font("Candara", Font.BOLD, 19));
		btnTamanhoDoCarrinho.setToolTipText("");
		btnTamanhoDoCarrinho.setSelectedIcon(new ImageIcon());
		btnTamanhoDoCarrinho.setBounds(274, 205, 155, 50);
		contentPane.add(btnTamanhoDoCarrinho);
		
		JButton btnItem = new JButton("1\u00BA Item");
		btnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Primeiro.main(null);
			}
		});
		btnItem.setBackground(SystemColor.scrollbar);
		btnItem.setToolTipText("");
		btnItem.setFont(new Font("Candara", Font.BOLD, 19));
		btnItem.setBounds(60, 268, 155, 50);
		contentPane.add(btnItem);
		
		JButton btnGravarVenda = new JButton("Gravar Vendas");
		btnGravarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tocarSom("somcaixa");  //Chama a fun��o de tocar som
				
			}
		});
		btnGravarVenda.setBackground(SystemColor.scrollbar);
		btnGravarVenda.setToolTipText("");
		btnGravarVenda.setFont(new Font("Candara", Font.BOLD, 19));
		btnGravarVenda.setBounds(274, 268, 155, 50);
		contentPane.add(btnGravarVenda);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				dispose();
				
			}
		});
		btnSair.setBackground(SystemColor.scrollbar);
		btnSair.setToolTipText("");
		btnSair.setFont(new Font("Candara", Font.BOLD, 19));
		btnSair.setBounds(60, 331, 369, 24);
		contentPane.add(btnSair);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 500, 528);
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("arquivos/menu.png")));
		contentPane.add(lblNewLabel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, contentPane}));
	}
}