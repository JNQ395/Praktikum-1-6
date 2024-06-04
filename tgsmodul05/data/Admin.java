package org.modul_5.tugas.data;

import org.modul_5.tugas.exception.custom.IllegalAdminAccess;
import org.modul_5.tugas.exception.custom.InvalidMenuInput;
import org.modul_5.tugas.util.iMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private static List<Student> studentList = new ArrayList<>();
    public static List<Student> getStudentList() {
        return studentList;
    }
    public static void setStudentList(List<Student> studentList) {
        Admin.studentList = studentList;
    }

    public void addStudent(){
        Scanner objInput = new Scanner(System.in);

        System.out.println("Masukkan nama mahasiswa: ");
        String name = objInput.nextLine();

        System.out.println("Masukkan NIM mahasiswa: ");
        String nim = objInput.nextLine();
        while (nim.length() != 15) {
            System.out.println("Nim harus 15 digit");
            System.out.println("Masukkan NIM mahasiswa: ");
            nim = objInput.nextLine();
        }

        System.out.println("Masukkan jurusan mahasiswa: ");
        String programStudi = objInput.nextLine();

        System.out.println("Masukkan fakultas mahasiswa: ");
        String faculty = objInput.nextLine();

        Student student = new Student(name, nim, faculty, programStudi);

        studentList.add(student);
    }
    public void inputBook(){
        super.inputBook();
    }

    public void displayBook(){
        super.displayBook();
    }

    public void displayStudent(){
        System.out.println("Data mahasiswa: ");
        System.out.println("Universitas Muhammadiyah Malang");
        for (Student mahasiswa : studentList) {
            System.out.println(mahasiswa.displayStudents());
        }
    }

    public boolean isAdmin(String username, String password) throws IllegalAdminAccess {
        if (!username.equals("admin") || !password.equals("admin")){
            throw new IllegalAdminAccess("Invalid credentials");
        }
        return true;
    }

    public String generateId(){
        return "";
    }

    @Override
    public void menu() {
        String pilihan;
        boolean ulang = true;
        Scanner objInput = new Scanner(System.in);

        while (ulang) {
            System.out.println("\nMenu: ");
            System.out.println("1. Tambahkan Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Tambahkan Buku");
            System.out.println("4. Tampilkan Buku");
            System.out.println("5. Keluar");

            System.out.print("Pilihan Anda: ");
            pilihan = objInput.nextLine();

            try {
                switch (pilihan) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        displayStudent();
                        break;
                    case "3":
                        inputBook();
                        break;
                    case "4":
                        displayBook();
                        break;
                    case "5":
                        System.out.println("adios");
                        ulang = false;
                        break;
                    default:
                        throw new InvalidMenuInput("inputan tidak valid");
                }
            } catch (InvalidMenuInput e) {
                String errorMessage = e.getMessage();
                System.out.println(errorMessage);
            }
        }
    }
}
