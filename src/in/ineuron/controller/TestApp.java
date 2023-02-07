package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE, PRESS[1/2/3/4/5]::  ");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertOperation();
				break;
			case "2":
				selectOpertation();
				break;
			case "3":
				updateOperation();
				break;
			case "4":
				deleteOperation();
				break;
			case "5":
				System.out.println("******* Thanks for using the application *****");
				System.exit(0);
			default:
				System.out.println("Invalid option plz try again with valid options....");
				break;
			}

		}

	}

	public static void updateOperation() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter the student id::");
		String id = br.readLine();
		
		Student student = studentService.searchStudent(Integer.parseInt(id));

		if (student != null) {
			Student newStudent = new Student();

			System.out.println("Student id is :: " + student.getSid());
			newStudent.setSid(student.getSid());

			System.out.print("Student oldName is :: " + student.getSname() + "  Enter newName :: ");
			String newName = br.readLine();
			if (newName.equals("") || newName == "") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}
			System.out.print("Student oldAge is :: " + student.getSage() + "  Enter newAge :: ");
			String newAge = br.readLine();
			if (newAge.equals("") || newAge == "") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newAge));
			}
			System.out.print("Student oldAddress is :: " + student.getSaddress() + "  Enter newAddress :: ");
			String newAddress = br.readLine();
			if (newAddress.equals("") || newAddress == "") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSaddress(newAddress);
			}

			System.out.println("new Object data is :: " + newStudent);
			System.out.println();

			String status = studentService.updateStudent(newStudent);
			if (status.equalsIgnoreCase("success")) {
				System.out.println("record updated succesfully");
			} else {
				System.out.println("record updation failed");
			}

		} else {
			System.out.println("Student record not available for the given id  " + id + " for updation...");
		}

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
