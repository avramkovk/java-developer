package com.github.avramkovk.binarytree;

public class Node {
    private int value;
    private Node left;
    private Node right;
    private Node parent;

    public Node getParent() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRight(Node right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }

    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
