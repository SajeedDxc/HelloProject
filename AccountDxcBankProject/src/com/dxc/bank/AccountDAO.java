package com.dxc.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountDAO {

	Connection connection;
	PreparedStatement pst;
	
	public String withdrawAccount(int accountNo,int withdrawAmount) throws SQLException {
		String result = "";
		connection = ConnectionHelper.getConnection();
		Account account = searchAccountDao(accountNo);
		if(account != null) {
			int amount =  account.getAmount();
			if(amount-withdrawAmount >= 1000) {
				String cmd = "Update account set amount= amount-? where accountNo =?";
				pst = connection.prepareStatement(cmd);
				pst.setInt(1, withdrawAmount);
				pst.setInt(2, accountNo);
				pst.executeUpdate();
				pst.close();
				cmd="Insert into accounttransaction(AccountNo, TranAmount," + 
						"TranType) values(?, ?, ?)";
				pst = connection.prepareStatement(cmd);
				pst.setInt(1, accountNo);
				pst.setInt(2, withdrawAmount);
				pst.setString(3, "D");
				pst.executeUpdate();
				result = "Amount Debited..";
			}else {
				result = "Insufficient Amount to Withdraw";
			}
		}else {
			result = "Account Not Found";
		}
		return result;
	}
	
	public String depositAccount(int accountNo, int depositAmount) throws SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "Update Account SET Amount = Amount + ? "
				+ " WHERE AccountNo=?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, depositAmount);
		pst.setInt(2, accountNo);
		pst.executeUpdate();
		pst.close();
		cmd = "Insert into accounttransaction(AccountNo, TranAmount,"
				+ "TranType) values(?, ?, ?)";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, accountNo);
		pst.setInt(2, depositAmount);
		pst.setString(3, "C");
		pst.executeUpdate();
		return "Amount Credited...";
	}
	
	public String closeAccount(int accountNo) throws SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "Update Account SET Status='inactive' WHERE "
				+ " AccountNo=?";
		pst=connection.prepareStatement(cmd);
		pst.setInt(1, accountNo);
		pst.executeUpdate();
		return "Account Closed...";
	}
	
	public String UpdateAccountDao(int accountNo,String city ,String state) throws SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "UPDATE ACCOUNT SET CITY=? ,STATE = ? WHERE ACCOUNTNO=?";
		pst = connection.prepareStatement(cmd);
		pst.setString(1, city);
		pst.setString(2, state);
		pst.setInt(3, accountNo);
		pst.executeUpdate();
		return "Account is Successfully Updated";
	}
	
	public Account searchAccountDao(int accountNo) throws SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd ="SELECT * FROM ACCOUNT WHERE ACCOUNTNO=?";
		Account account = null;
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, accountNo);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			account = new Account();
			account.setAccountNo(rs.getInt("accountNo"));
			account.setFirstName(rs.getString("firstName"));
			account.setLastName(rs.getString("lastName"));
			account.setCity(rs.getString("city"));
			account.setState(rs.getString("state"));
			account.setAmount(rs.getInt("amount"));
			account.setCheqFacil(rs.getString("cheqFacil"));
			account.setAccountType(rs.getString("accountType"));	
		}
		return account;
	}
	
	public String createAccountDao(Account account) throws SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd ="INSERT INTO ACCOUNT(ACCOUNTNO,FIRSTNAME,LASTNAME,CITY,STATE,AMOUNT,CHEQFACIL,ACCOUNTTYPE) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1,account.getAccountNo());
		pst.setString(2, account.getFirstName());
		pst.setString(3, account.getLastName());
		pst.setString(4, account.getCity());
		pst.setString(5, account.getState());
		pst.setInt(6, account.getAmount());
		pst.setString(7, account.getCheqFacil());
		pst.setString(8, account.getAccountType());
		pst.executeUpdate();
		return "Account is Created Succesfully";

	}
	
	public int generateAccountNo() throws SQLException {
		int accountNo = 0;
		connection = ConnectionHelper.getConnection();
		String cmd = "SELECT CASE WHEN MAX(ACCOUNTNO) IS NULL THEN 1 " + 
				"ELSE MAX(ACCOUNTNO)+1 END accno FROM Account";
		pst = connection.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		rs.next();
		accountNo = rs.getInt("accno");
		return accountNo;
	}
}
