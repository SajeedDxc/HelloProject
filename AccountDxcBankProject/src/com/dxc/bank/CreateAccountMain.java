package com.dxc.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateAccountMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Account account = new Account();
		AccountDAO dao = new AccountDAO();
		System.out.println("Enter FirstName ");
		account.setFirstName(sc.nextLine());
		System.out.println("Enter LastName ");
		account.setLastName(sc.nextLine());
		System.out.println("City ");
		account.setCity(sc.nextLine());
		System.out.println("State ");
		account.setState(sc.nextLine());
		System.out.println("Amount ");
		account.setAmount(Integer.parseInt(sc.nextLine()));
		System.out.println("CheqFacil (YES/NO)");
		account.setCheqFacil(sc.nextLine());
		System.out.println("AccountType ");
		account.setAccountType(sc.nextLine());
		
		
		try {
			System.out.println(dao.generateAccountNo());
			
			int accno =dao.generateAccountNo();
			account.setAccountNo(accno);
			System.out.println(dao.createAccountDao(account));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
