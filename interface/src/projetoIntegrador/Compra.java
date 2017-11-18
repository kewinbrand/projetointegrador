package projetoIntegrador;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Venda;
import sistemaVendas.SistemaVendas;
import utilidades.ValidacaoException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Compra extends JDialog {


	private static final long serialVersionUID = 1L;
	
	public JPanel contentPane;
	public JTextField textFieldQuantidade;
	public JTextField textFieldValor;
	public JTextField textFieldDesc;
	public JTextField textFieldAliq;
	public JTextField textFieldObs;
	public JComboBox<String> comboProduto;
	
	private SistemaVendas sistema;	
	
	public static Compra novaVenda(SistemaVendas sistema) {
		Compra frame = new Compra();
		frame.sistema = sistema;
		frame.setResizable(false);
		frame.setSize(500,550);
		return frame;
	}
	
	private void realizarVenda() {
		try {
			Venda venda = Venda.criarVenda(comboProduto.getName(), textFieldQuantidade.getText(), textFieldValor.getText(), textFieldDesc.getText(), 
					textFieldAliq.getText(), textFieldObs.getText());
			sistema.novaVenda(venda);
			JOptionPane.showMessageDialog(rootPane, "Venda realizada com sucesso.");
		} catch (ValidacaoException e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public Compra() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Compra.class.getResource("arquivos/carrinhoicon.jpg")));
		setTitle("COMPRE AQUI");
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setSize(500,550);
		contentPane.setLayout(null);
		
		comboProduto = new JComboBox<String>();
		comboProduto.setBounds(256, 150, 131, 22);
		contentPane.add(comboProduto);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(256, 190, 131, 22);
		contentPane.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);
		
		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(256, 230, 131, 22);
		contentPane.add(textFieldValor);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setColumns(10);
		textFieldDesc.setBounds(256, 270, 131, 22);
		contentPane.add(textFieldDesc);
		
		textFieldAliq = new JTextField();
		textFieldAliq.setColumns(10);
		textFieldAliq.setBounds(256, 310, 131, 22);
		contentPane.add(textFieldAliq);
		
		textFieldObs = new JTextField();
		textFieldObs.setColumns(10);
		textFieldObs.setBounds(256, 350, 131, 22);
		contentPane.add(textFieldObs);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBackground(SystemColor.scrollbar);
		btnVoltar.setFont(new Font("Candara", Font.BOLD, 14));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose(); 	//Esse dispose fecha a janela		
				
			}
		});
		
		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				realizarVenda();				
			}
		});
		btnFinalizar.setBackground(SystemColor.scrollbar);
		btnFinalizar.setFont(new Font("Candara", Font.BOLD, 14));
		btnFinalizar.setBounds(65, 389, 131, 25);
		contentPane.add(btnFinalizar);
		btnVoltar.setBounds(65, 426, 131, 25);
		contentPane.add(btnVoltar);
		
		JLabel lblObservao = new JLabel("OBSERVA\u00C7\u00C3O");
		lblObservao.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservao.setFont(new Font("Candara", Font.BOLD, 19));
		lblObservao.setBounds(45, 349, 159, 27);
		contentPane.add(lblObservao);
		
		JLabel lblValorUnitrio = new JLabel("PRODUTO");
		lblValorUnitrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorUnitrio.setFont(new Font("Candara", Font.BOLD, 19));
		lblValorUnitrio.setBounds(45, 149, 159, 27);
		contentPane.add(lblValorUnitrio);
		
		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Candara", Font.BOLD, 19));
		lblQuantidade.setBounds(45, 189, 159, 27);
		contentPane.add(lblQuantidade);
		
		JLabel label_2 = new JLabel("VALOR UNIT\u00C1RIO");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Candara", Font.BOLD, 19));
		label_2.setBounds(45, 229, 159, 27);
		contentPane.add(label_2);
		
		JLabel lblDesconto = new JLabel("DESCONTO");
		lblDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesconto.setFont(new Font("Candara", Font.BOLD, 19));
		lblDesconto.setBounds(45, 269, 159, 27);
		contentPane.add(lblDesconto);
		
		JLabel lblAliquotaIcms = new JLabel("ALIQUOTA ICMS");
		lblAliquotaIcms.setHorizontalAlignment(SwingConstants.CENTER);
		lblAliquotaIcms.setFont(new Font("Candara", Font.BOLD, 19));
		lblAliquotaIcms.setBounds(45, 309, 159, 27);
		contentPane.add(lblAliquotaIcms);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Compra.class.getResource("arquivos/compreaqui.png")));
		lblNewLabel.setBounds(0, 0, 500, 529);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(88, 160, 56, 16);
		contentPane.add(label);
		
	}
}
