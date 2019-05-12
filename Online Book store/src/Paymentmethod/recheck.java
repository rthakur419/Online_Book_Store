package Paymentmethod;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Confirmation.confirmation;
import cart.cart;
import cart.cart.MyConnection;
import login.login;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class recheck extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField phno;
	private JTextField addr;

	/**
	 * Launch the application.
	 */
	public static class MyConnection{
		public static  Connection getConnection(){
	     
	        Connection con = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","1234567890");
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        
	        return con;
	    }
	    
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recheck frame = new recheck();
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
	public recheck() {
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String sss;
				String ss;
				String s;
				ResultSet rs = null;
				ResultSet rset = null;
				ResultSet rst = null;
				PreparedStatement ps;
				PreparedStatement pst;
				PreparedStatement pset;
		        String query = "Select customer_EmailId from home where signout=1" ;
		        String un ="";
		        String sql = "select name,phono,address from customer where emailid=?";
		        try {
					ps = MyConnection.getConnection().prepareStatement(query);
					rs=ps.executeQuery();
					if(rs.next()) {
						un=rs.getString("customer_EmailId");
					pst = MyConnection.getConnection().prepareStatement(sql);
					pst.setString(1,un);
					rset=pst.executeQuery();
					if(rset.next()) {
					sss=rset.getString("name");
					ss=rset.getString("phono");
					s=rset.getString("address");
			    	 name.setText(sss);
			    	 phno.setText(ss);
					addr.setText(s);
					}
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(10, 11, 89, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				paymentmethod c = new paymentmethod();
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 580, 48);
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Online book Store");
		panel.add(label);
		label.setForeground(Color.RED);
		label.setFont(new Font("Adobe Arabic", Font.PLAIN, 25));
		label.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Re-Check Details");
		lblNewLabel.setBounds(37, 59, 162, 27);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(141, 98, 183, 27);
		contentPane.add(name);
		name.setColumns(10);
		
		phno = new JTextField();
		phno.setBounds(141, 155, 183, 27);
		contentPane.add(phno);
		phno.setColumns(10);
		
		addr = new JTextField();
		addr.setBounds(139, 225, 340, 96);
		contentPane.add(addr);
		addr.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(71, 97, 50, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPhoneNo = new JLabel("Phone no.");
		lblPhoneNo.setBounds(71, 155, 60, 27);
		contentPane.add(lblPhoneNo);
		
		JLabel lblAddrress = new JLabel("Addrress");
		lblAddrress.setBounds(73, 225, 56, 27);
		contentPane.add(lblAddrress);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = name.getText();
				   String phono = phno.getText();
			        String addrr= addr.getText();
			        String mail="";
			        if(uname.equals(""))
			        {
			            JOptionPane.showMessageDialog(null, "Add A Username");
			        }
			        else if(((phono.length()) < 9) || ((phono.length()) > 11) ) {
			        	JOptionPane.showMessageDialog(null, "Incorrect Phone no.");
			        }
			        else if(addr.equals(""))
			        {
			        	JOptionPane.showMessageDialog(null, "Enter your address");
			        }
			        else {
			        ResultSet rs = null;
			        PreparedStatement ps;
			        PreparedStatement pset;
			        PreparedStatement preset;
			        PreparedStatement pst;
			        String qq = "Select customer_EmailId from home where signout=1" ;
			        String query = "update customer set name =?,phono=?,address=? where EmailId =?";
			        try {
			        	pst=MyConnection.getConnection().prepareStatement(qq);
			        	rs=pst.executeQuery();
			        	if(rs.next()) {
			        	mail=rs.getString("customer_EmailId");
			            ps = MyConnection.getConnection().prepareStatement(query);
			            ps.setString(1, uname);
			            ps.setString(2, phono);
			            ps.setString(3, addrr);
			            ps.setString(4, mail);
			            if(ps.executeUpdate() > 0)
			            {
			                dispose();
							confirmation cna = new confirmation();
							cna.setVisible(true);
							cna.setLocationRelativeTo(null);
							cna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			            }
			        	}
			            
			        } catch(Exception p){ System.out.println(p);
			        }
			        }
			}
		});
		btnSubmit.setBounds(250, 346, 89, 23);
		contentPane.add(btnSubmit);
	}
}
