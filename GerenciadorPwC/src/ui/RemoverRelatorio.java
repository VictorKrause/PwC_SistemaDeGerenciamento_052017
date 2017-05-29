package ui;

import java.awt.BorderLayout; 
import business.APE;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.DataManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RemoverRelatorio extends JFrame {

	private JPanel contentPane;
	private DataManager data;
	private JTextField txtFieldID;
	
	/**
	 * Create the frame.
	 */
	public RemoverRelatorio(DataManager data) {
		setResizable(false);
		setTitle("Remover APE");
		this.data = data;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 76);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNumeroDoPedido = new JLabel("Numero do ID:");
		
		txtFieldID = new JTextField();
		txtFieldID.setToolTipText("Digite o numero do ID que deseja remover");
		txtFieldID.setColumns(10);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(data.removerRelatorio(Integer.parseInt(txtFieldID.getText())))
						JOptionPane.showMessageDialog(null, "Relatório removido");
					else
						JOptionPane.showMessageDialog(null, "Relatório não localizado");
					
					txtFieldID.setText("");
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Insira apenas o ID do relatório");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNumeroDoPedido)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFieldID, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRemover, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumeroDoPedido)
						.addComponent(txtFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemover))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
