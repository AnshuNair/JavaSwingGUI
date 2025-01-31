package ws5;

import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;

public class StudentInfoWriter {

	static ArrayList<Student> students = new ArrayList<Student>();
	static ArrayList<String> courses = new ArrayList<String>();

	static int studentId = 0;
	static String firstName = "";
	static String lastName = "";
	public static int studentNum;

	static void createFrame() {
		StudentForm stdCreator = new StudentForm();
		stdCreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stdCreator.setSize(300, 500);
		stdCreator.setVisible(true);
		stdCreator.setResizable(false);
	}

	static void writeToFile() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("student.txt"));
			os.writeObject(students);
			os.flush();
			os.close();
			JOptionPane.showMessageDialog(null, "Wrote " + studentNum + " student(s) to file", "Write to file",
					JOptionPane.PLAIN_MESSAGE);
		} catch (Throwable e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		createFrame();
	}
}
