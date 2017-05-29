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
import business.Relatorio;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarRelatorio extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldParametroDeBusca;
	private JTable tabelaResultado;
	private DataManager data;
	private ArrayList<Relatorio> resultadoBusca;

	public BuscarRelatorio(DataManager data) {
		this.data = data;
		setTitle("Buscar Relatorios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		txtFieldParametroDeBusca.setToolTipText("Preencha o nome do cliente ou data de emiss\u00E3o (##/##/####)");
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
				"ID", "Cliente", "Descri\u00E7\u00E3o", "Data Emiss\u00E3o", "Solicitante", "Entregue para:", "Vias"
			}
		));
		tabelaResultado.getColumnModel().getColumn(0).setPreferredWidth(33);
		tabelaResultado.getColumnModel().getColumn(1).setPreferredWidth(108);
		tabelaResultado.getColumnModel().getColumn(2).setPreferredWidth(146);
		tabelaResultado.getColumnModel().getColumn(4).setPreferredWidth(114);
		tabelaResultado.getColumnModel().getColumn(5).setPreferredWidth(110);
		tabelaResultado.getColumnModel().getColumn(6).setPreferredWidth(35);
		scrollPane.setViewportView(tabelaResultado);
		contentPane.setLayout(gl_contentPane);

	}

	public void addRowToTable(String parametro) throws IOException{
		data.load();
		resultadoBusca = data.buscarRelatorios(parametro);
		DefaultTableModel model = (DefaultTableModel) tabelaResultado.getModel();
		Object rowData[] = new Object[8];
		for(Relatorio relatorio : resultadoBusca){
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
