package ws5;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StudentForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JTextField tfFirstName;
	private final JTextField tfLastName;
	private final JTextField tfStudentId;

	private final JLabel lFirstName;
	private final JLabel lLastName;
	private final JLabel lStudentId;
	private final JLabel lCourse;

	private final JButton saveButton;
	private final JButton writeButton;
	private final JTextArea taCourse;

	private static String[] courseArray = new String[20];

	private static boolean correctInput = true;

	public StudentForm() {
		super("Student Creator");
		setLayout(new FlowLayout());

		lStudentId = new JLabel("Student ID: ");
		add(lStudentId);
		tfStudentId = new JTextField(15);
		tfStudentId.setHorizontalAlignment(SwingConstants.LEFT);
		add(tfStudentId);

		JPanel p = new JPanel();
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(p);

		lFirstName = new JLabel("First Name: ");
		add(lFirstName);
		tfFirstName = new JTextField(15);
		tfFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		add(tfFirstName);

		JPanel q = new JPanel();
		q.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(q);

		lLastName = new JLabel("Last Name: ");
		add(lLastName);
		tfLastName = new JTextField(15);
		tfLastName.setHorizontalAlignment(SwingConstants.LEFT);
		add(tfLastName);

		JPanel r = new JPanel();
		r.setBorder(new EmptyBorder(10, 10, 20, 10));
		add(r);

		lCourse = new JLabel("Course Names: ");
		add(lCourse);
		taCourse = new JTextArea(null, 10, 15);
		add(taCourse);
		add(new JScrollPane(taCourse));

		JPanel s = new JPanel();
		s.setBorder(new EmptyBorder(10, 10, 150, 10));
		add(s);

		saveButton = new JButton("Save Student");
		saveButton.setVerticalAlignment(SwingConstants.BOTTOM);
		saveButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(saveButton);

		writeButton = new JButton("Write");
		writeButton.setVerticalAlignment(SwingConstants.BOTTOM);
		writeButton.setHorizontalAlignment(SwingConstants.RIGHT);
		add(writeButton);

		writeButton.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				StudentInfoWriter.writeToFile();
			}
		}));

		ButtonHandler handler = new ButtonHandler();
		saveButton.addActionListener(handler);
		tfStudentId.addActionListener(handler);
		tfFirstName.addActionListener(handler);
		tfLastName.addActionListener(handler);
	}

	public class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				if (tfStudentId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Student must have an ID.", "Wrong Input",
							JOptionPane.PLAIN_MESSAGE);
					correctInput = false;
					throw new IllegalArgumentException();
				}
				StudentInfoWriter.studentId = Integer.parseInt(tfStudentId.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Student ID must have only numbers", "Wrong Input",
						JOptionPane.PLAIN_MESSAGE);
				correctInput = false;
			}

			try {
				if (tfFirstName.getText().equals("")) {
					correctInput = false;
					throw new IllegalArgumentException();
				}
				StudentInfoWriter.firstName = tfFirstName.getText();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Student must have a first name.", "Wrong Input",
						JOptionPane.PLAIN_MESSAGE);
			}

			try {
				if (tfLastName.getText().equals("")) {

					correctInput = false;
					throw new IllegalArgumentException();
				}
				StudentInfoWriter.lastName = tfLastName.getText();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Student must have a last name.", "Wrong Input",
						JOptionPane.PLAIN_MESSAGE);
			}

			try {
				if (taCourse.getText().equals("")) {

					correctInput = false;
					throw new IllegalArgumentException();
				}
				courseArray = taCourse.getText().split("\\r?\\n");
				StudentInfoWriter.courses = new ArrayList<>(Arrays.asList(courseArray));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Student must be enrolled in at least one course.", "Wrong Input",
						JOptionPane.PLAIN_MESSAGE);
			}

			if (correctInput) {
				Student student = new Student();
				student.setFirstName(StudentInfoWriter.firstName);
				student.setLastName(StudentInfoWriter.lastName);
				student.setStdID(StudentInfoWriter.studentId);
				student.setCourses(StudentInfoWriter.courses);
				StudentInfoWriter.students.add(student);
				StudentInfoWriter.studentNum++;
			}

			else
				JOptionPane.showMessageDialog(null, "Student was not saved", "Wrong Input", JOptionPane.PLAIN_MESSAGE);

			tfStudentId.setText("");
			tfFirstName.setText("");
			tfLastName.setText("");
			taCourse.setText("");
			correctInput = true;
		}
	}
}
