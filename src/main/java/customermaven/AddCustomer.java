package customermaven;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import customermaven.model.Customer;
import customermaven.model.CustomerDAO;


import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField usernametextField;
	private JTextField passwordtextField;
	private JTextField nametextField;
	private JTextField mobiletextField;
	private JTextField emailtextField;
	private JTextArea addresstextArea;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer frame = new AddCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AddCustomer() {
		setTitle("เพิ่มข้อมูลลูกค้า");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernametextField = new JTextField();
		usernametextField.setBounds(162, 38, 145, 26);
		contentPane.add(usernametextField);
		usernametextField.setColumns(10);
		
		passwordtextField = new JTextField();
		passwordtextField.setColumns(10);
		passwordtextField.setBounds(162, 77, 145, 26);
		contentPane.add(passwordtextField);
		
		nametextField = new JTextField();
		nametextField.setColumns(10);
		nametextField.setBounds(162, 117, 145, 26);
		contentPane.add(nametextField);
		
		mobiletextField = new JTextField();
		mobiletextField.setColumns(10);
		mobiletextField.setBounds(162, 233, 145, 26);
		contentPane.add(mobiletextField);
		
		emailtextField = new JTextField();
		emailtextField.setColumns(10);
		emailtextField.setBounds(162, 269, 145, 26);
		contentPane.add(emailtextField);
		
		 addresstextArea = new JTextArea();
		addresstextArea.setBounds(162, 162, 197, 61);
		contentPane.add(addresstextArea);
		
		JButton insertCustomerButton = new JButton("เพิ่มข้อมูลลูกค้า");
		insertCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Customer customer = new Customer();
				customer.setUsername(usernametextField.getText());
				customer.setPassword(passwordtextField.getText());
				customer.setName(nametextField.getText());
				customer.setAddress(addresstextArea.getText());
				customer.setMobile(mobiletextField.getText());
				customer.setEmail(emailtextField.getText());
			
				CustomerDAO dao = new CustomerDAO();
				int affected = dao.addNewCustomer(customer);
				if(affected > 0) {
					JOptionPane.showMessageDialog(null,"Success");
				}
				else {
					JOptionPane.showMessageDialog(null,"Failed");
				}
				usernametextField.setText("");
				passwordtextField.setText("");
				nametextField.setText("");
				addresstextArea.setText("");
				mobiletextField.setText("");
				emailtextField.setText("");
				
			}
		});
		insertCustomerButton.setBounds(134, 352, 109, 39);
		contentPane.add(insertCustomerButton);
		
		JButton calcelCustomerButton = new JButton("ยกเลิก");
		calcelCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		calcelCustomerButton.setBounds(274, 352, 85, 39);
		contentPane.add(calcelCustomerButton);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(43, 38, 109, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(43, 77, 109, 20);
		contentPane.add(lblPassword);
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(43, 117, 109, 20);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(43, 162, 109, 20);
		contentPane.add(lblAddress);
		
		JLabel lblMobile = new JLabel("mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMobile.setBounds(43, 233, 109, 20);
		contentPane.add(lblMobile);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(43, 269, 109, 20);
		contentPane.add(lblEmail);
	}
	void closeFrame() {
		this.dispose();
	}
}
