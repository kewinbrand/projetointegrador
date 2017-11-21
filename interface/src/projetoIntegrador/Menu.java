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
	private JButton btnNewButton;
	private JButton btnCarrinhoDeCompras;
	private JButton btnEstoque;
	private JButton btnTamanhoDoCarrinho;
	private JButton btnItem;
	private JButton btnGravarVenda;
	private JButton btnSair;
	private JButton btnRecriar;
	private JButton btnVendasGravadas;
	
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
	
	private void recriarBancoDados() {
		try {
			if(JOptionPane.showConfirmDialog(rootPane, "Você tem certeza?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				MenuAplicacao.recriarBancoDados();
			}
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
		try {
			String itensCarrinho = String.valueOf(MenuAplicacao.quantidadeItensCarrinho()+1);
			JOptionPane.showMessageDialog(rootPane, "Atualmente existem "+itensCarrinho+" itens no carrinho.", "Itens do Carrinho", DISPOSE_ON_CLOSE, null);
		} catch (Exception e) {
			defaultExceptionHandler(e);
		}		
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
			MenuAplicacao.gravarVendas();
			Utilidades.tocarSom(getClass().getResource("arquivos/somcaixa.wav").toString().replace("file:/", ""));
		} catch (Exception e) {
			defaultExceptionHandler(e);
		}
	}
	
	private void mostrarVendasGravadas() {
		try {
			MenuAplicacao.abrirVendasGravadas();
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
		
		
		btnNewButton = new JButton("Realizar Venda");
		btnNewButton.setForeground(SystemColor.activeCaptionText);
		btnNewButton.setBackground(SystemColor.scrollbar);
		btnNewButton.setFont(new Font("Candara", Font.BOLD, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				btnNewButton.setEnabled(false);
				try {
					abrirVendas();
				} finally {
					btnNewButton.setEnabled(true);
				}			
			}
		});
		btnNewButton.setBounds(60, 109, 155, 50);
		contentPane.add(btnNewButton);
		
		btnCarrinhoDeCompras = new JButton("Carrinho");
		btnCarrinhoDeCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCarrinhoDeCompras.setEnabled(false);
				try {
					abrirCarrinho();
				} finally {
					btnCarrinhoDeCompras.setEnabled(true);
				}
			}
		});
		btnCarrinhoDeCompras.setBackground(SystemColor.scrollbar);
		btnCarrinhoDeCompras.setFont(new Font("Candara", Font.BOLD, 19));
		btnCarrinhoDeCompras.setBounds(60, 175, 155, 50);
		contentPane.add(btnCarrinhoDeCompras);
		
	    btnEstoque = new JButton("Estoque");
		btnEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEstoque.setEnabled(false);
				try {
					abrirEstoque();
				} finally {
					btnEstoque.setEnabled(true);
				}
			}
		});
		btnEstoque.setBackground(SystemColor.scrollbar);
		btnEstoque.setFont(new Font("Candara", Font.BOLD, 19));
		btnEstoque.setBounds(274, 109, 155, 50);
		contentPane.add(btnEstoque);
		
		btnTamanhoDoCarrinho = new JButton("Tamanho");
		btnTamanhoDoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTamanhoDoCarrinho.setEnabled(false);
				try {
					abrirQuantidadeItensCarrinho();
				} finally {
					btnTamanhoDoCarrinho.setEnabled(true);
				}
			}
		});
		btnTamanhoDoCarrinho.setBackground(SystemColor.scrollbar);
		btnTamanhoDoCarrinho.setFont(new Font("Candara", Font.BOLD, 19));
		btnTamanhoDoCarrinho.setBounds(274, 175, 155, 50);
		contentPane.add(btnTamanhoDoCarrinho);
		
		btnItem = new JButton("1\u00BA Item");
		btnItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnItem.setEnabled(false);
				try {
					abrirPrimeiroItemCarrinho();
				} finally {
					btnItem.setEnabled(true);
				}
			}
		});
		btnItem.setBackground(SystemColor.scrollbar);
		btnItem.setFont(new Font("Candara", Font.BOLD, 19));
		btnItem.setBounds(60, 235, 155, 50);
		contentPane.add(btnItem);
		
		btnGravarVenda = new JButton("Gravar Vendas");
		btnGravarVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnGravarVenda.setEnabled(false);
				try {
					gravarVendasBancoDados();
				} finally {
					btnGravarVenda.setEnabled(true);
				}
			}
		});
		
		btnGravarVenda.setBackground(SystemColor.scrollbar);
		btnGravarVenda.setFont(new Font("Candara", Font.BOLD, 19));
		btnGravarVenda.setBounds(274, 235, 155, 50);
		contentPane.add(btnGravarVenda);
		
		btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAplicacao.sair();
				dispose();
			}
		});
		btnSair.setBackground(SystemColor.scrollbar);
		btnSair.setFont(new Font("Candara", Font.BOLD, 19));
		btnSair.setBounds(60, 350, 369, 24);
		contentPane.add(btnSair);
		
		btnRecriar = new JButton("Recriar Database");
		btnRecriar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRecriar.setBackground(SystemColor.scrollbar);
		btnRecriar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnRecriar.setEnabled(false);
				try {
					recriarBancoDados();
				} finally {
					btnRecriar.setEnabled(true);
				}
			}
		});
		btnRecriar.setBounds(10, 477, 131, 23);
		contentPane.add(btnRecriar);
		
		btnVendasGravadas = new JButton("Vendas Gravadas");
		btnVendasGravadas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnVendasGravadas.setEnabled(false);
				try {
					mostrarVendasGravadas();
				} finally {
					btnVendasGravadas.setEnabled(true);
				}
			}
		});
		btnVendasGravadas.setFont(new Font("Candara", Font.BOLD, 19));
		btnVendasGravadas.setBackground(SystemColor.scrollbar);
		btnVendasGravadas.setBounds(139, 293, 194, 50);
		contentPane.add(btnVendasGravadas);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 500, 528);
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("arquivos/menu.png")));
		contentPane.add(lblNewLabel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, contentPane}));
	}
}
