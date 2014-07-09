package ps1.debug;

import java.util.ArrayList;

public class InflateGrades
{
	public static void main(String[] args)
	{
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("Ben", "B-", "3"));
		students.add(new Student("Alyssa", "A-", "5"));
		students.add(new Student("Alice", "A", "12"));
		
		System.out.println("Before:");
		System.out.println(students);
		
		for (Student student : students)
		{
			student.inflateGrade();
			student.boostAttendance();
		}
		
		System.out.println("\nAfter:");
		System.out.println(students);
	}
}
