  package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import business.APE;
import business.DataManager;
import business.Relatorio;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AdicionarRelatorio extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldCliente;
	private JTextField txtFieldDescricao;
	private JFormattedTextField txtFieldDataEmissao;
	private JTextField txtFieldSolicitante;
	private JLabel lblSolicitante;
	private JTextField txtFieldEntreguePara;
	private JLabel lblEntreguePara;
	private JTextField txtFieldQtdVias;
	private JLabel lblQuantidadeDeVias;
	private JButton btnAdicionar;
	private DataManager data;

	//Construtor usado para editar

	public AdicionarRelatorio(DataManager data, Relatorio relatorio){
		this.data = data;
		setTitle("Editar relatorio");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		setBounds(100, 100, 355, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtFieldCliente = new JTextField();
		txtFieldCliente.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		txtFieldDescricao = new JTextField();
		txtFieldDescricao.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		
		try {
			txtFieldDataEmissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtFieldDataEmissao=new JFormattedTextField();
		}
		
		txtFieldDataEmissao.setColumns(10);
		
		JLabel lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o:");
		
		txtFieldSolicitante = new JTextField();
		txtFieldSolicitante.setColumns(10);
		
		lblSolicitante = new JLabel("Solicitante:");
		
		txtFieldEntreguePara = new JTextField();
		txtFieldEntreguePara.setColumns(10);
		
		lblEntreguePara = new JLabel("Entregue para:");
		
		txtFieldQtdVias = new JTextField();
		txtFieldQtdVias.setColumns(10);
		
		lblQuantidadeDeVias = new JLabel("Qtd de vias:");
		
		btnAdicionar = new JButton("Editar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!temCampoVazio()){
						data.adicionarRelatorio(new Relatorio(relatorio.getId(), txtFieldCliente.getText(), txtFieldSolicitante.getText(), 
							txtFieldDescricao.getText(), txtFieldEntreguePara.getText(), txtFieldDataEmissao.getText(), txtFieldQtdVias.getText()));
						limparCampos();
						JOptionPane.showMessageDialog(null, "Relatorio editado!");
						setVisible(false);
					}
					else JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
					
				} catch (IOException e1) {
					JOptionPane.showInternalMessageDialog(null, "Falha ao setar ID");
					e1.printStackTrace();
				}
				
			}
		});
		
		txtFieldCliente.setText(relatorio.getCliente());
		txtFieldDataEmissao.setText(relatorio.getDataDeEmissao());
		txtFieldDescricao.setText(relatorio.getDescricao());
		txtFieldEntreguePara.setText(relatorio.getEntreguePara());
		txtFieldQtdVias.setText(relatorio.getQtdVias());
		txtFieldSolicitante.setText(relatorio.getSolicitante());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAdicionar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCliente)
										.addComponent(lblDescricao)
										.addComponent(lblDataDeEmisso)
										.addComponent(lblSolicitante)
										.addComponent(lblEntreguePara)))
								.addComponent(lblQuantidadeDeVias))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtFieldQtdVias, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldDataEmissao, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldSolicitante)
								.addComponent(txtFieldDescricao)
								.addComponent(txtFieldCliente, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(txtFieldEntreguePara))))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(txtFieldCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescricao)
						.addComponent(txtFieldDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeEmisso)
						.addComponent(txtFieldDataEmissao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSolicitante)
						.addComponent(txtFieldSolicitante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntreguePara)
						.addComponent(txtFieldEntreguePara, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidadeDeVias)
						.addComponent(txtFieldQtdVias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(btnAdicionar))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public AdicionarRelatorio(DataManager data) {
		this.data = data;
		setTitle("Adicionar relatorio");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		setBounds(100, 100, 355, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtFieldCliente = new JTextField();
		txtFieldCliente.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		txtFieldDescricao = new JTextField();
		txtFieldDescricao.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		
		try {
			txtFieldDataEmissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtFieldDataEmissao=new JFormattedTextField();
		}
		
		txtFieldDataEmissao.setColumns(10);
		
		JLabel lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o:");
		
		txtFieldSolicitante = new JTextField();
		txtFieldSolicitante.setColumns(10);
		
		lblSolicitante = new JLabel("Solicitante:");
		
		txtFieldEntreguePara = new JTextField();
		txtFieldEntreguePara.setColumns(10);
		
		lblEntreguePara = new JLabel("Entregue para:");
		
		txtFieldQtdVias = new JTextField();
		txtFieldQtdVias.setColumns(10);
		
		lblQuantidadeDeVias = new JLabel("Qtd de vias:");
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!temCampoVazio()){
						data.adicionarRelatorio(new Relatorio(data.setId(1), txtFieldCliente.getText(), txtFieldSolicitante.getText(), 
							txtFieldDescricao.getText(), txtFieldEntreguePara.getText(), txtFieldDataEmissao.getText(), txtFieldQtdVias.getText()));
						limparCampos();
						JOptionPane.showMessageDialog(null, "Relatorio adicionado!");
						Relatorios janelaAnterior = new Relatorios(data);
						janelaAnterior.attTabela();
					}
					else JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
					
				} catch (IOException e1) {
					JOptionPane.showInternalMessageDialog(null, "Falha ao setar ID");
					e1.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAdicionar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCliente)
										.addComponent(lblDescricao)
										.addComponent(lblDataDeEmisso)
										.addComponent(lblSolicitante)
										.addComponent(lblEntreguePara)))
								.addComponent(lblQuantidadeDeVias))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtFieldQtdVias, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldDataEmissao, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldSolicitante)
								.addComponent(txtFieldDescricao)
								.addComponent(txtFieldCliente, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(txtFieldEntreguePara))))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(txtFieldCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescricao)
						.addComponent(txtFieldDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeEmisso)
						.addComponent(txtFieldDataEmissao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSolicitante)
						.addComponent(txtFieldSolicitante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntreguePara)
						.addComponent(txtFieldEntreguePara, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidadeDeVias)
						.addComponent(txtFieldQtdVias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(btnAdicionar))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private boolean temCampoVazio(){
		boolean temCampoVazio = false;
		if(txtFieldCliente.getText().equals("")
				|| txtFieldDataEmissao.getText().equals("")
				|| txtFieldDescricao.getText().equals("")
				|| txtFieldEntreguePara.getText().equals("")
				|| txtFieldQtdVias.getText().equals("")
				|| txtFieldSolicitante.getText().equals(""))
			return true;
		
		
		return temCampoVazio;
	}
	
	private void limparCampos(){
		txtFieldCliente.setText("");
		txtFieldDataEmissao.setText("");
		txtFieldDescricao.setText("");
		txtFieldEntreguePara.setText("");
		txtFieldQtdVias.setText("");
		txtFieldSolicitante.setText("");
	}

}
