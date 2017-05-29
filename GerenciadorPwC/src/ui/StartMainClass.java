package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.DataManager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;

public class StartMainClass extends JFrame {

	private JPanel contentPane;
	private DataManager data; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMainClass frame = new StartMainClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartMainClass() {
		data = new DataManager();
		setResizable(false);
		setTitle("PwC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 166, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnApes = new JButton("APE's");
		btnApes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				APEs JFrame = new APEs(data);
				JFrame.setVisible(true);
					
			}
		});
		
		JButton btnRelatorios = new JButton("Relatorios");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios janela = new Relatorios(data);
				janela.setVisible(true);
			}
		});
		
		JLabel lblVtkrause = new JLabel("Victor Krause");
		lblVtkrause.setForeground(Color.LIGHT_GRAY);
		lblVtkrause.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnRelatorios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnApes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
						.addComponent(lblVtkrause))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(btnApes, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRelatorios, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(lblVtkrause))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
