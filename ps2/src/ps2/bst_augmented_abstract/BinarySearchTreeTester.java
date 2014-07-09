package ps2.bst_augmented_abstract;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import ps2.bst_exception.DuplicateItemException;
import ps2.bst_exception.ItemNotFoundException;


public class BinarySearchTreeTester {

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
					tree.contains(i), false);
			try {
				tree.find(i);
				Assert.assertEquals("find should throw exception", true, false);
			} catch (ItemNotFoundException e) {
				// should come here
			}
		}

		for (int i = 0; i < 100; i++) {
			Assert.assertEquals("tree should be not have keys",
					tree.contains(i), false);
			try {
				tree.remove(i);
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
			tree.insert(i);
		}
		Assert.assertEquals("tree should not be empty", tree.isEmpty(), false);

		for (int i = 0; i < 10; i++) {
			tree.removeMin();
		}
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		Assert.assertEquals("tree should not be empty", tree.isEmpty(), false);
		tree.makeEmpty();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		for (int i = 0; i < 10; i++) {
			Assert.assertEquals("tree should not contain anything",
					tree.contains(i), false);

		}
	}

	@Test
	public void testNormalTreeOperations() {
		for (int try_num = 0; try_num < 5; try_num++) {
			int num_integers = 3000;
			BinarySearchTree tree = new BinarySearchTree();
			Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

			ArrayList<Integer> inserted_integers = new ArrayList<Integer>();
			for (int i = 0; i < num_integers; i++) {
				if (Math.random() > 0.5) {
					inserted_integers.add(i);
					tree.insert(i);
					Assert.assertEquals("tree should contain i",
							tree.contains(i), true);
					Assert.assertEquals("tree should find i", tree.find(i), i);
				} else {
					Assert.assertEquals("tree should not contain i",
							tree.contains(i), false);
				}
			}

			int min = findMin(inserted_integers);
			Assert.assertEquals("min should be correct", tree.findMin(), min);
			tree.remove(min);
			inserted_integers.remove(new Integer(min));
			min = findMin(inserted_integers);
			Assert.assertEquals("min should be correct", tree.findMin(), min);

			int max = findMax(inserted_integers);
			Assert.assertEquals("max should be correct", tree.findMax(), max);
			tree.remove(max);
			inserted_integers.remove(new Integer(max));
			max = findMax(inserted_integers);
			Assert.assertEquals("max should be correct", tree.findMax(), max);

			Assert.assertEquals("tree should not be empty", tree.isEmpty(),
					false);
			for (int i = 0; i < num_integers; i++) {
				if (inserted_integers.size() < 5)
					break;

				if (inserted_integers.contains(i)) {
					if (Math.random() > 0.5) {
						inserted_integers.remove(new Integer(i));
						tree.remove(i);
						Assert.assertEquals("tree should not contain i",
								tree.contains(i), false);
						try {
							tree.find(i);
							Assert.assertEquals("tree should find i", true,
									false);
						} catch (ItemNotFoundException e) {
							// should come here.
						}
					} else {
						Assert.assertEquals("tree should contain i",
								tree.contains(i), true);
						Assert.assertEquals("tree should find i", tree.find(i),
								i);
					}
				} else {
					Assert.assertEquals("tree should not contain i",
							tree.contains(i), false);
					try {
						tree.find(i);
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
	public void testDuplicationOperation() {
		int num_integers = 3000;
		BinarySearchTree tree = new BinarySearchTree();
		Assert.assertEquals("tree should be empty", tree.isEmpty(), true);

		ArrayList<Integer> inserted_integers = new ArrayList<Integer>();
		for (int i = 0; i < num_integers; i++) {
			if (Math.random() > 0.5) {
				inserted_integers.add(i);
				tree.insert(i);
				Assert.assertEquals("tree should contain i", tree.contains(i),
						true);
			}
		}

		for (Integer i : inserted_integers) {
			if (Math.random() > 0.5) {
				try {
					tree.insert(i);
					Assert.assertEquals("tree should not insert i", false, true);
				} catch (DuplicateItemException e) {
					// should come here
				}
			}

		}

	}
}
