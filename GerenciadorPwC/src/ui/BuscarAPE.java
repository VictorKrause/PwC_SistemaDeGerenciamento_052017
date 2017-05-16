package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.APE;
import business.DataManager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarAPE extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldParametroDeBusca;
	private JTable tabelaResultado;
	private DataManager data;
	private ArrayList<APE> resultadoBusca;

	public BuscarAPE(DataManager data) {
		this.data = data;
		setTitle("Buscar APEs");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 644, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tabelaResultado.getModel();
				model.setRowCount(0);
				if(!txtFieldParametroDeBusca.getText().equals("")){
					try {
						addRowToTable(txtFieldParametroDeBusca.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally{ JOptionPane.showMessageDialog(null, "Busca Concluida!\n"+resultadoBusca.size()+" resultados encontrados.");
								txtFieldParametroDeBusca.setText("");}
				}
				else {JOptionPane.showMessageDialog(null, "Parametro invalido");
				txtFieldParametroDeBusca.setText("");
				}
			}
		});

		txtFieldParametroDeBusca = new JTextField();
		txtFieldParametroDeBusca.setToolTipText("Preencha o Numero do Batch, Numero do Pedido ou Nome do Fornecedor");
		txtFieldParametroDeBusca.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(txtFieldParametroDeBusca, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnBuscar))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldParametroDeBusca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
				);

		tabelaResultado = new JTable();
		tabelaResultado.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Fornecedor", "Descri\u00E7\u00E3o", "Valor (R$)", "Data Envio", "Data de Venc", "N\u00BA Pedido", "N\u00BA Batch", "Formato"
				}
				));
		tabelaResultado.getColumnModel().getColumn(0).setPreferredWidth(121);
		tabelaResultado.getColumnModel().getColumn(1).setPreferredWidth(149);
		scrollPane.setViewportView(tabelaResultado);
		contentPane.setLayout(gl_contentPane);

	}

	public void addRowToTable(String parametro) throws IOException{
		data.load();
		resultadoBusca = data.buscarApes(parametro);
		DefaultTableModel model = (DefaultTableModel) tabelaResultado.getModel();
		Object rowData[] = new Object[8];
		for(APE ape : resultadoBusca){
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
