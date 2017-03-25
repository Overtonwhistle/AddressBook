package view;

import java.util.Scanner;

import controller.Controller;

public class MainMethod {

	public static void main(String[] args) {

		Controller controller = new Controller();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String response;
		String command;

		System.out.println("Enter command:");

		while (true) {

			command = scanner.nextLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("Exit programm...");
				break;
			}
			response = controller.executeTask(command);
			System.out.println(response);
		}
	}
}
