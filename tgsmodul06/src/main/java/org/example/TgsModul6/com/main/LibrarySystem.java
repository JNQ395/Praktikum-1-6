package org.example.TgsModul6.com.main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.TgsModul6.books.*;
import org.example.TgsModul6.data.Admin;
import org.example.TgsModul6.data.Student;

import java.util.ArrayList;

public class LibrarySystem {
    public static ArrayList<Book> daftarBuku = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static void startLibrarySystem(Stage stage) {
        daftarBuku.add(new StoryBook("ID777", "ga tau", 17, "Story", "iya"));
        daftarBuku.add(new HistoryBook("JP696", "makan", 2, "History", "hooh"));
        daftarBuku.add(new TextBook("DT00", "APA", 4, "Text", "kuu"));

        studentList.add(new Student("202310370366777", "olaf", "Teknik", "HI"));
        studentList.add(new Student("202310370316970", "anna", "Teknik", "IT"));
        studentList.add(new Student("202310370314798", "elsa", "Teknik", "elektro"));

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Rectangle colors = new Rectangle();
        colors.setWidth(400);
        colors.setHeight(300);
        colors.setFill(new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.web("#f8bd55")),
                new Stop(0.14, Color.web("#c0fe56")),
                new Stop(0.28, Color.web("#5dfbc1")),
                new Stop(0.43, Color.web("#64c2f8")),
                new Stop(0.57, Color.web("#be4af7")),
                new Stop(0.71, Color.web("#ed5fc2")),
                new Stop(0.85, Color.web("#ef504c")),
                new Stop(1, Color.web("#f2660f")),
        }));

        Label label = new Label("===== Library System =====");
        Button studentLoginButton = new Button("Login sebagai Mahasiswa");
        studentLoginButton.setPrefHeight(80);
        studentLoginButton.setPrefWidth(200);
        Button adminLoginButton = new Button("Login sebagai Admin");
        adminLoginButton.setPrefHeight(80);
        adminLoginButton.setPrefWidth(200);
        Button exitButton = new Button("Keluar");
        exitButton.setPrefWidth(200);
        exitButton.setPrefHeight(80);

        studentLoginButton.setOnAction(event -> studentLogin(stage));
        adminLoginButton.setOnAction(event -> {
            try {
                new Admin().login(stage);
            } catch (Exception e) {
                showErrorDialog("Error", e.getMessage());
            }
        });
        exitButton.setOnAction(event -> stage.close());

        root.getChildren().addAll(label, studentLoginButton, adminLoginButton, exitButton);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(colors, root);

        Scene scene = new Scene(stackPane, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Library System");
        stage.show();
    }

    private static void studentLogin(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("Masukkan NIM : ");
        TextField nimField = new TextField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Kembali");

        loginButton.setOnAction(event -> {
            String nimStudent = nimField.getText();
            if (nimStudent.length() == 15 && checkNim(nimStudent)) {
                Student student = new Student(nimStudent);
                student.login(stage);
            } else {
                showErrorDialog("Error", "Nim tidak terdaftar atau tidak valid!");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, nimField, loginButton, backButton);

        stage.setScene(scene);
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
