package projetoIntegrador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import utilidades.ExcecaoSql;
import utilidades.Utilidades;
import utilidades.ValidacaoException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 

public class Menu extends JFrame {
	
	
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
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void defaultExceptionHandler(Exception exception) {
		StringBuilder excptMessage = new StringBuilder();
		if((exception instanceof ExcecaoSql) || (exception instanceof ValidacaoException)) {
			excptMessage.append(exception.getMessage());
			JOptionPane.showMessageDialog(rootPane, excptMessage.toString(), "Verifique", JOptionPane.INFORMATION_MESSAGE);
		}else {			
			excptMessage.append(String.format("Deu ruim!!!!!!!!!!!!!!!!!!!!!!!!!!!! %n %s", exception.getMessage()));		
			JOptionPane.showMessageDialog(rootPane, excptMessage.toString(), "Ovos não eram pra ser verdes...", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void abrirVendas() {
		try {
			MenuAplicacao.abrirVendas();
		} catch (Exception e) {
			defaultExceptionHandler(e);
		}
	}
	
	private void conectarBancoDados() {
		try {
			MenuAplicacao.recriarBancoDados();
		} catch (Exception e) {
			defaultExceptionHandler(e);
		}
	}
	
	private void abrirEstoque() {
		try {
			MenuAplicacao.abrirEstoque();
		} catch (Exception e) {
			defaultExceptionHandler(e);
		}
	}
	
	private void abrirCarrinho() {
		try {
			MenuAplicacao.abrirCarrinho();
		}
		catch (Exception e) {
			defaultExceptionHandler(e);
		}
	}
	
	private void abrirQuantidadeItensCarrinho() {
		String itensCarrinho = String.valueOf(MenuAplicacao.quantidadeItensCarrinho());
		JOptionPane.showMessageDialog(rootPane, "Atualmente existem "+itensCarrinho+" itens no carrinho.", "Itens do Carrinho", DISPOSE_ON_CLOSE, null);
	}
	
	private void abrirPrimeiroItemCarrinho() {
		try {
			MenuAplicacao.abrirPrimeiroItemCarrinho();
		}
		catch (Exception e) {
			defaultExceptionHandler(e);
		}
	}
	
	private void gravarVendasBancoDados() {
		try {
			Utilidades.tocarSom(getClass().getResource("arquivos/somcaixa.wav").toString().replace("file:/", ""));
		} catch (Exception e) {
			defaultExceptionHandler(e);
		}
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
				abrirVendas();				
			}
		});
		btnNewButton.setBounds(60, 140, 155, 50);
		contentPane.add(btnNewButton);
		
		JButton btnCarrinhoDeCompras = new JButton("Carrinho");
		btnCarrinhoDeCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirCarrinho();
			}
		});
		btnCarrinhoDeCompras.setBackground(SystemColor.scrollbar);
		btnCarrinhoDeCompras.setFont(new Font("Candara", Font.BOLD, 19));
		btnCarrinhoDeCompras.setBounds(60, 205, 155, 50);
		contentPane.add(btnCarrinhoDeCompras);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirEstoque();
			}
		});
		btnEstoque.setBackground(SystemColor.scrollbar);
		btnEstoque.setFont(new Font("Candara", Font.BOLD, 19));
		btnEstoque.setBounds(274, 140, 155, 50);
		contentPane.add(btnEstoque);
		
		JButton btnTamanhoDoCarrinho = new JButton("Tamanho");
		btnTamanhoDoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirQuantidadeItensCarrinho();
			}
		});
		btnTamanhoDoCarrinho.setBackground(SystemColor.scrollbar);
		btnTamanhoDoCarrinho.setFont(new Font("Candara", Font.BOLD, 19));
		btnTamanhoDoCarrinho.setToolTipText("");
		btnTamanhoDoCarrinho.setSelectedIcon(new ImageIcon());
		btnTamanhoDoCarrinho.setBounds(274, 205, 155, 50);
		contentPane.add(btnTamanhoDoCarrinho);
		
		JButton btnItem = new JButton("1\u00BA Item");
		btnItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPrimeiroItemCarrinho();
			}
		});
		btnItem.setBackground(SystemColor.scrollbar);
		btnItem.setToolTipText("");
		btnItem.setFont(new Font("Candara", Font.BOLD, 19));
		btnItem.setBounds(60, 268, 155, 50);
		contentPane.add(btnItem);
		
		JButton btnGravarVenda = new JButton("Gravar Vendas");
		btnGravarVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gravarVendasBancoDados();
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
		
		JButton btnRecriar = new JButton("Recriar Database");
		btnRecriar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				conectarBancoDados();
			}
		});
		btnRecriar.setBounds(10, 477, 115, 23);
		contentPane.add(btnRecriar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 500, 528);
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("arquivos/menu.png")));
		contentPane.add(lblNewLabel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, contentPane}));
	}
}
