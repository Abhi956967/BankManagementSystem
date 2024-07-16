package bank;
import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener  {
	// Instanse Variable
	JLabel lblWelcome,lblCardNumber,lblPinNumber;
	JTextField tfCardNumber;
	JPasswordField pfPinNumber;
	
	JButton btnLogin,btnClear,btnSignup;
	
	// Non param Constructer
	
	public
	
	Login() {
		setTitle("Bank Management System");
		// to disable the default layout of frame
		setUndecorated(true);
		setVisible(true);
		setLayout(null);
		
		
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
		Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel lblImage=new JLabel(i3);
		lblImage.setBounds(70, 10, 100, 100);
		add(lblImage);
		
		lblWelcome=new JLabel("Welcome To bank System");
		lblWelcome.setFont(new Font("Arial",Font.BOLD,35));
		lblWelcome.setBounds(200,40,500,40);
		add(lblWelcome);
		
		
		lblCardNumber=new JLabel("Enter Card No:");
		lblCardNumber.setFont(new Font("Tahoma",Font.BOLD,25));
		lblCardNumber.setBounds(120,150,400,30);
		add(lblCardNumber);
		
		
		tfCardNumber=new JTextField(20);
		tfCardNumber.setBounds(350,150,230,30);
		tfCardNumber.setFont(new Font("Tahoma",Font.BOLD,15));
		add(tfCardNumber);
		
		
		
		lblPinNumber=new JLabel("Enter PIN Number");
		lblPinNumber.setFont(new Font("Tahoma",Font.BOLD,25));
		lblPinNumber.setBounds(120,250,400,30);
		add(lblPinNumber);
		
		

		pfPinNumber=new JPasswordField(20);
		pfPinNumber.setBounds(350,250,230,30);
		pfPinNumber.setFont(new Font("Tahoma",Font.BOLD,15));
		add(pfPinNumber);
		
		btnLogin=new JButton("Login.");
		btnLogin.setBackground(Color.black);
		btnLogin.setForeground(Color.white);
		
		btnClear=new JButton("Clear");
		btnClear.setBackground(Color.black);
		btnClear.setForeground(Color.white);
		
		btnSignup=new JButton("Sign Up.");
		btnSignup.setBackground(Color.black);
		btnSignup.setForeground(Color.white);
		
		
		btnLogin.setFont(new Font ("Tahoma",Font.BOLD,15));
		btnLogin.setBounds(300,300,100,40);
		add(btnLogin);
		
		btnClear.setFont(new Font ("Tahoma",Font.BOLD,15));
		btnClear.setBounds(400,300,100,40);
		add(btnClear);
		
		btnSignup.setFont(new Font ("Tahoma",Font.BOLD,15));
		btnSignup.setBounds(500,300,100,40);
		add(btnSignup);
		
		// Attaching the listener
		btnLogin.addActionListener(this);
		btnClear.addActionListener(this);
		btnSignup.addActionListener(this);
		
		
		
		// changing the background color Frame
		getContentPane().setBackground(Color.white);
		setVisible(true);
		setSize(800, 500);
		setLocation(400, 200);
	}

	public static void main(String[] args) {
		
		// Creating Login class object
		Login obj=new Login();
		

	}
	
	
	//this methode is automatically called when u click on any button 
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			if(ae.getSource()==btnLogin) {
				// this for login purpose
				ConnectionFactory cf =new ConnectionFactory();
				String cardNum=tfCardNumber.getText();
				String pin=pfPinNumber.getText();
				
				
				String query="Select * from Login where cardnumber='"+cardNum+"' and pin='"+pin+"'";
				ResultSet rs=cf.stmt.executeQuery(query);
				if(rs.next()) {
					setVisible(false);
					new Transactions(pin).setVisible(true);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Either cardNum or Pin Num is Wrong");
				}
				
				
			}
		
			else if(ae.getSource()==btnClear) {
				tfCardNumber.setText("");
				pfPinNumber.setText("");
			}
			else if(ae.getSource()==btnSignup) {
					//This is for Sign Up purpose
					this.setVisible(false);
					new Signup();
				}
			}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}

}
