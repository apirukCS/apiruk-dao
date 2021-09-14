package customermaven.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import customermaven.MainFrame;

public class CustomerDAO {
	MainFrame mainFrame = new MainFrame();
	Connection connect = mainFrame.getConnection();
	
	public int addNewCustomer(Customer customer) {
		int affected = 0;
		try {
			String sql = "insert into customer "
					+"(`username`,`password`,`name`,`address`,`mobile`,`email`)"
					+" values(?,?,?,?,?,?)";
			PreparedStatement ps = this.connect.prepareStatement(sql);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getName());
			ps.setString(4, customer.getAddress());
			ps.setString(5, customer.getMobile());
			ps.setString(6, customer.getEmail());
			affected = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return affected;
	}
	
	public List<Customer> getCustomerByName(String input){
		List<Customer> customers = new ArrayList<Customer>();
		try {
			String sql = "select * from customer where name like ?";
			PreparedStatement ps = this.connect.prepareStatement(sql);
			ps.setString(1, "%"+input+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setMobile(rs.getString("mobile"));
				customer.setEmail(rs.getString("email"));
				customers.add(customer);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return customers;
	}
	
}
