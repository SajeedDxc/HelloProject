package com.dxc.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateAccountMain {

	public static void main(String[] args) {
		int accountNo;
		String city,state;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter AccountNo");
		accountNo = Integer.parseInt(sc.nextLine());
		System.out.println("Enter City");
		city = sc.nextLine();
		System.out.println("Enter State");
		state = sc.nextLine();
		AccountDAO dao = new AccountDAO();
		try {
			System.out.println(dao.UpdateAccountDao(accountNo, city, state));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
