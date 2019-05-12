package cart;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import signup.signup.MyConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Paymentmethod.paymentmethod;
import home.home;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.List;

import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Choice;
import javax.swing.JProgressBar;

public class cart extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblTotalAmount;
	private JTextField total;

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
					cart frame = new cart();
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

	public cart() {
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String sss;
				ResultSet rs = null;
				ResultSet rst= null;
				ResultSet rset = null;
				PreparedStatement ps;
				PreparedStatement pset;
				PreparedStatement pst;
				String totalprice;
		        String query = "Select customer_EmailId from home where signout=1" ;
		        String un ="";
		        String sql = "select Books,Price,Quantity,Total_Price from cart where customer_EmailId=?";
		        String q ="SELECT SUM(Total_Price)FROM cart WHERE  customer_EmailId=?;";
		        try {
					ps = MyConnection.getConnection().prepareStatement(query);
					rs=ps.executeQuery();
					
					if(rs.next()) {
						un=rs.getString("customer_EmailId");
					pst = MyConnection.getConnection().prepareStatement(sql);
					pst.setString(1,un);
					rset=pst.executeQuery();
					pset = MyConnection.getConnection().prepareStatement(q);
					pset.setString(1,un);
					rst=pset.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rset));
					if(rst.next())
					{
						totalprice= rst.getString("SUM(Total_Price)");
						total.setText(totalprice);
					}
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 391);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JButton Buyn = new JButton("Buy Now");
		Buyn.setBounds(322, 357, 89, 23);
		Buyn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				paymentmethod pm = new paymentmethod();
				pm.setVisible(true);
				pm.setLocationRelativeTo(null);
				pm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(Buyn);
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.setFont(new Font("Cambria", Font.BOLD, 20));
		lblCart.setBounds(178, 51, 51, 32);
		contentPane.add(lblCart);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(24, 94, 375, 125);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBorder(null);
		table.setBackground(UIManager.getColor("Button.background"));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book", "Price","Quantity","Total_Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setBounds(24, 230, 107, 32);
		contentPane.add(lblTotalAmount);
		
		JButton btnNewButton = new JButton("Add Books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				home h = new home();
				h.setVisible(true);
				h.setLocationRelativeTo(null);
				h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});
		btnNewButton.setBounds(24, 298, 107, 23);
		contentPane.add(btnNewButton);
		
		total = new JTextField();
		total.setBorder(null);
		total.setBackground(UIManager.getColor("Button.background"));
		total.setBounds(147, 230, 107, 32);
		contentPane.add(total);
		total.setColumns(10);
		
		JButton btnRemoveBooks = new JButton("Remove Book");
		btnRemoveBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> myoptions = new ArrayList<String>();
				String mail;
				ResultSet rset = null;
				ResultSet rs =null;
				PreparedStatement ps;
				PreparedStatement pset;
				String sql = "Select customer_emailid from home where signout = 1 ";
				String query ="Select Books From cart where customer_emailid=?";
				try {
					pset = MyConnection.getConnection().prepareStatement(sql);
					rset = pset.executeQuery();
					if(rset.next()) {
					mail = rset.getString("customer_emailid");
					
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, mail);
				 rs = ps.executeQuery();
					while(rs.next()) {
						 myoptions.add(rs.getString("Books")); 
					}
					}
				}
				catch(Exception e1){
					System.out.println(e1);
					
				}
				Object[] options = myoptions.toArray();
				String ans = JOptionPane.showInputDialog(null,"Select Book","Remove Book",JOptionPane.QUESTION_MESSAGE,null,options,myoptions.get(0)).toString();
				ResultSet rs1;
				PreparedStatement ps1;
		        String q=("delete from cart where books=?");
				try {
					ps1 = MyConnection.getConnection().prepareStatement(q);
					ps1.setString(1, ans);
					if( ps1.executeUpdate()>0){
						JOptionPane.showMessageDialog(null,"Book has been removed from the cart");
						dispose();
						cart c = new cart();
						c.setVisible(true);
						c.setLocationRelativeTo(null);
						c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}  
				}
				 catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnRemoveBooks.setBounds(250, 298, 132, 23);
		contentPane.add(btnRemoveBooks);
		
		JLabel label = new JLabel("Online book Store");
		label.setForeground(Color.RED);
		label.setFont(new Font("Adobe Arabic", Font.PLAIN, 25));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(133, 0, 172, 40);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 454, 40);
		contentPane.add(panel);
		table.getColumnModel().getColumn(1).setMinWidth(25);

	}
}
