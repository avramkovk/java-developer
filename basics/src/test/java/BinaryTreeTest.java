import com.github.avramkovk.binarytree.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BinaryTreeTest {

    private BinaryTree getTree() {
        BinaryTree tree = new BinaryTree();
        tree.add(10);
        tree.add(6);
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(1);
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(0);
        tree.add(-1);
        tree.add(14);
        tree.add(12);
        tree.add(11);
        tree.add(13);
        tree.add(18);
        tree.add(16);
        tree.add(15);
        tree.add(17);
        tree.add(19);
        tree.add(20);
        return tree;
    }

    @Test
    public void test() {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        tree.add(8);

        Assert.assertEquals(3, tree.levels());
        Assert.assertArrayEquals(new Integer[]{2, 4, 6, 8}, tree.getLeaves().toArray(new Integer[0]));

        tree.remove(5);
        Assert.assertArrayEquals(new Integer[]{2, 6, 8}, tree.getLeaves().toArray(new Integer[0]));
        Assert.assertTrue(tree.contains(4));
    }


    @Test
    public void testAdd() {
        BinaryTree bt = new BinaryTree();
        bt.add(2);
        bt.add(1);
        bt.add(0);
        Assert.assertTrue(bt.contains(2));
        Assert.assertTrue(bt.contains(1));
        Assert.assertTrue(bt.contains(0));
    }

    @Test
    public void testSize() {
        BinaryTree bt0 = new BinaryTree();
        Assert.assertEquals(0, bt0.size());

        BinaryTree bt1 = new BinaryTree();
        bt1.add(10);
        Assert.assertEquals(1, bt1.size());

        BinaryTree bt2 = new BinaryTree();
        bt2.add(10);
        bt2.add(9);
        Assert.assertEquals(2, bt2.size());

        BinaryTree bt3 = new BinaryTree();
        bt3.add(10);
        bt3.add(9);
        bt3.add(8);
        Assert.assertEquals(3, bt3.size());

        BinaryTree bt4 = new BinaryTree();
        bt4.add(10);
        bt4.add(6);
        bt4.add(8);
        bt4.add(7);
        bt4.add(9);
        bt4.add(1);
        bt4.add(3);
        bt4.add(2);
        bt4.add(4);
        bt4.add(0);
        bt4.add(-1);
        bt4.add(14);
        bt4.add(12);
        bt4.add(11);
        bt4.add(13);
        bt4.add(18);
        bt4.add(16);
        bt4.add(15);
        bt4.add(17);
        bt4.add(19);
        bt4.add(20);
        bt4.remove(3);
        Assert.assertEquals(20, bt4.size());
        bt4.remove(-1);
        Assert.assertEquals(19, bt4.size());
        bt4.remove(1);
        Assert.assertEquals(18, bt4.size());
        bt4.remove(20);
        Assert.assertEquals(17, bt4.size());
        bt4.remove(16);
        Assert.assertEquals(16, bt4.size());
        bt4.remove(18);
        Assert.assertEquals(15, bt4.size());
        bt4.remove(10);
        Assert.assertEquals(14, bt4.size());
    }

    @Test
    public void testRemoveHead() {
        BinaryTree bt0 = new BinaryTree();
        bt0.add(7);
        bt0.add(3);
        bt0.add(10);
        List<Integer> bt0Level1 = Collections.singletonList(7);
        List<Integer> bt0Level2 = Arrays.asList(3, 10);
        Assert.assertEquals(bt0Level1, bt0.getValuesAtLevel(1));
        Assert.assertEquals(bt0Level2, bt0.getValuesAtLevel(2));
        Assert.assertTrue(bt0.remove(7));
        List<Integer> updatedBt0Level1 = Collections.singletonList(3);
        List<Integer> updatedBt0Level2 = Collections.singletonList(10);
        Assert.assertEquals(updatedBt0Level1, bt0.getValuesAtLevel(1));
        Assert.assertEquals(updatedBt0Level2, bt0.getValuesAtLevel(2));

        BinaryTree bt1 = new BinaryTree();
        bt1.add(7);
        bt1.add(3);
        bt1.add(4);
        bt1.add(1);
        List<Integer> bt1Level1 = Collections.singletonList(7);
        List<Integer> bt1Level2 = Collections.singletonList(3);
        List<Integer> bt1Level3 = Arrays.asList(1, 4);
        Assert.assertEquals(bt1Level1, bt1.getValuesAtLevel(1));
        Assert.assertEquals(bt1Level2, bt1.getValuesAtLevel(2));
        Assert.assertEquals(bt1Level3, bt1.getValuesAtLevel(3));
        Assert.assertTrue(bt1.remove(7));
        Assert.assertFalse(bt1.contains(7));
        List<Integer> updatedBt1Level1 = Collections.singletonList(4);
        List<Integer> updatedBt1Level2 = Collections.singletonList(3);
        List<Integer> updatedBt1Level3 = Collections.singletonList(1);
        Assert.assertEquals(updatedBt1Level1, bt1.getValuesAtLevel(1));
        Assert.assertEquals(updatedBt1Level2, bt1.getValuesAtLevel(2));
        Assert.assertEquals(updatedBt1Level3, bt1.getValuesAtLevel(3));

        BinaryTree bt2 = new BinaryTree();
        bt2.add(7);
        bt2.add(10);
        bt2.add(11);
        bt2.add(8);
        List<Integer> bt2Level1 = Collections.singletonList(7);
        List<Integer> bt2Level2 = Collections.singletonList(10);
        List<Integer> bt2Level3 = Arrays.asList(8, 11);
        Assert.assertEquals(bt2Level1, bt2.getValuesAtLevel(1));
        Assert.assertEquals(bt2Level2, bt2.getValuesAtLevel(2));
        Assert.assertEquals(bt2Level3, bt2.getValuesAtLevel(3));
        Assert.assertTrue(bt2.remove(7));
        Assert.assertFalse(bt2.contains(7));
        List<Integer> updatedBt2Level1 = Collections.singletonList(8);
        List<Integer> updatedBt2Level2 = Collections.singletonList(10);
        List<Integer> updatedBt2Level3 = Collections.singletonList(11);
        Assert.assertEquals(updatedBt2Level1, bt2.getValuesAtLevel(1));
        Assert.assertEquals(updatedBt2Level2, bt2.getValuesAtLevel(2));
        Assert.assertEquals(updatedBt2Level3, bt2.getValuesAtLevel(3));

        BinaryTree bt3 = new BinaryTree();
        bt3.add(7);
        bt3.add(3);
        bt3.add(10);
        bt3.add(1);
        List<Integer> bt3Level1 = Collections.singletonList(7);
        List<Integer> bt3Level2 = Arrays.asList(3, 10);
        List<Integer> bt3Level3 = Collections.singletonList(1);
        Assert.assertEquals(bt3Level1, bt3.getValuesAtLevel(1));
        Assert.assertEquals(bt3Level2, bt3.getValuesAtLevel(2));
        Assert.assertEquals(bt3Level3, bt3.getValuesAtLevel(3));
        Assert.assertTrue(bt3.remove(7));
        List<Integer> updatedBt3Level1 = Collections.singletonList(3);
        List<Integer> updatedBt3Level2 = Arrays.asList(1, 10);
        Assert.assertEquals(updatedBt3Level1, bt3.getValuesAtLevel(1));
        Assert.assertEquals(updatedBt3Level2, bt3.getValuesAtLevel(2));
    }

    @Test
    public void testLevels() {
        BinaryTree bt0 = new BinaryTree();
        Assert.assertEquals(0, bt0.levels());

        BinaryTree bt1 = new BinaryTree();
        bt1.add(10);
        Assert.assertEquals(1, bt1.levels());

        BinaryTree bt2 = new BinaryTree();
        bt2.add(10);
        bt2.add(9);
        Assert.assertEquals(2, bt2.levels());

        BinaryTree bt3 = new BinaryTree();
        bt3.add(10);
        bt3.add(9);
        bt3.add(8);
        bt3.add(7);
        Assert.assertEquals(4, bt3.levels());
    }

    @Test
    public void testContains() {
        BinaryTree bt = new BinaryTree();
        bt.add(10);
        bt.add(9);
        bt.add(8);
        bt.add(7);
        Assert.assertTrue(bt.contains(7));
        Assert.assertTrue(bt.contains(10));
        Assert.assertFalse(bt.contains(4));
        Assert.assertFalse(bt.contains(-4));
    }

    @Test
    public void testGetLeaves() {
        BinaryTree bt = getTree();

        List<Integer> actual = Arrays.asList(-1, 2, 4, 7, 9, 11, 13, 15, 17, 20);
        Assert.assertEquals(actual, bt.getLeaves());
    }

    @Test
    public void testValuesAtLevel() {
        BinaryTree bt = getTree();

        List<Integer> level1 = Collections.singletonList(10);
        List<Integer> level2 = Arrays.asList(6, 14);
        List<Integer> level3 = Arrays.asList(1, 8, 12, 18);
        List<Integer> level4 = Arrays.asList(0, 3, 7, 9, 11, 13, 16, 19);
        List<Integer> level5 = Arrays.asList(-1, 2, 4, 15, 17, 20);

        Assert.assertEquals(level1, bt.getValuesAtLevel(1));
        Assert.assertEquals(level2, bt.getValuesAtLevel(2));
        Assert.assertEquals(level3, bt.getValuesAtLevel(3));
        Assert.assertEquals(level4, bt.getValuesAtLevel(4));
        Assert.assertEquals(level5, bt.getValuesAtLevel(5));
        Assert.assertEquals(Collections.emptyList(), bt.getValuesAtLevel(6));
    }

    @Test
    public void testRemoveLeaf() {
        BinaryTree bt = new BinaryTree();
        bt.add(7);
        bt.add(3);
        bt.add(10);

        List<Integer> level_1 = Collections.singletonList(7);
        List<Integer> level_2 = Arrays.asList(3, 10);

        Assert.assertTrue(bt.remove(10));
        Assert.assertTrue(bt.remove(3));
        Assert.assertFalse(bt.remove(-11));

        Assert.assertEquals(level_1, bt.getValuesAtLevel(1));
        Assert.assertEquals(Collections.emptyList(), bt.getValuesAtLevel(2));
    }

    @Test
    public void testRemoveNodeIfHasOnlyLeftChild() {
        BinaryTree bt = new BinaryTree();
        bt.add(7);
        bt.add(3);
        bt.add(1);

        List<Integer> level_1 = Collections.singletonList(7);
        List<Integer> level_2 = Collections.singletonList(3);
        List<Integer> level_3 = Collections.singletonList(1);

        Assert.assertTrue(bt.remove(3));
        level_2 = level_3;

        Assert.assertEquals(level_1, bt.getValuesAtLevel(1));
        Assert.assertEquals(level_2, bt.getValuesAtLevel(2));
        Assert.assertEquals(Collections.emptyList(), bt.getValuesAtLevel(3));
    }

    @Test
    public void testRemoveNodeIfHasOnlyRightChild() {
        BinaryTree bt = new BinaryTree();
        bt.add(7);
        bt.add(8);
        bt.add(9);

        List<Integer> level_1 = Collections.singletonList(7);
        List<Integer> level_2 = Collections.singletonList(8);
        List<Integer> level_3 = Collections.singletonList(9);

        Assert.assertTrue(bt.remove(8));
        level_2 = level_3;

        Assert.assertEquals(level_1, bt.getValuesAtLevel(1));
        Assert.assertEquals(level_2, bt.getValuesAtLevel(2));
        Assert.assertEquals(Collections.emptyList(), bt.getValuesAtLevel(3));
    }

    @Test
    public void testRemoveNonExistentElement() {
        BinaryTree bt = new BinaryTree();
        bt.add(7);
        bt.add(8);
        bt.add(9);
        Assert.assertFalse(bt.remove(-1));
    }

    @Test
    public void testRemoveIfHasTwoChildren() {
        BinaryTree bt = getTree();

        List<Integer> level_1 = Collections.singletonList(10);
        List<Integer> level_2 = Arrays.asList(6, 14);
        List<Integer> level_3 = Arrays.asList(1, 8, 12, 18);
        List<Integer> level_4 = Arrays.asList(0, 3, 7, 9, 11, 13, 16, 19);
        List<Integer> level_5 = Arrays.asList(-1, 2, 4, 15, 17, 20);

        Assert.assertTrue(bt.remove(8));
        Assert.assertFalse(bt.contains(8));
        Assert.assertTrue(bt.remove(12));
        Assert.assertFalse(bt.contains(12));

        level_3 = Arrays.asList(1, 7, 11, 18);
        level_4 = Arrays.asList(0, 3, 9, 13, 16, 19);
        Assert.assertEquals(level_1, bt.getValuesAtLevel(1));
        Assert.assertEquals(level_2, bt.getValuesAtLevel(2));
        Assert.assertEquals(level_3, bt.getValuesAtLevel(3));
        Assert.assertEquals(level_4, bt.getValuesAtLevel(4));
        Assert.assertEquals(level_5, bt.getValuesAtLevel(5));

        Assert.assertTrue(bt.remove(1));
        Assert.assertFalse(bt.contains(1));
        Assert.assertTrue(bt.remove(18));
        Assert.assertFalse(bt.contains(18));

        level_3 = Arrays.asList(0, 7, 11, 17);
        level_4 = Arrays.asList(-1, 3, 9, 13, 16, 19);
        level_5 = Arrays.asList(2, 4, 15, 20);
        Assert.assertEquals(level_1, bt.getValuesAtLevel(1));
        Assert.assertEquals(level_2, bt.getValuesAtLevel(2));
        Assert.assertEquals(level_3, bt.getValuesAtLevel(3));
        Assert.assertEquals(level_4, bt.getValuesAtLevel(4));
        Assert.assertEquals(level_5, bt.getValuesAtLevel(5));
    }

    @Test
    public void testIterator() {
        BinaryTree bt = new BinaryTree();
        bt.add(5);
        bt.add(3);
        bt.add(7);
        bt.add(6);

        Iterator<Integer> it = bt.iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(3), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(5), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(6), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(7), it.next());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testIterator2() {
        BinaryTree bt = new BinaryTree();
        bt.add(5);
        bt.add(3);
        bt.add(0);
        bt.add(2);
        bt.add(4);
        bt.add(7);
        bt.add(6);
        bt.add(8);

        System.out.println(bt.getValuesAtLevel(1));
        System.out.println(bt.getValuesAtLevel(2));
        System.out.println(bt.getValuesAtLevel(3));
        System.out.println(bt.getValuesAtLevel(4));
        System.out.println(bt.getValuesAtLevel(5));

        Iterator<Integer> it = bt.iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(0), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(2), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(3), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(4), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(5), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(6), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(7), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(8), it.next());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testForEach() {
        BinaryTree bt = new BinaryTree();
        bt.add(5);
        bt.add(3);
        bt.add(0);
        bt.add(2);
        bt.add(4);
        bt.add(7);
        bt.add(6);
        bt.add(8);

        for (Integer t : bt) {
            System.out.println(t);
        }
    }
}