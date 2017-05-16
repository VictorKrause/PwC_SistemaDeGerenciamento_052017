package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import business.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApeAdicionar extends JFrame {

	private JPanel contentPane;
	private DataManager data;
	private JTextField txtFieldFornecedor;
	private JTextField txtFieldDescricao;
	private JTextField txtFieldValor;
	private JFormattedTextField txtFieldDataVencimento;
	private JFormattedTextField txtFieldDataEnvio;
	private JTextField txtFieldNumPedido;
	private JLabel lblNDoPedido;
	private JTextField txtFieldNumBatch;
	private JLabel lblNDoBatch;
	private JButton btnAdicionar;
	private JComboBox comboBoxFormato = new JComboBox();

	public ApeAdicionar(DataManager data) {
		setTitle("Cadastrar Nova APE");
		this.data=data;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 316, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtFieldFornecedor = new JTextField();
		txtFieldFornecedor.setToolTipText("");
		txtFieldFornecedor.setColumns(10);

		JLabel lblFornecedor = new JLabel("Fornecedor:");

		txtFieldDescricao = new JTextField();
		txtFieldDescricao.setToolTipText("");
		txtFieldDescricao.setColumns(10);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");



		txtFieldValor = new JTextField();
		txtFieldValor.setText("R$ ");
		txtFieldValor.setToolTipText("");
		txtFieldValor.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");


		try {
			txtFieldDataEnvio = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtFieldDataEnvio.setToolTipText("");
			txtFieldDataEnvio.setHorizontalAlignment(SwingConstants.LEFT);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Falha na formatação da data de envio");
			txtFieldDataEnvio = new JFormattedTextField();
		}

		JLabel lblDataDeEnvio = new JLabel("Data de Envio:");


		try {
			txtFieldDataVencimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtFieldDataVencimento.setToolTipText("");
			txtFieldDataVencimento.setHorizontalAlignment(SwingConstants.LEFT);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Falha na formatação da data de Vencimento");
			txtFieldDataVencimento = new JFormattedTextField();
		}

		JLabel lblDataDeVenc = new JLabel("Data de Venc:");

		txtFieldNumPedido = new JTextField();
		txtFieldNumPedido.setColumns(10);

		lblNDoPedido = new JLabel("N\u00BA do Pedido:");

		txtFieldNumBatch = new JTextField();
		txtFieldNumBatch.setColumns(10);

		lblNDoBatch = new JLabel("N\u00BA do Batch:");


		comboBoxFormato.setModel(new DefaultComboBoxModel(new String[] {"Boleto", "Deposito"}));

		JLabel lblFormato = new JLabel("Formato: ");

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!temCampoVazio()){
					try {
						data.adicionarAPE(new APE(txtFieldDataEnvio.getText(),txtFieldDataVencimento.getText(), 
								txtFieldDescricao.getText(),comboBoxFormato.getSelectedItem().toString(),
								txtFieldValor.getText(),txtFieldNumPedido.getText(),txtFieldNumBatch.getText(),
								txtFieldFornecedor.getText()));
						txtFieldDataEnvio.setText("");
						txtFieldDataVencimento.setText("");
						txtFieldValor.setText("");
						txtFieldNumPedido.setText("");
						txtFieldNumBatch.setText("");
						txtFieldFornecedor.setText("");
						txtFieldDescricao.setText("");
						JOptionPane.showMessageDialog(null, "APE Adicionada!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Falha ao adicionar APE");
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
				}

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblFornecedor)
												.addComponent(lblDescrio)
												.addComponent(lblValor)
												.addComponent(lblDataDeEnvio)
												.addComponent(lblDataDeVenc)
												.addComponent(lblNDoPedido)
												.addComponent(lblNDoBatch)
												.addComponent(lblFormato))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtFieldNumPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtFieldDescricao, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(txtFieldValor, Alignment.LEADING)
														.addComponent(txtFieldDataEnvio, Alignment.LEADING)
														.addComponent(txtFieldDataVencimento, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(comboBoxFormato, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(txtFieldNumBatch, Alignment.LEADING))
												.addComponent(txtFieldFornecedor, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFornecedor)
								.addComponent(txtFieldFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescrio)
								.addComponent(txtFieldDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValor))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldDataEnvio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataDeEnvio))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldDataVencimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataDeVenc))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldNumPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNDoPedido))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldNumBatch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNDoBatch))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxFormato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFormato))
						.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				);
		contentPane.setLayout(gl_contentPane);
	}

	private boolean temCampoVazio(){
		boolean temCampoVazio = false;

		if(txtFieldFornecedor==null || txtFieldFornecedor.getText().equals("")
				|| txtFieldDescricao==null || txtFieldDescricao.getText().equals("")
				|| txtFieldValor==null || txtFieldValor.getText().equals("")
				|| txtFieldDataVencimento==null || txtFieldDataVencimento.getText().equals("")
				|| txtFieldDataEnvio==null || txtFieldDataEnvio.getText().equals("")
				|| txtFieldNumPedido==null || txtFieldNumPedido.getText().equals("")
				|| txtFieldNumBatch==null || txtFieldNumBatch.getText().equals(""))
			temCampoVazio=true;

		return temCampoVazio;
	}
}
