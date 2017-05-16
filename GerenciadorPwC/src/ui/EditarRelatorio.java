package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import business.DataManager;
import business.Relatorio;
import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarRelatorio extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldID;

	public EditarRelatorio(DataManager data) {
		setTitle("Editar Relatorio");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 277, 81);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		txtFieldID = new JTextField();
		txtFieldID.setToolTipText("Digite o ID do relatorio que deseja editar");

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String numeroToString = txtFieldID.getText();
				try{
				Relatorio relatorio = data.buscarParaEditarRelatorio(Integer.parseInt(numeroToString));
				if(relatorio!=null){
					AdicionarRelatorio janela = new AdicionarRelatorio(data, relatorio);
					JOptionPane.showMessageDialog(null, "Relatório localizado\n"
							+ relatorio.getDescricao()+" do cliente "+relatorio.getCliente());
					janela.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null, "Relatório não localizado.");
				
				}
				catch(Exception e2){
					JOptionPane.showMessageDialog(null, "Insira apenas o numero ID do relatório.");					
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(56, Short.MAX_VALUE)
						.addComponent(txtFieldID, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnEditar)
						.addGap(34))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEditar)
								.addComponent(txtFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);
	}

}
