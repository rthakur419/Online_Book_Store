package Paymentmethod;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Confirmation.confirmation;
import cart.cart;
import home.home;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class paymentmethod extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paymentmethod frame = new paymentmethod();
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
	public paymentmethod() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseYourPayment = new JLabel("Choose Your Payment Method");
		lblChooseYourPayment.setBounds(10, 56, 271, 54);
		contentPane.add(lblChooseYourPayment);
		
		JRadioButton rdbtnCod = new JRadioButton("COD");
		rdbtnCod.setBounds(57, 157, 109, 23);
		contentPane.add(rdbtnCod);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCod.isSelected())
				{dispose();
				recheck con = new recheck();
				con.setVisible(true);
				con.setLocationRelativeTo(null);}
				else {
					JOptionPane.showMessageDialog(null, "Pls Select COD option as we currently suppot COD only.");
				}
			}
		});
		btnProceed.setBounds(188, 242, 89, 23);
		contentPane.add(btnProceed);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 281, 48);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Online book Store");
		panel.add(label);
		label.setForeground(Color.RED);
		label.setFont(new Font("Adobe Arabic", Font.PLAIN, 25));
		label.setBackground(Color.LIGHT_GRAY);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				cart c = new cart();
			    c.setVisible(true);
				c.setLocationRelativeTo(null);
				c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnBack.setBounds(10, 242, 95, 23);
		contentPane.add(btnBack);
	}
}
