package com.github.avramkovk.binarytree;

import java.util.Iterator;

public class BinaryTreeIterator implements Iterator<Integer> {

    private Node current;

    public BinaryTreeIterator(Node head) {
        this.current = getLeftmost(head);
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Integer next() {
        int i = current.getValue();
        if (!current.hasParent() || current.hasRight()) {
            current = getLeftmost(current.getRight());
            return i;
        }

        while (current != null && current.getValue() <= i) {
            current = current.getParent();
        }
        return i;
    }

    private Node getLeftmost(Node node) {
        while (node.hasLeft()) {
            node = node.getLeft();
        }
        return node;
    }
}
