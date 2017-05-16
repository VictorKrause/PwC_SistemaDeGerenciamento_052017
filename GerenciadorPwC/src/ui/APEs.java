package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.*;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class APEs extends JFrame {

	private JPanel contentPane;
	private JTable tabelaAPEs;
	private DataManager data;
	private ArrayList<APE> apes;


	/**
	 * Create the frame.
	 */
	public APEs(DataManager data) {
		this.data = data;
		setTitle("APE's Enviadas");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 882, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ApeAdicionar JFrame = new ApeAdicionar(data);
				JFrame.setVisible(true);
			}
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoverAPE JFrame = new RemoverAPE(data);
				JFrame.setVisible(true);
			}
		});

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarAPE janela = new BuscarAPE(data);
				janela.setVisible(true);
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		

				DefaultTableModel model = (DefaultTableModel) tabelaAPEs.getModel();
				model.setRowCount(0);

				addRowToTable();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRemover, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAdicionar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)))
		);

		tabelaAPEs = new JTable();
		tabelaAPEs.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Fornecedor", "Descri\u00E7\u00E3o", "Valor (R$)", "Data Envio", "Data de Venc", "N\u00BA Pedido", "N\u00BA Batch", "Formato"
				}
				));
		tabelaAPEs.getColumnModel().getColumn(0).setPreferredWidth(121);
		tabelaAPEs.getColumnModel().getColumn(1).setPreferredWidth(149);
		scrollPane.setViewportView(tabelaAPEs);
		contentPane.setLayout(gl_contentPane);

		
	}
	
	public void addRowToTable(){
		try {
			data.load();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha na leitura");
			e.printStackTrace();

		}
		apes = data.getApes();
		DefaultTableModel model = (DefaultTableModel) tabelaAPEs.getModel();
		Object rowData[] = new Object[8];
		for(APE ape : apes){
			rowData[0] = ape.getFornecedor();
			rowData[1] = ape.getDescricao();
			rowData[2] = ape.getValor();
			rowData[3] = ape.getDataDeEnvio();
			rowData[4] = ape.getDataDeVencimento();
			rowData[5] = ape.getNumPedido();
			rowData[6] = ape.getNumBatch();
			rowData[7] = ape.getMeioDePagamento();
			model.addRow(rowData);
		}
	}
}
