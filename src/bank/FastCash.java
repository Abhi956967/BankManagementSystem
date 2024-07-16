package bank; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FastCash extends JFrame implements ActionListener {
	// instance variable

			JTextField tf1;
			JButton b1, b2, b3,b4,b5,b6,b7,b8;
			JLabel l1, l2,l3;
			String pin;
			
			// parametriced constructor

			public FastCash(String pin)
			{
				this.pin = pin;
				//Defining the size of frame
				setSize(960, 1080);
				setLocation(300, 0);
				setUndecorated(true);
				setVisible(true);
				setLayout(null);
				
				ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atmm.jpg"));
				Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
				ImageIcon i3 = new ImageIcon(i2);

				l3 = new JLabel(i3);
				l3.setBounds(0, 0, 900, 900);
				add(l3);
				
				
				
				l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
				l1.setForeground(Color.WHITE);
				l1.setFont(new Font("System", Font.BOLD, 15));
				l1.setBounds(250, 300, 400, 35);
				l3.add(l1);
				
				
				b1 = new JButton("Rs 100");
				b2 = new JButton("Rs 200");
				b3 = new JButton("Rs 500");
				b4 = new JButton("Rs 1000");
				b5 = new JButton("Rs 2000");
				b6 = new JButton("Rs 10000");
				b7 = new JButton("BACK");
				
				b1.setBounds(190,370,150,35);
				add(b1);
				
				b2.setBounds(350,370,150,35);
				add(b2);
				
				b3.setBounds(190,420,150,35);
				add(b3);
				
				b4.setBounds(350,420,150,35);
				add(b4);
				
				b5.setBounds(190,470,150,35);
				add(b5);
				
				b6.setBounds(350,470,150,35);
				add(b6);
				

				b7.setBounds(350,505,150,35);
				add(b7);
				
				
				
				

				b1.addActionListener(this);
				b2.addActionListener(this);
				b3.addActionListener(this);
				b4.addActionListener(this);
				b5.addActionListener(this);
				b6.addActionListener(this);
				b7.addActionListener(this);

				
			
			
		}


			public static void main(String[] args) {
				new FastCash("").setVisible(true);
			}


			@Override
			public void actionPerformed(ActionEvent ae) {
				try
				{
					String amount = ((JButton)ae.getSource()).getText().substring(3);
					ConnectionFactory cf = new ConnectionFactory();
					ResultSet rs = cf.stmt.executeQuery("select * from bank where pin = '" +pin+ "'");

					int balance = 0;
					while (rs.next())
					{
						if (rs.getString("type").equals("Deposit")) {
							balance += Integer.parseInt(rs.getString("amount"));
						} else {
							balance -= Integer.parseInt(rs.getString("amount"));
						}
					}

					if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
						return;
					}
					
					if(ae.getSource()==b7) {
					setVisible(false);
					new Transactions(pin).setVisible(true);
					}
					else
					{
						Date date = new Date();
						cf.stmt.executeUpdate("insert into bank values('"+pin+"','"+date+"','Widthdrawl','"+amount+"')");
						JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
						setVisible(false);
						new Transactions(pin).setVisible(true);
						
					} 
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}

		}
