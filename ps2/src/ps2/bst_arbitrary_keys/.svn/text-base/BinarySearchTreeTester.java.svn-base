package ps2.bst_arbitrary_keys;

import java.util.ArrayList;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ps2.bst_exception.DuplicateItemException;
import ps2.bst_exception.ItemNotFoundException;


public class BinarySearchTreeTester {

	private Random random;

	@Before
	public void initializeSuite() {
		random = new Random();
	}

	private Student createRandomStudent() {
		return new Student((int)( Math.random() * 1000000),
				generateRandomString());
	}

	private String generateRandomString() {
		String toReturn = "";
		toReturn += Long.toString(Math.abs(random.nextLong()), 36);

		for (int i = 0; i < 3; i++) {
			if (Math.random() > 0.5)
				toReturn += Long.toString(Math.abs(random.nextLong()), 36);
		}
		return toReturn;
	}

	private Student findMinStudent(ArrayList<Student> list) {
		if (list.size() == 0)
			return null;
		Student min = list.get(0);
		for (Student student : list) {
			if (min.getName().equals(student.getName())) {
				if (min.getMitID() > student.getMitID())
					min = student;
			} else {
				if (min.getName().compareTo(student.getName()) > 0)
					min = student;
			}
		}
		return min;
	}

	private Student findMaxStudent(ArrayList<Student> list) {
		if (list.size() == 0)
			return null;
		Student max = list.get(0);
		for (Student student : list) {
			if (max.getName().equals(student.getName())) {
				if (max.getMitID() < student.getMitID())
					max = student;
			} else {
				if (max.getName().compareTo(student.getName()) < 0)
					max = student;
			}
		}
		return max;
	}

	private String findMinString(ArrayList<String> list) {
		if (list.size() == 0)
			return "";
		String min = list.get(0);
		for (String string : list) {
			if (string.compareTo(min) < 0)
				min = string;
		}
		return min;
	}

	private String findMaxString(ArrayList<String> list) {
		if (list.size() == 0)
			return "";
		String max = list.get(0);
		for (String string : list) {
			if (string.compareTo(max) > 0)
				max = string;
		}
		return max;
	}

	private int findMin(ArrayList<Integer> list) {
		int min = Integer.MAX_VALUE;
		for (Integer i : list) {
			if (i < min)
				min = i;
		}
		return min;
	}

	private int findMax(ArrayList<Integer> list) {
		int max = Integer.MIN_VALUE;
		for (Integer i : list) {
			if (i > max)
				max = i;
		}
		return max;
	}

