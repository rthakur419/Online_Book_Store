package signup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.System.Logger;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JPasswordField pass;
	private JTextField address;
	private JPasswordField repass;
	private JTextField phoneno;

	/**
	 * Launch the application.
	 */
	 public static class MyConnection {
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
					signup frame = new signup();
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

	public signup() {
		setTitle("Online Book Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignup = new JLabel("SignUp");
		lblSignup.setBounds(241, 11, 215, 47);
		lblSignup.setFont(new Font("Cambria", Font.BOLD, 22));
		contentPane.add(lblSignup);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(145, 69, 48, 14);
		contentPane.add(lblName);
		
		name = new JTextField();
		name.setBounds(133, 84, 323, 31);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(145, 124, 48, 14);
		contentPane.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(133, 138, 323, 31);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(143, 236, 74, 14);
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(133, 249, 323, 31);
		contentPane.add(pass);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(145, 345, 74, 14);
		contentPane.add(lblAddress);
		
		address = new JTextField();
		address.setBounds(133, 357, 328, 75);
		contentPane.add(address);
		address.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Retype Pass");
		lblPhoneNo.setBounds(143, 289, 89, 14);
		contentPane.add(lblPhoneNo);
		
		JButton btnSignup = new JButton("SignUp");
		btnSignup.setBounds(241, 443, 89, 23);
		btnSignup.addActionListener(new ActionListener() 
				{
			   public boolean checkEmailId(String email)
			    {
			        PreparedStatement ps;
			        ResultSet rs;
			        boolean checkUser = false;
			        String query = "SELECT * FROM `customer` WHERE `EmailId` =?";
			        
			        try {
			            ps = MyConnection.getConnection().prepareStatement(query);
			            ps.setString(1, email);
			            
			            rs = ps.executeQuery();
			            
			            if(rs.next())
			            {
			                checkUser = true;
			            }
			        } catch(Exception ex){ System.out.println(ex);
			        }
			         return checkUser;
			    }
			public void actionPerformed(ActionEvent e) {
				   String uname = name.getText();
				   String phno = phoneno.getText();
			        String password = String.valueOf(pass.getPassword());
			        String repassword = String.valueOf(repass.getPassword());
			        String mail = email.getText();
			        String addr= address.getText();
			        if(uname.equals(""))
			        {
			            JOptionPane.showMessageDialog(null, "Add A Username");
			        }
			        else if(mail.equals(""))
			        { JOptionPane.showMessageDialog(null, "Add A Email");
			        }
			        else if(checkEmailId(mail))
			        {
			            JOptionPane.showMessageDialog(null, "This User Already Exist");
			        }
			        else if(((phno.length()) < 9) || ((phno.length()) > 11) ) {
			        	JOptionPane.showMessageDialog(null, "Incorrect Phone no.");
			        }
			        else if(password.equals(""))
			        {
			            JOptionPane.showMessageDialog(null, "Add A Password");
			        }
			        else if(!password.equals(repassword))
			        {
			            JOptionPane.showMessageDialog(null, " Password don't match");
			        }
			        else if(addr.equals(""))
			        {
			        	JOptionPane.showMessageDialog(null, "Enter your address");
			        }
			        else {
			        
			        PreparedStatement ps;
			        PreparedStatement pset;
			        PreparedStatement preset;
			        String query = "INSERT INTO `customer`(`name`, `address`, `emailid`, `password`,`phono`) VALUES (?,?,?,?,?)";
			        String sql = "INSERT INTO `home`( `signout`, `customer_EmailId`) VALUES (0,?)";
			        try {
			            ps = MyConnection.getConnection().prepareStatement(query);
			            ps.setString(1, uname);
			            ps.setString(2, addr);
			            ps.setString(3, mail);
			            ps.setString(4, password);
			            ps.setString(5, phno);
			           
			            pset = MyConnection.getConnection().prepareStatement(sql);
			            pset.setString(1, mail);
			            pset.executeUpdate();
			            if(ps.executeUpdate() > 0)
			            {
			                JOptionPane.showMessageDialog(null, "New User Add");
			                dispose();
							login cna = new login();
							cna.setVisible(true);
							cna.setLocationRelativeTo(null);
							cna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			            }
			            
			        } catch(Exception p){ System.out.println(p);
			        }
			        }
			}
			});
		contentPane.add(btnSignup);
		
		JLabel lblAlreadHaveAn = new JLabel("Alread have an account");
		lblAlreadHaveAn.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAlreadHaveAn.setBounds(184, 499, 132, 14);
		contentPane.add(lblAlreadHaveAn);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				login cna = new login();
				cna.setVisible(true);
				cna.setLocationRelativeTo(null);
				cna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   
			}
		});
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(326, 499, 56, 14);
		contentPane.add(lblNewLabel);
		
		repass = new JPasswordField();
		repass.setBounds(133, 303, 323, 31);
		contentPane.add(repass);
		
		phoneno = new JTextField();
		phoneno.setBounds(133, 194, 323, 31);
		contentPane.add(phoneno);
		phoneno.setColumns(10);
		
		JLabel lblPhoneNo_1 = new JLabel("Phone No.");
		lblPhoneNo_1.setBounds(143, 180, 100, 14);
		contentPane.add(lblPhoneNo_1);
	}

	public void setDefalutCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}
}
