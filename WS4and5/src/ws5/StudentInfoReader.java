package ws5;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StudentInfoReader extends JFrame {

	static ArrayList<Student> students = new ArrayList<Student>();

	private static JTextArea fileOutput;

	public StudentInfoReader() {
		fileOutput = new JTextArea(null, 10, 15);
		add(fileOutput);
		add(new JScrollPane(fileOutput));
		fileOutput.setEditable(false);
	}

	static void createFrame() {
		StudentInfoReader stdReader = new StudentInfoReader();
		stdReader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stdReader.setSize(300, 500);
		stdReader.setVisible(true);
		stdReader.setResizable(true);
	}

	static void ReadStudentInfo() {
		createFrame();
		try {
			FileInputStream fis = new FileInputStream("student.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			students = (ArrayList<Student>) ois.readObject();
			fis.close();
		} catch (Throwable e) {
			System.err.println(e);
		}

		for (int i = 0; i < students.size(); i++) {

			fileOutput.append("******** Student " + (i + 1) + " ********\n");

			fileOutput.append("Student ID: " + students.get(i).getStdID() + "\n");
			fileOutput.append("Full Name: " + students.get(i).getFirstName() + " " + students.get(i).getLastName() + "\n");
			fileOutput.append("Enrolled courses are:\n");
			ArrayList<String> courses = students.get(i).getCourses();

			for (int j = 0; j < courses.size(); j++) {
				fileOutput.append(courses.get(j) + "\n");
			}
		}
	}

	public static void main(String[] args) {
		ReadStudentInfo();
	}
}
