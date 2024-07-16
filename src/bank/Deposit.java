package bank;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Deposit extends  JFrame implements ActionListener {
	JTextField tf1,tf2;
	JButton b1,b2,b3;
	JLabel l1,l2,l3;
	
	String pin;
	//parameteried constructor
	public Deposit(String pin) {
		this.pin=pin;
		setSize(900,900);
		setLocation(300,0);
//		setUndecorated(true);
		setVisible(true);
		setLayout(null);
		
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/atmm.jpg"));
		Image i2=i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		
		l3=new JLabel(i3);
		l3.setBounds(0,0,900,900);
		add(l3);
		
		
		l1=new JLabel("Enter amount you want to deposit");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System",Font.BOLD,15));
		l1.setBounds(220,350,400,35);
		l3.add(l1);
		
		tf1=new JTextField();
		tf1.setFont(new Font("System",Font.BOLD,22));
		tf1.setBounds(220,400,250,25);
		l3.add(tf1);
		
		b1=new JButton("DEPOSIT");
		b2=new JButton("BACK");
		
		
		b1.setBounds(350,450,150,35);
		l3.add(b1);
		
		b2.setBounds(350,500,150,35);
		l3.add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
	}
	public static void main(String[] args) {
		new Deposit("");
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			String amount=tf1.getText();
			Date date = new Date();
			if(ae.getSource()==b1) {
				if(tf1.getText().equals("")) {
					JOptionPane.showConfirmDialog(null, "Please Enter Some Amount");
				}
				else
				{
					ConnectionFactory cf=new ConnectionFactory();
					cf.stmt.executeUpdate("insert into bank values('"+pin+"','"+date+"','Deposit','"+amount+"')");
					JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
					setVisible(false);
					new Transactions(pin).setVisible(true);
					
					
				}
			}
			else if(ae.getSource()==b2) {
				setVisible(false);
				new Transactions(pin).setVisible(true);
				
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}

