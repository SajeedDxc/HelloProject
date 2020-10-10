package com.dxc.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class WithdrawAccountMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int accountNo,withdrawAmount;
		System.out.println("Enter AccountNo ");
		accountNo = sc.nextInt();
		System.out.println("Enter WithdrawAmount ");
		withdrawAmount = sc.nextInt();
		AccountDAO dao = new AccountDAO();
		try {
			System.out.println(dao.withdrawAccount(accountNo, withdrawAmount));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
