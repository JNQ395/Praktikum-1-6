package org.modul_5.tugas.com.main;


import org.modul_5.tugas.data.Admin;
import org.modul_5.tugas.data.Student;
import org.modul_5.tugas.exception.custom.IllegalAdminAccess;


import java.util.Scanner;

public class LibrarySystem {
    private String nimUser;
    static Scanner objInput = new Scanner(System.in);
    private static LibrarySystem menu = new LibrarySystem();
    private Admin admin = new Admin();
    public Student studentSekarang;

    public void menu(){
        String choose;
        boolean ulang = true;

        while (ulang) {
            System.out.println("\nLibrary system: ");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");

            System.out.print("Choose option: ");
            choose = objInput.nextLine();

            switch (choose) {
                case "1":
                    menu.inputNim();
                    break;
                case "2":
                    System.out.println("Enter your username: ");
                    String userName = objInput.nextLine();
                    System.out.println("Enter your password: ");
                    String passWord = objInput.nextLine();
                    try {
                        if (admin.isAdmin(userName, passWord)) {
                            System.out.println("Succesful login as Admin");
                            admin.menu();
                        }
                    } catch (IllegalAdminAccess e) {
                        String errorMessage = e.getMessage();
                        System.out.println(errorMessage);
                    }
                    break;
                case "3":
                    System.out.println("thank you. exiting program");
                    ulang = false;
                    break;
                default:
                    System.out.println("inputan tidak valid");
                    break;
            }
        }
    }

    public void inputNim(){
        System.out.println("Enter your NIM (input 99 untuk back): ");
        nimUser = objInput.nextLine();
        if (nimUser.equals("99")) {
            System.out.println("User exit");
        } else {
            studentSekarang = menu.checkNim();
            if (studentSekarang == null) {
                System.out.println("NIM tidak ditemukan");
            } else {
                studentSekarang.menu();
            }
        }
    }
    public Student checkNim(){
        for (Student student : Admin.getStudentList()) {
            if (nimUser.equals(student.getNim())){
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args){
        menu.menu();
    }
    public void addTempStudent(){}
    public void addTempBooks(){}
}