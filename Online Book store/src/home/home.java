package home;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cart.cart;
import login.login;
import signup.signup.MyConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.border.BevelBorder;

    

public class home extends JFrame {
public String name="";
	private JPanel contentPane;
	private JTextField sresult;
    private JTextField sresult1;
    private JTextField sresult2;
    private JLabel sri1;
    private JTextField uname;
    String s1="";
	String s2="";
	String s3="";
	String s4="";
	String s5="";
	String s6="";
    String a1="";
	String a2="";
	String a3="";
	String a4="";
	String a5="";
	String a6="";
	private JTextField author1;
	private JTextField author2;
	private JTextField author3;
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
					home frame = new home();
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
	public class Function{
	       Connection con = null;
	       ResultSet rs = null;
	       PreparedStatement ps =null;
	       public ResultSet find(String s){
	           try{
	   
	           ps = MyConnection.getConnection().prepareStatement("select bname,author from book where bname = ?");
	           ps.setString(1,s);
	           rs = ps.executeQuery();
	           }catch(Exception ex){
	              JOptionPane.showMessageDialog(null, ex.getMessage());
	           }
	           return rs;
	       }
	       
	   }
	public class Username{
	       Connection con = null;
	       ResultSet rs = null;
	       PreparedStatement ps =null;
	       public ResultSet find(String s){
	           try{
	   
	           ps = MyConnection.getConnection().prepareStatement("select name from customer where email=?");
	           ps.setString(1,s);
	           rs = ps.executeQuery();
	           }catch(Exception ex){
	              JOptionPane.showMessageDialog(null, ex.getMessage());
	           }
	           return rs;
	       }
	       
	   }
	public home() {
		setUndecorated(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String sss;
				ResultSet rs = null;
				ResultSet rset = null;
				PreparedStatement ps;
				PreparedStatement pst;
		        String query = "Select customer_EmailId from home where signout=1" ;
		        String un ="";
		        String sql = "select name from customer where emailid=?";
		        try {
		        	  sresult.setText("Let us c++");
			    	  author1.setText("Yashwant Kanetkar");
			    	  sresult1.setText("Core Java");
			    	  author2.setText("R. Najeswara Rao");
			    	  sresult2.setText("DBMS");
			    	  author3.setText("Rajiv Chopra");
			    	  
					ps = MyConnection.getConnection().prepareStatement(query);
					rs=ps.executeQuery();
					if(rs.next()) {
						un=rs.getString("customer_EmailId");
					System.out.println(un);
					pst = MyConnection.getConnection().prepareStatement(sql);
					pst.setString(1,un);
					rset=pst.executeQuery();
					if(rset.next()) {
					sss=rset.getString("name");
					System.out.println(sss);
			    	 uname.setText(sss);
					}
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		
		setTitle("Home-OnlineBookStore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel signout = new JLabel("Signout");
		signout.setBounds(746, 11, 73, 22);
		signout.setBackground(Color.WHITE);
		signout.setVerticalAlignment(SwingConstants.TOP);
		signout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs = null;
				PreparedStatement ps;
				String query=("Update onlinebookstore.home set signout =0  where signout=1;");
				try {
					ps = MyConnection.getConnection().prepareStatement(query);
					
					if(ps.executeUpdate()>0){
						JOptionPane.showMessageDialog(null,"Thank you for shopping here");
						dispose();
						login cna = new login();
						cna.setVisible(true);
						cna.setLocationRelativeTo(null);
						cna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(signout);
		
		JLabel lblNewLabel = new JLabel("Online book Store");
		lblNewLabel.setBounds(34, 0, 146, 48);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Adobe Arabic", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.RED);
		contentPane.add(lblNewLabel);
		
		TextField sbook = new TextField();
		sbook.setBounds(189, 113, 381, 48);
		sbook.setFont(new Font("Cambria", Font.PLAIN, 20));
		sbook.setBackground(Color.WHITE);
		contentPane.add(sbook);
		
		Button search = new Button("Search");
		search.setBounds(333, 171, 70, 22);
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				 Function f = new Function();
				    ResultSet rs = null;
				    rs = f.find(sbook.getText());
				    
				    try{
				    	
				      if(rs.next()){
				    	  if(s1.isEmpty()==true && a1.isEmpty()==true) {  
				    	  s1=rs.getString("bname");
				    	  a1=rs.getString("author");
				    	  System.out.println(a1);
				    	  sresult.setText(s1);
				    	  author1.setText(a1);
				    	  }
				    	  else if(s1.isEmpty()==false && a1.isEmpty()==false)
				    	  {
				    		  if(s2.isEmpty()==true && a2.isEmpty()==true) {
						    	  s2=rs.getString("bname");
						    	  a2=rs.getString("author");
						    	  sresult1.setText(s2);
						    	  author2.setText(a2);}
						    	  else if(s2.isEmpty()==false && a2.isEmpty()==false)
						    	  {
						    		  if(s3.isEmpty()==true && a3.isEmpty()==true) {
								    	  s3=rs.getString("bname");
								    	  a3=rs.getString("author");
								    	  sresult2.setText(s3);
								    	  author3.setText(a3);}
						    		  
						    		  else if(s3.isEmpty()==false && a3.isEmpty()==false)
							    	  {
					
							    		  if(s4.isEmpty()==true && a4.isEmpty()==true) {
									    	  s4=rs.getString("bname");
									    	  a1=rs.getString("author");
									    	  sresult.setText(s4);
									    	  author1.setText(a1);}
									    	  else if(s4.isEmpty()==false && a4.isEmpty()==false)
									    	  {									    	  
									    		  if(s5.isEmpty()==true && a5.isEmpty()==true) {
											    	  s5=rs.getString("bname");
											    	  a2=rs.getString("author");
											    	  sresult1.setText(s5);
											    	  author2.setText(a2);}
									    		  else if(s5.isEmpty()==false && a5.isEmpty()==false) {
									    			  s6=rs.getString("bname");
									    			  a3=rs.getString("author");
									    			  sresult2.setText(s6);
									    			  author3.setText(a3);
									    		  }
						    	  }
				    	  }
						    	  }
				    	  }
				      }
				      else{
				          JOptionPane.showMessageDialog(null, "No Book Found");
				      } 
				    }
				    	catch(Exception ex){
				           JOptionPane.showMessageDialog(null, ex.getMessage());
				            }
			}
		});
		contentPane.add(search);
		
		sresult = new JTextField();
		sresult.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sresult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs;
				ResultSet rst;
				ResultSet rs1;
				PreparedStatement ps;
				PreparedStatement ps1;
				PreparedStatement pst;
				PreparedStatement pset;
		        String query = "Select customer_EmailId from home where signout=1;" ;
		        String sql="Select price from book where bname='"+sresult.getText()+"';" ;
		        String qw="Select quantity from cart where books='"+sresult.getText()+"';" ;
		        String mail ="";
		        int rate;
		        int quant;
		        int tp;
		        String w=("update cart set quantity=?,Total_Price=? where books='"+sresult.getText()+"';");
		        String q=("INSERT INTO `cart`( `customer_EmailId`,`books`,`price`,`signout`,`Quantity`,`Total_Price`) VALUES (?,'"+sresult.getText()+"',?,1,1,?);");
				try {
					ps1 = MyConnection.getConnection().prepareStatement(qw);
					rs1=ps1.executeQuery();
					ps = MyConnection.getConnection().prepareStatement(query);
					rs=ps.executeQuery();
					pset = MyConnection.getConnection().prepareStatement(sql);
					rst=pset.executeQuery();
					
					if(rst.next() && rs.next()) {
						rate = rst.getInt("price");
						
					mail=rs.getString("customer_EmailId");
					if(rs1.next() ) {
						quant=rs1.getInt("Quantity");
						if(quant>0) {
						quant=quant+1;
						tp=quant*rate;
						pst = MyConnection.getConnection().prepareStatement(w);
						pst.setInt(1,quant);
						pst.setInt(2,tp);
						if(pst.executeUpdate()>0){
							JOptionPane.showMessageDialog(null,"Book Added to Cart");
						}
						}
					}
					else{
					pst = MyConnection.getConnection().prepareStatement(q);
					pst.setString(1,mail);
					pst.setInt(2,rate);
					pst.setInt(3, rate);
					if(pst.executeUpdate()>0){
						JOptionPane.showMessageDialog(null,"Book Added to Cart");
					}
					}
				}
				} catch (SQLException e1) {
						System.out.println(e1);
					}
			
			}
		});
		sresult.setEditable(false);
		sresult.setBounds(44, 282, 194, 30);
		sresult.setFont(new Font("Cambria", Font.PLAIN, 20));
		contentPane.add(sresult);
		sresult.setColumns(10);
		
		sresult1 = new JTextField();
		sresult1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sresult1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs;
				ResultSet rst;
				ResultSet rs1;
				PreparedStatement ps;
				PreparedStatement ps1;
				PreparedStatement pst;
				PreparedStatement pset;
		        String query = "Select customer_EmailId from home where signout=1;" ;
		        String sql="Select price from book where bname='"+sresult1.getText()+"';" ;
		        String qw="Select quantity from cart where books='"+sresult1.getText()+"';" ;
		        String mail ="";
		        int rate;
		        int quant;
		        int tp;
		        String w=("update cart set quantity=?,Total_Price=? where books='"+sresult1.getText()+"';");
		        String q=("INSERT INTO `cart`( `customer_EmailId`,`books`,`price`,`signout`,`Quantity`,`Total_Price`) VALUES (?,'"+sresult1.getText()+"',?,1,1,?);");
				try {
					ps1 = MyConnection.getConnection().prepareStatement(qw);
					rs1=ps1.executeQuery();
					ps = MyConnection.getConnection().prepareStatement(query);
					rs=ps.executeQuery();
					pset = MyConnection.getConnection().prepareStatement(sql);
					rst=pset.executeQuery();
					
					if(rst.next() && rs.next()) {
						rate = rst.getInt("price");
						
					mail=rs.getString("customer_EmailId");
					if(rs1.next() ) {
						quant=rs1.getInt("Quantity");
						if(quant>0) {
						quant=quant+1;
						tp=quant*rate;
						pst = MyConnection.getConnection().prepareStatement(w);
						pst.setInt(1,quant);
						pst.setInt(2,tp);
						if(pst.executeUpdate()>0){
							JOptionPane.showMessageDialog(null,"Book Added to Cart");
						}
						}
					}
					else{
					pst = MyConnection.getConnection().prepareStatement(q);
					pst.setString(1,mail);
					pst.setInt(2,rate);
					pst.setInt(3, rate);
					if(pst.executeUpdate()>0){
						JOptionPane.showMessageDialog(null,"Book Added to Cart");
					}
					}
				}
				} catch (SQLException e1) {
						System.out.println(e1);
					}
			}
		});
		sresult1.setEditable(false);
		sresult1.setBounds(303, 282, 194, 30);
		sresult1.setFont(new Font("Cambria", Font.PLAIN, 20));
		sresult1.setColumns(10);
		sresult1.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(sresult1);
		
		sresult2 = new JTextField();
		sresult2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sresult2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs;
				ResultSet rst;
				ResultSet rs1;
				PreparedStatement ps;
				PreparedStatement ps1;
				PreparedStatement pst;
				PreparedStatement pset;
		        String query = "Select customer_EmailId from home where signout=1;" ;
		        String sql="Select price from book where bname='"+sresult2.getText()+"';" ;
		        String qw="Select quantity from cart where books='"+sresult2.getText()+"';" ;
		        String mail ="";
		        int rate;
		        int quant;
		        int tp;
		        String w=("update cart set quantity=?,Total_Price=? where books='"+sresult2.getText()+"';");
		        String q=("INSERT INTO `cart`( `customer_EmailId`,`books`,`price`,`signout`,`Quantity`,`Total_Price`) VALUES (?,'"+sresult2.getText()+"',?,1,1,?);");
				try {
					ps1 = MyConnection.getConnection().prepareStatement(qw);
					rs1=ps1.executeQuery();
					ps = MyConnection.getConnection().prepareStatement(query);
					rs=ps.executeQuery();
					pset = MyConnection.getConnection().prepareStatement(sql);
					rst=pset.executeQuery();
					
					if(rst.next() && rs.next()) {
						rate = rst.getInt("price");
						
					mail=rs.getString("customer_EmailId");
					if(rs1.next() ) {
						quant=rs1.getInt("Quantity");
						if(quant>0) {
						quant=quant+1;
						tp=quant*rate;
						pst = MyConnection.getConnection().prepareStatement(w);
						pst.setInt(1,quant);
						pst.setInt(2,tp);
						if(pst.executeUpdate()>0){
							JOptionPane.showMessageDialog(null,"Book Added to Cart");
						}
						}
					}
					else{
					pst = MyConnection.getConnection().prepareStatement(q);
					pst.setString(1,mail);
					pst.setInt(2,rate);
					pst.setInt(3, rate);
					if(pst.executeUpdate()>0){
						JOptionPane.showMessageDialog(null,"Book Added to Cart");
					}
					}
				}
				} catch (SQLException e1) {
						System.out.println(e1);
					}
			}
		});
		sresult2.setEditable(false);
		sresult2.setBounds(569, 282, 218, 30);
		sresult2.setFont(new Font("Cambria", Font.PLAIN, 20));
		sresult2.setColumns(10);
		sresult2.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(sresult2);
		
		uname = new JTextField();
		uname.setEditable(false);
		uname.setFont(new Font("Tahoma", Font.PLAIN, 11));
		uname.setBorder(null);
		uname.setBackground(UIManager.getColor("Button.background"));
		uname.setBounds(640, 8, 96, 20);
		contentPane.add(uname);
		uname.setColumns(10);
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				cart crt = new cart();
				crt.setVisible(true);
				crt.setLocationRelativeTo(null);
				crt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		lblCart.setBounds(593, 11, 34, 14);
		contentPane.add(lblCart);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 832, 48);
		contentPane.add(panel);
		
		Label label = new Label("Book Name");
		label.setBackground(UIManager.getColor("Button.background"));
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(44, 246, 146, 30);
		contentPane.add(label);
		
		author1 = new JTextField();
		author1.setBorder(null);
		author1.setBackground(UIManager.getColor("Button.background"));
		author1.setEditable(false);
		author1.setBounds(44, 347, 194, 30);
		contentPane.add(author1);
		author1.setColumns(10);
		
		author2 = new JTextField();
		author2.setBorder(null);
		author2.setBackground(UIManager.getColor("Button.background"));
		author2.setEditable(false);
		author2.setColumns(10);
		author2.setBounds(303, 347, 194, 30);
		contentPane.add(author2);
		
		author3 = new JTextField();
		author3.setBorder(null);
		author3.setBackground(UIManager.getColor("Button.background"));
		author3.setEditable(false);
		author3.setColumns(10);
		author3.setBounds(570, 348, 217, 29);
		contentPane.add(author3);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAuthor.setBounds(47, 323, 153, 16);
		contentPane.add(lblAuthor);
		
		JLabel label_1 = new JLabel("Author");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_1.setBounds(303, 320, 153, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Author");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		label_2.setBounds(569, 323, 153, 16);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Book Name");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_3.setBackground(SystemColor.menu);
		label_3.setBounds(303, 246, 146, 30);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Book Name");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_4.setBackground(SystemColor.menu);
		label_4.setBounds(569, 246, 146, 30);
		contentPane.add(label_4);
	}
}
