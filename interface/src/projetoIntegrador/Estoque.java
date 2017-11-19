package projetoIntegrador;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilidades.ExcecaoSql;
import utilidades.ValidacaoException;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Estoque extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable table;
	private TableModelProduto model;
	
	private void adicionarProduto() {
		String codigoProduto = JOptionPane.showInputDialog(rootPane, "Informe o código do produto");
		if (codigoProduto != null){
			try {
				model.adicionarProduto(codigoProduto);
			} catch (ExcecaoSql | ValidacaoException e) {
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}
		}
	}
	
	private void excluirProduto() {
		int row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		try {
			model.excluirProduto(row);
		} catch (ValidacaoException | ExcecaoSql e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage());
		}
	}
	
	public static Estoque abrirEstoque(TableModelProduto model) {
		Estoque frame = new Estoque();
		frame.model = model;
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
		button.setBounds(48, 422, 131, 25);
		contentPane.add(button);
		
		JScrollPane scrlPaneTable = new JScrollPane();
		scrlPaneTable.setBounds(48, 126, 420, 220);
		contentPane.add(scrlPaneTable);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneTable.setViewportView(table);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adicionarProduto();
			}
		});
		btnNovo.setFont(new Font("Candara", Font.BOLD, 14));
		btnNovo.setBackground(SystemColor.scrollbar);
		btnNovo.setBounds(48, 357, 85, 25);
		contentPane.add(btnNovo);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				excluirProduto();
			}
		});
		btnExcluir.setFont(new Font("Candara", Font.BOLD, 14));
		btnExcluir.setBackground(SystemColor.scrollbar);
		btnExcluir.setBounds(143, 357, 85, 25);
		contentPane.add(btnExcluir);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Estoque.class.getResource("arquivos/estoque.png")));
		lblNewLabel.setBounds(0, 0, 494, 529);
		contentPane.add(lblNewLabel);
	}
}
