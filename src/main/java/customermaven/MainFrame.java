package customermaven;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import customermaven.model.Customer;
import customermaven.model.CustomerDAO;


import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameCustomertextField;
	private JTextField usernameCustomerEdittextField;
	private JTextField nameCustomerEdittextField;
	private JTextField nameCustomerDAOtextField;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblPassword;
	private JLabel lblName;
	private JLabel lblNameEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("62011212025 อภิรักษ์ เริ่มรักษ์ (Customer-DAO)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameCustomertextField = new JTextField();
		nameCustomertextField.setBounds(22, 47, 96, 19);
		contentPane.add(nameCustomertextField);
		nameCustomertextField.setColumns(10);
		
		JButton showCustomerButton = new JButton("แสดงข้อมูลลูกค้า");
		showCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = getConnection();
				if(conn!=null) {
					System.out.println("con!=null");
					try {
						String sql = "select * from customer where name like ? or email like ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1,"%"+nameCustomertextField.getText()+"%");
						ps.setString(2,"%"+nameCustomertextField.getText()+"%");
						
						ResultSet rs = ps.executeQuery();
						table.setModel(buildTableModel(rs));
						if(rs.next()) {
							System.out.println(rs.getString(1));
							
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				else {
					System.out.println("con=null");
				}
				
				usernameCustomerEdittextField.setText("");
				nameCustomerEdittextField.setText("");
				nameCustomerDAOtextField.setText("");
				
			}
		});
		showCustomerButton.setBounds(22, 83, 97, 21);
		contentPane.add(showCustomerButton);
		
		usernameCustomerEdittextField = new JTextField();
		usernameCustomerEdittextField.setColumns(10);
		usernameCustomerEdittextField.setBounds(147, 47, 96, 19);
		contentPane.add(usernameCustomerEdittextField);
		usernameCustomerEdittextField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				nameCustomertextField.setText("");
				nameCustomerDAOtextField.setText("");
				
			}
		});
		
		nameCustomerEdittextField = new JTextField();
		nameCustomerEdittextField.setColumns(10);
		nameCustomerEdittextField.setBounds(253, 47, 96, 19);
		contentPane.add(nameCustomerEdittextField);
		
		JButton editCustomerButton = new JButton("แก้ไขข้อมูลลูกค้า");
		editCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = getConnection();
					String sql = "update dw64_62011212025.customer set name = ? where username = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, nameCustomerEdittextField.getText());
					ps.setString(2, usernameCustomerEdittextField.getText());
					
					int affected = ps.executeUpdate(); //ตรวจสอบผลกระทบที่เกิดขึ้น
					if(affected > 0) {
						JOptionPane.showMessageDialog(null, "Updated");
					}
					else {
						JOptionPane.showMessageDialog(null, "Not Updated");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				nameCustomertextField.setText("");
				nameCustomerDAOtextField.setText("");
			}
		});
		editCustomerButton.setBounds(196, 83, 113, 21);
		contentPane.add(editCustomerButton);
		
		nameCustomerDAOtextField = new JTextField();
		nameCustomerDAOtextField.setColumns(10);
		nameCustomerDAOtextField.setBounds(398, 47, 96, 19);
		contentPane.add(nameCustomerDAOtextField);
		
		JButton showCustomerDAOButton = new JButton("แสดงข้อมูลลูกค้า (DAO)");
		showCustomerDAOButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pname = nameCustomerDAOtextField.getText();
					CustomerDAO dao = new CustomerDAO();
					List<Customer> customers = dao.getCustomerByName(pname);
					
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("username");
					model.addColumn("password");
					model.addColumn("name");
					model.addColumn("address");
					model.addColumn("mobile");
					model.addColumn("email");
					
					for(Customer customer : customers) {
						Object[] obj = {customer.getUsername(),
								customer.getPassword(),
								customer.getName(),
								customer.getAddress(),
								customer.getMobile(),
								customer.getEmail()};
						model.addRow(obj);
					}
					table.setModel(model);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				usernameCustomerEdittextField.setText("");
				nameCustomerEdittextField.setText("");
				nameCustomertextField.setText("");
			}
		});
		showCustomerDAOButton.setBounds(355, 83, 139, 21);
		contentPane.add(showCustomerDAOButton);
		
		JButton insertCustomerButton = new JButton("เพิ่มข้อมูลลูกค้า");
		insertCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomer addCustomer=new AddCustomer();				
				addCustomer.setLocationRelativeTo(null);
				addCustomer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addCustomer.setVisible(true);
				
				usernameCustomerEdittextField.setText("");
				nameCustomerEdittextField.setText("");
				nameCustomertextField.setText("");
				nameCustomerDAOtextField.setText("");
			}
		});
		insertCustomerButton.setBounds(528, 83, 113, 21);
		contentPane.add(insertCustomerButton);
		
		table = new JTable();
		table.setBounds(10, 135, 643, 192);
		contentPane.add(table);
		
		lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(176, 24, 67, 13);
		contentPane.add(lblNewLabel);
		
		lblPassword = new JLabel("edit_name");
		lblPassword.setBounds(275, 24, 67, 13);
		contentPane.add(lblPassword);
		
		lblName = new JLabel("name");
		lblName.setBounds(427, 24, 67, 13);
		contentPane.add(lblName);
		
		lblNameEmail = new JLabel("name , email");
		lblNameEmail.setBounds(29, 24, 67, 13);
		contentPane.add(lblNameEmail);
	}
	
	public Connection getConnection() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager
					.getConnection(
							"jdbc:mysql://202.28.34.197:3306/dw64_62011212025",
							"dw64_62011212025","62011212025@csmsu");
			
			System.out.println("Connection Success");
			return connect;
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return null;
	}
	
	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
	{
		java.sql.ResultSetMetaData metaData = rs.getMetaData();
		
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for(int column = 1; column <= columnCount;column++) 
		{
			columnNames.add(metaData.getColumnName(column));
		}
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while(rs.next())
		{
			Vector<Object> vector = new Vector<Object>();
			for(int columnIndex = 1;columnIndex<=columnCount;columnIndex++) 
			{
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data,columnNames);
		
	}
}
