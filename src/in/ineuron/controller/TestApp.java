package in.ineuron.controller;

import java.util.Scanner;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) {
		System.out.println("Welcome to CRUD APP"+"\n"+"-----------------------");
		option();
	}

	private static void option() {
	
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\n* Press 1 for Insert operation");
		System.out.println("* Press 2 for Select operation");
		System.out.println("* Press 3 for Update operation");
		System.out.println("* Press 4 for Delete operation");
		System.out.println("* Press 0 for Exit   operation");
		System.out.print(">");
		
		int value = sc.nextInt();

		switch (value) {
		case 0:
			System.out.println("Turning Off.."); // exit
			System.exit(0);
			break;
		case 1:
			insertOperation();
			break;

		case 2:
			selectOpertation();
			break;

		case 3:
			updateOperation();
			break;

		case 4:
			deleteOperation();
			break;
			
		default:
			System.out.println("Please Press the valid option!..");
			option();
			
		}
		
	}

	public static void updateOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the student id::");
		int id = sc.nextInt();
		sc.nextLine();
		Student student = studentService.searchStudent(id);

		if (student != null) {
			System.out.println("Old Records::" + "\n"+"------------");
			System.out.print("Name: " + student.getSname() + ", Enter the new Name: ");
			String name = sc.nextLine();
			System.out.print("Address: " + student.getSaddress() + ", Enter the new Address: ");
			String address = sc.nextLine();
			System.out.print("Age: " + student.getSage() + ", Enter the new Age: ");
			int age = sc.nextInt();
//			sc.nextLine();
			String msg = studentService.updateStudent(id, name, age, address);

			if (msg.equalsIgnoreCase("success"))
				System.out.println("Record Updated sucessfully..");
			else
				System.out.println("Updation failed..");

		} else
			System.out.println("Record Not Available..");
		sc.close();
	}

	public static void deleteOperation() {

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the student id::");
		int id = sc.nextInt();
//		sc.nextLine();
		String msg = studentService.deleteStudent(id);

		if (msg.equalsIgnoreCase("success"))
			System.out.println("Record Deleted sucessfully..");
		else if (msg.equalsIgnoreCase("not available"))
			System.out.println("Record Not Available..");
		else
			System.out.println("Deletion failed..");
		sc.close();
	}

	public static void selectOpertation() {

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the student id::");
		int id = sc.nextInt();
		Student student = studentService.searchStudent(id);

		if (student != null) {
			System.out.println(student);
			System.out.println("\nID\tNAME\tAGE\tADDRESS");
			System.out.println(student.getSid() + "\t" + student.getSname() + "\t" + student.getSage() + "\t"
					+ student.getSaddress());
		} else
			System.out.println("Record Not Available..");
	}

	public static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the student Name::");
		String name = sc.next();
		System.out.print("Enter the student Address::");
		String address = sc.next();
		System.out.print("Enter the student Age::");
		int age = sc.nextInt();
		String msg = studentService.addStudent(name, age, address);

		if (msg.equalsIgnoreCase("success")) {

			System.out.println("Record added successfully");

		} else
			System.out.println("Failed");

	}
}
