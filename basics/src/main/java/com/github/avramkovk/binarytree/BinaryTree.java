package com.github.avramkovk.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree implements Iterable<Integer> {

    private Node head;
    private int size = 0;

    public boolean remove(int value) {
        Node delete = getRecursivelyNode(head, value);
        if (delete == null) {
            return false;
        } else if (delete.hasLeft()) {
            removeRightmostNodeFor(delete);
        } else if (delete.hasRight()) {
            removeLeftmostNodeFor(delete);
        } else if (delete.getParent() == null) {
            head = null;
            size = 0;
            return true;
        } else {
            removeLeaf(delete);
        }
        return true;
    }

    private void removeRightmostNodeFor(Node delete) {
        Node rightmostAtFirstLeft = getRightmost(delete.getLeft());
        Node parent = rightmostAtFirstLeft.getParent();
        delete.setValue(rightmostAtFirstLeft.getValue());
        if (rightmostAtFirstLeft.getParent() == delete) {
            parent.setLeft(rightmostAtFirstLeft.getLeft());
        } else {
            parent.setRight(null);
        }
        size = size - 1;
    }

    private void removeLeftmostNodeFor(Node delete) {
        Node leftmostAtFirstRight = getLeftmost(delete.getRight());
        Node parent = leftmostAtFirstRight.getParent();
        delete.setValue(leftmostAtFirstRight.getValue());
        if (leftmostAtFirstRight.getParent() == delete) {
            parent.setRight(leftmostAtFirstRight.getRight());
        } else {
            parent.setLeft(null);
        }
        size = size - 1;
    }

    private void removeLeaf(Node delete) {
        Node parent = delete.getParent();
        if (delete == parent.getLeft()) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
        size = size - 1;
    }

    private Node getRightmost(Node node) {
        while (node.hasRight()) {
            node = node.getRight();
        }
        return node;
    }

    private Node getLeftmost(Node node) {
        while (node.hasLeft()) {
            node = node.getLeft();
        }
        return node;
    }

    public Iterator<Integer> iterator() {
        return new BinaryTreeIterator(head);
    }

    public List<Integer> getValuesAtLevel(int level) {
        List<Integer> valuesAtLevel = new LinkedList<>();
        return getRecursivelyValuesAtLevel(head, level, valuesAtLevel, 1);
    }

    private List<Integer> getRecursivelyValuesAtLevel(Node head, int level, List<Integer> valuesAtLevel,
                                                      int nodeLevel) {
        if (level == nodeLevel) {
            valuesAtLevel.add(head.getValue());
            return valuesAtLevel;
        }

        if (head.hasLeft()) {
            getRecursivelyValuesAtLevel(head.getLeft(), level, valuesAtLevel, nodeLevel + 1);
        }

        if (head.hasRight()) {
            getRecursivelyValuesAtLevel(head.getRight(), level, valuesAtLevel, nodeLevel + 1);
        }
        return valuesAtLevel;
    }

    public int levels() {
        return getRecursivelyLevels(head, 1);
    }

    private int getRecursivelyLevels(Node head, int level) {
        if (size == 0) {
            return 0;
        }
        int leftLevel = level;
        int rightLevel = level;
        if (head.hasLeft()) {
            leftLevel = getRecursivelyLevels(head.getLeft(), level + 1);
        }

        if (head.hasRight()) {
            rightLevel = getRecursivelyLevels(head.getRight(), level + 1);
        }
        return Math.max(leftLevel, rightLevel);
    }

    public List<Integer> getLeaves() {
        List<Integer> leaves = new LinkedList<>();
        return getRecursivelyLeaves(head, leaves);
    }

    private List<Integer> getRecursivelyLeaves(Node head, List<Integer> leaves) {
        if (head.hasLeft()) {
            getRecursivelyLeaves(head.getLeft(), leaves);
            if (!head.hasRight() && !head.hasLeft()) {
                leaves.add(head.getValue());
            }
        }

        if (head.hasRight()) {
            getRecursivelyLeaves(head.getRight(), leaves);
        } else {
            if (!head.hasLeft()) {
                leaves.add(head.getValue());
            }
        }
        return leaves;
    }

    public boolean contains(int value) {
        Node current = head;
        if (current.getValue() != value) {
            current = getRecursivelyNode(current, value);
        }
        if (current == null) {
            return false;
        }
        return current.getValue() == value;
    }

    private Node getRecursivelyNode(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == value) {
            return node;
        }
        if (value < node.getValue()) {
            node = getRecursivelyNode(node.getLeft(), value);
        } else {
            node = getRecursivelyNode(node.getRight(), value);
        }
        return node;
    }

    public int size() {
        return size;
    }

    public void add(int value) {
        size++;
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            Node parent;
            while (true) {
                parent = current;
                if (value < current.getValue()) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }
}