	@Test
	public void testEmptyTreeCreation() {
		BinarySearchTree tree = new BinarySearchTree();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);
	}

	@Test
	public void testEmptyTreeIllegalOperations() {
		BinarySearchTree tree = new BinarySearchTree();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		for (int i = 0; i < 100; i++) {
			Assert.assertEquals("tree should be not have keys",
					tree.contains(new IntegerBinaryTreeItem(i)), false);
			try {
				tree.find(new IntegerBinaryTreeItem(i));
				Assert.assertEquals("find should throw exception", true, false);
			} catch (ItemNotFoundException e) {
				// should come here
			}
		}

		for (int i = 0; i < 100; i++) {
			Assert.assertEquals("tree should be not have keys",
					tree.contains(new IntegerBinaryTreeItem(i)), false);
			try {
				tree.remove(new IntegerBinaryTreeItem(i));
				Assert.assertEquals("remove should throw exception", true,
						false);
			} catch (ItemNotFoundException e) {
				// should come here
			}
		}

		try {
			tree.removeMin();
			Assert.assertEquals("removeMin should throw exception", true, false);
		} catch (ItemNotFoundException e) {
			// should come here
		}

		try {
			tree.findMin();
			Assert.assertEquals("findMin should throw exception", true, false);
		} catch (ItemNotFoundException e) {
			// should come here
		}

		try {
			tree.findMax();
			Assert.assertEquals("findMax should throw exception", true, false);
		} catch (ItemNotFoundException e) {
			// should come here
		}

	}

	@Test
	public void testEmptyTreeTransitions() {
		BinarySearchTree tree = new BinarySearchTree();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		for (int i = 0; i < 10; i++) {
			tree.insert(new IntegerBinaryTreeItem(i));
		}
		Assert.assertEquals("tree should not be empty", tree.isEmpty(), false);

		for (int i = 0; i < 10; i++) {
			tree.removeMin();
		}
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		for (int i = 0; i < 10; i++) {
			tree.insert(new IntegerBinaryTreeItem(i));
		}
		Assert.assertEquals("tree should not be empty", tree.isEmpty(), false);
		tree.makeEmpty();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		for (int i = 0; i < 10; i++) {
			Assert.assertEquals("tree should not contain anything",
					tree.contains(new IntegerBinaryTreeItem(i)), false);

		}
	}

	@Test
	public void testNormalTreeOperationsInteger() {

		for (int try_num = 0; try_num < 5; try_num++) {
			int num_integers = 3000;
			BinarySearchTree tree = new BinarySearchTree();
			Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

			ArrayList<Integer> inserted_integers = new ArrayList<Integer>();
			for (int i = 0; i < num_integers; i++) {
				if (Math.random() > 0.5) {
					inserted_integers.add(i);
					tree.insert(new IntegerBinaryTreeItem(i));
					Assert.assertEquals("tree should contain i",
							tree.contains(new IntegerBinaryTreeItem(i)), true);
					Assert.assertEquals(
							"tree should find i",
							tree.find(new IntegerBinaryTreeItem(i)).compareTo(
									new IntegerBinaryTreeItem(i)), 0);
				} else {
					Assert.assertEquals("tree should not contain i",
							tree.contains(new IntegerBinaryTreeItem(i)), false);
				}
			}

			int minInt = findMin(inserted_integers);
			Assert.assertEquals("minInt should be correct", tree.findMin()
					.compareTo(new IntegerBinaryTreeItem(minInt)), 0);
			tree.remove(new IntegerBinaryTreeItem(minInt));
			inserted_integers.remove(new Integer(minInt));
			minInt = findMin(inserted_integers);
			Assert.assertEquals("minInt should be correct again", tree.findMin()
					.compareTo(new IntegerBinaryTreeItem(minInt)), 0);

			int maxInt = findMax(inserted_integers);
			Assert.assertEquals("maxInt should be correct", tree.findMax()
					.compareTo(new IntegerBinaryTreeItem(maxInt)), 0);
			tree.remove(new IntegerBinaryTreeItem(maxInt));
			inserted_integers.remove(new Integer(maxInt));
			maxInt = findMax(inserted_integers);
			Assert.assertEquals("maxInt should be correct again", tree.findMax()
					.compareTo(new IntegerBinaryTreeItem(maxInt)), 0);

			Assert.assertEquals("tree should not be empty", tree.isEmpty(),
					false);
			for (int i = 0; i < num_integers; i++) {
				if (inserted_integers.size() < 5)
					break;

				if (inserted_integers.contains(i)) {
					if (Math.random() > 0.5) {
						inserted_integers.remove(new Integer(i));
						tree.remove(new IntegerBinaryTreeItem(i));
						Assert.assertEquals("tree should not contain i",
								tree.contains(new IntegerBinaryTreeItem(i)),
								false);
						try {
							tree.find(new IntegerBinaryTreeItem(i));
							Assert.assertEquals("tree should find i", true,
									false);
						} catch (ItemNotFoundException e) {
							// should come here.
						}
					} else {
						Assert.assertEquals("tree should contain i",
								tree.contains(new IntegerBinaryTreeItem(i)),
								true);
						Assert.assertEquals(
								"tree should find i",
								tree.find(new IntegerBinaryTreeItem(i))
										.compareTo(new IntegerBinaryTreeItem(i)),
								0);
					}
				} else {
					Assert.assertEquals("tree should not contain i",
							tree.contains(new IntegerBinaryTreeItem(i)), false);
					try {
						tree.find(new IntegerBinaryTreeItem(i));
						Assert.assertEquals("tree should not find i", true,
								false);
					} catch (ItemNotFoundException e) {
						// should come here.
					}
				}
			}
		}
	}

	@Test
	public void testNormalTreeOperationsString() 
	{
		for (int try_num = 0; try_num < 5; try_num++) 
		{
			int num_Strings = 3000;
			String rand_String = "";
			String current_String = "";
			String temp = "";
			BinarySearchTree tree = new BinarySearchTree();
			
			Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

			ArrayList<String> inserted_Strings = new ArrayList<String>();
			for (int i = 0; i < num_Strings; i++)
			{
				if (Math.random() > 0.5)				
				{
					rand_String = generateRandomString();  //generate random string
					inserted_Strings.add(rand_String);  //Add it to the list of strings added to the tree
					tree.insert(new StringBinaryTreeItem(rand_String)); //Actually add it to the tree
					Assert.assertEquals("tree should contain " + rand_String,
							tree.contains(new StringBinaryTreeItem(rand_String)), true); //Verify the tree contains the string
					Assert.assertEquals(
							"tree should find " + rand_String,
							tree.find(new StringBinaryTreeItem(rand_String)).compareTo(
									new StringBinaryTreeItem(rand_String)), 0);
				} 
				else 
				{
					rand_String = generateRandomString();
					Assert.assertEquals("tree should not contain " + rand_String,
							tree.contains(new StringBinaryTreeItem(rand_String)), false);
				}
			}

			String minString = findMinString(inserted_Strings);
			Assert.assertEquals("minString should be correct", tree.findMin()
					.compareTo(new StringBinaryTreeItem(minString)), 0);
			tree.remove(new StringBinaryTreeItem(minString));
			temp = minString;  //copy the minString
			inserted_Strings.remove(new String(minString));  //remove it from the list temporarily
			minString = findMinString(inserted_Strings);
			Assert.assertEquals("minString should be correct again", tree.findMin()
					.compareTo(new StringBinaryTreeItem(minString)), 0);
			inserted_Strings.add(temp); //put the string back in for use later 
			
			String maxString = findMaxString(inserted_Strings);
			Assert.assertEquals("maxString should be correct", tree.findMax()
					.compareTo(new StringBinaryTreeItem(maxString)), 0);
			tree.remove(new StringBinaryTreeItem(maxString));
			temp = maxString; //copy the maxString
			inserted_Strings.remove(new String(maxString)); //remove it from the list temporarily
			maxString = findMaxString(inserted_Strings);
			Assert.assertEquals("maxString should be correct again", tree.findMax()
					.compareTo(new StringBinaryTreeItem(maxString)), 0);
			inserted_Strings.add(temp); //add it back for use later
			
			Assert.assertEquals("tree should not be empty", tree.isEmpty(),
					false);
			for (int i = 0; i < inserted_Strings.size(); i++) 
			{
				if (inserted_Strings.size() < 5)
					break;

				current_String = inserted_Strings.get(i);
				if (tree.contains(new StringBinaryTreeItem(current_String)))
				{
					if (Math.random() > 0.5)
					{
						inserted_Strings.remove(new String(current_String));
						tree.remove(new StringBinaryTreeItem(current_String));
						Assert.assertEquals("tree should not contain " + current_String,
								tree.contains(new StringBinaryTreeItem(current_String)),
								false);
						try
						{
							tree.find(new StringBinaryTreeItem(current_String));
							Assert.assertEquals("tree should not find " + current_String, true,
									false);
						}
						catch (ItemNotFoundException e)
						{
							// should come here.
						}
					}
					else
					{
						Assert.assertEquals("tree should contain " + current_String,
								tree.contains(new StringBinaryTreeItem(current_String)),
								true);
						Assert.assertEquals(
								"tree should find " + current_String,
								tree.find(new StringBinaryTreeItem(current_String))
										.compareTo(new StringBinaryTreeItem(current_String)),
								0);
					}
				} 
				else
				{
					Assert.assertEquals("tree should not contain " + current_String,
							tree.contains(new StringBinaryTreeItem(current_String)), false);
					try 
					{
						tree.find(new StringBinaryTreeItem(current_String));
						Assert.assertEquals("tree should not find " + current_String, true,
								false);
					} 
					catch (ItemNotFoundException e) 
					{
						// should come here.
					}
				}
			}
		}
	}

	@Test
	public void testNormalTreeOperationsStudent() 
	{
		for (int try_num = 0; try_num < 5; try_num++) 
		{
			int num_Students = 3000;
			Student rand_Student;
			Student current_Student;
			Student temp;
			BinarySearchTree tree = new BinarySearchTree();
			
			Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

			ArrayList<Student> inserted_Students = new ArrayList<Student>();
			for (int i = 0; i < num_Students; i++)
			{
				if (Math.random() > 0.5)				
				{
					rand_Student = createRandomStudent();  //generate random student
					inserted_Students.add(rand_Student);  //Add it to the list of students added to the tree
					tree.insert(rand_Student.CopyThisStudent()); //Actually add it to the tree
					Assert.assertEquals("tree should contain " + rand_Student.toString(),
							tree.contains(rand_Student.CopyThisStudent()), true); //Verify the tree contains the string
					Assert.assertEquals(
							"tree should find " + rand_Student.toString(),
							tree.find(rand_Student.CopyThisStudent()).compareTo(
									rand_Student.CopyThisStudent()), 0);
				} 
				else 
				{
					rand_Student = createRandomStudent();
					Assert.assertEquals("tree should not contain " + rand_Student.toString(),
							tree.contains(rand_Student.CopyThisStudent()), false);
				}
			}

			Student minStudent = findMinStudent(inserted_Students);
			Assert.assertEquals("minStudent should be correct", tree.findMin()
					.compareTo(minStudent.CopyThisStudent()), 0);
			tree.remove(minStudent.CopyThisStudent());
			temp = minStudent;  //copy the minStudent
			inserted_Students.remove(minStudent);  //remove it from the list temporarily
			minStudent = findMinStudent(inserted_Students);
			Assert.assertEquals("minStudents should be correct again", tree.findMin()
					.compareTo(minStudent.CopyThisStudent()), 0);
			inserted_Students.add(temp); //put the student back in for use later 
			
			Student maxStudent = findMaxStudent(inserted_Students);
			Assert.assertEquals("maxStudent should be correct", tree.findMax()
					.compareTo(maxStudent.CopyThisStudent()), 0);
			tree.remove(maxStudent.CopyThisStudent());
			temp = maxStudent; //copy the maxStudent
			inserted_Students.remove(maxStudent); //remove it from the list temporarily
			maxStudent = findMaxStudent(inserted_Students);
			Assert.assertEquals("maxStudent should be correct again", tree.findMax()
					.compareTo(maxStudent.CopyThisStudent()), 0);
			inserted_Students.add(temp); //add it back for use later
			
			Assert.assertEquals("tree should not be empty", tree.isEmpty(),
					false);
			for (int i = 0; i < inserted_Students.size(); i++) 
			{
				if (inserted_Students.size() < 5)
					break;

				current_Student = inserted_Students.get(i);
				if (tree.contains(current_Student.CopyThisStudent()))
				{
					if (Math.random() > 0.5)
					{
						inserted_Students.remove(current_Student.CopyThisStudent());
						tree.remove(current_Student.CopyThisStudent());
						Assert.assertEquals("tree should not contain " + current_Student.toString(),
								tree.contains(current_Student.CopyThisStudent()),
								false);
						try
						{
							tree.find(current_Student.CopyThisStudent());
							Assert.assertEquals("tree should not find " + current_Student.toString(), true,
									false);
						}
						catch (ItemNotFoundException e)
						{
							// should come here.
						}
					}
					else
					{
						Assert.assertEquals("tree should contain " + current_Student.toString(),
								tree.contains(current_Student.CopyThisStudent()),
								true);
						Assert.assertEquals(
								"tree should find " + current_Student.toString(),
								tree.find(current_Student.CopyThisStudent())
										.compareTo(current_Student.CopyThisStudent()),
								0);
					}
				} 
				else
				{
					Assert.assertEquals("tree should not contain " + current_Student.toString(),
							tree.contains(current_Student.CopyThisStudent()), false);
					try 
					{
						tree.find(current_Student.CopyThisStudent());
						Assert.assertEquals("tree should not find " + current_Student.toString(), true,
								false);
					} 
					catch (ItemNotFoundException e) 
					{
						// should come here.
					}
				}
			}
		}
	}

	@Test
	public void testDuplicationOperation() {
		int num_integers = 3000;
		BinarySearchTree tree = new BinarySearchTree();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		ArrayList<Integer> inserted_integers = new ArrayList<Integer>();
		for (int i = 0; i < num_integers; i++) {
			if (Math.random() > 0.5) {
				inserted_integers.add(i);
				tree.insert(new IntegerBinaryTreeItem(i));
				Assert.assertEquals("tree should contain i",
						tree.contains(new IntegerBinaryTreeItem(i)), true);
			}
		}

		for (Integer i : inserted_integers) {
			if (Math.random() > 0.5) {
				try {
					tree.insert(new IntegerBinaryTreeItem(i));
					Assert.assertEquals("tree should not insert i", false, true);
				} catch (DuplicateItemException e) {
					// should come here
				}
			}

		}

	}
}
