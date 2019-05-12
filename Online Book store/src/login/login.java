package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import home.home;
import signup.signup;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setResizable(false);
		setTitle("Online Book Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Calibri", Font.BOLD, 22));
		lblLogin.setBounds(204, 11, 66, 31);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setBounds(147, 58, 72, 22);
		contentPane.add(lblUsername);
		
		user = new JTextField();
		user.setBounds(137, 80, 230, 36);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(147, 127, 72, 22);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mail = user.getText();
				try{  
					PreparedStatement ps = null;
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/onlinebookstore","root","1234567890");   
					Statement stmt=con.createStatement();
					String sql=("select * from customer where emailid ='"+user.getText()+"' and password = '"+new String(pass.getPassword())+"'");  
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next()) { 
						stmt.executeUpdate("Update onlinebookstore.home set signout =1  where customer_EmailId ='"+user.getText()+"';");
						stmt.executeUpdate("Update onlinebookstore.cart set signout =1  where customer_EmailId ='"+user.getText()+"';");
						
						dispose();
						home h = new home();
						h.setVisible(true);
						h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect user or password");
					con.close();  
					} catch(Exception p){ System.out.println(p);}
			}
		});
		btnLogin.setBounds(204, 213, 89, 23);
		contentPane.add(btnLogin);
		
		pass = new JPasswordField();
		pass.setBounds(137, 145, 230, 36);
		contentPane.add(pass);
		
		JLabel cna = new JLabel("Create a New Account");
		cna.addMouseListener(new MouseAdapter() {
			@Override
			public  void mouseClicked(MouseEvent e) {
				dispose();
				signup cna = new signup();
				cna.setVisible(true);
				cna.setLocationRelativeTo(null);
				cna.setDefalutCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		});
		cna.setForeground(Color.BLUE);
		cna.setFont(new Font("Calibri", Font.PLAIN, 12));
		cna.setBounds(192, 247, 123, 31);
		contentPane.add(cna);
	}
}
