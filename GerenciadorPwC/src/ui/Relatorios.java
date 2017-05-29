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

public class Relatorios extends JFrame {

	private JPanel contentPane;
	private JTable tabelaRelatorios;
	private DataManager data;
	private ArrayList<Relatorio> relatorios;


	/**
	 * Create the frame.
	 */
	public Relatorios(DataManager data) {
		this.data = data;
		setTitle("Relatorios emitidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 882, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdicionarRelatorio JFrame = new AdicionarRelatorio(data);
				JFrame.setVisible(true);
			}
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoverRelatorio JFrame = new RemoverRelatorio(data);
				JFrame.setVisible(true);
			}
		});

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarRelatorio janela = new BuscarRelatorio(data);
				janela.setVisible(true);
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				attTabela();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarRelatorio janela = new EditarRelatorio(data);
				janela.setVisible(true);
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRemover, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnAdicionar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)))
		);

		tabelaRelatorios = new JTable();
		tabelaRelatorios.setEnabled(false);
		tabelaRelatorios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Cliente", "Descri\u00E7\u00E3o", "Data Emiss\u00E3o", "Solicitante", "Entregue para:", "Vias"
			}
		));
		tabelaRelatorios.getColumnModel().getColumn(0).setPreferredWidth(29);
		tabelaRelatorios.getColumnModel().getColumn(1).setPreferredWidth(129);
		tabelaRelatorios.getColumnModel().getColumn(2).setPreferredWidth(131);
		tabelaRelatorios.getColumnModel().getColumn(4).setPreferredWidth(110);
		tabelaRelatorios.getColumnModel().getColumn(5).setPreferredWidth(110);
		tabelaRelatorios.getColumnModel().getColumn(6).setPreferredWidth(38);
		scrollPane.setViewportView(tabelaRelatorios);
		contentPane.setLayout(gl_contentPane);

		
	}
	
	public void attTabela(){
		DefaultTableModel model = (DefaultTableModel) tabelaRelatorios.getModel();
		model.setRowCount(0);

		addRowToTable();
	}
	
	private void addRowToTable(){
		try {
			data.load();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha na leitura");
			e.printStackTrace();

		}
		relatorios = data.getRelatorios();
		DefaultTableModel model = (DefaultTableModel) tabelaRelatorios.getModel();
		Object rowData[] = new Object[7];
		for(Relatorio relatorio : relatorios){
			rowData[0]=relatorio.getId();
			rowData[1]=relatorio.getCliente();
			rowData[2]=relatorio.getDescricao();
			rowData[3]=relatorio.getDataDeEmissao();
			rowData[4]=relatorio.getSolicitante();
			rowData[5]=relatorio.getEntreguePara();
			rowData[6]=relatorio.getQtdVias();
			model.addRow(rowData);
		}
	}
}
