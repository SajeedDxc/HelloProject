package com.dxc.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class SearchAccountMain {

	public static void main(String[] args) {
		int accountNo;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter AccountNo ");
		accountNo = sc.nextInt();
		AccountDAO dao =  new AccountDAO();	
		try {
			Account accountFound = dao.searchAccountDao(accountNo);
			if(accountFound != null) {
				System.out.println(accountFound);
				
			}else {
				System.out.println("Account Does Not Exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
