package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

public class TestBinarySearch{
    private BinarySearchTree<Integer> tree;
    

    @BeforeEach
    public void setUp() throws Exception{
        tree = new BinarySearchTree<>();
    }
    @AfterEach
    public void tearDown() throws Exception {
    }
    
    @Test
    void testAdd(){
        assertTrue(tree.add(3), "Did not return true");
        assertTrue(tree.add(5), "couldnt add another");
        assertFalse(tree.add(3), "Managed to add the same number");
    }

    @Test
    void testHeight(){
        assertEquals(0, tree.height(), "Height was not zero");

        tree.add(3);
        tree.add(5);

        assertEquals(2, tree.height(), "Height was not 2");
    }

    @Test
	void testSize() {
        assertEquals(0, tree.size(), " size is not 0");
		tree.add(3);
		tree.add(2);
		tree.add(0);
		tree.add(5);
		tree.add(1);
		assertTrue(tree.size() == 5, " size is not 5");
		// tree.printTree();
	}
	@Test
	void testClear() {
		tree.add(3);
		tree.add(2);
		tree.add(5);
		assertTrue(tree.add(4));
		assertFalse(tree.add(4));
		tree.clear();
		assertTrue(tree.size() == 0);
		assertTrue(tree.height() == 0);
		tree.printTree();
	}

}
