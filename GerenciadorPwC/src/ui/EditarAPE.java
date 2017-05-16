package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.APE;
import business.DataManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarAPE extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldNumPedido;

	public EditarAPE(DataManager data) {
		setResizable(false);
		setTitle("Editar APE");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 337, 82);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				APE ape = data.buscarParaEditarAPE(txtFieldNumPedido.getText());
				if(ape != null){
					JOptionPane.showMessageDialog(null, "APE localizada!\n"
							+ "Fornecedor: "+ape.getFornecedor());
					AdicionarAPE janela = new AdicionarAPE(data, ape);
					janela.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "APE não localizada!");
				}

}
		});

		txtFieldNumPedido = new JTextField();
		txtFieldNumPedido.setToolTipText("Digite o Numero do Pedido que deseja editar");
		txtFieldNumPedido.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(21)
						.addComponent(txtFieldNumPedido, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addGap(27)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(44, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFieldNumPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEditar))
						.addContainerGap(14, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
	}
}
