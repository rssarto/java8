package test.dataStructures;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(new Node(18));
        int[] unsortedArray = new int[]{3, 7, 16, 3, 2, 9, 20, 45, 86, 4};
        for (int num : unsortedArray) {
            binaryTree.add(num);
        }
        System.out.println(String.format("Size: %d", binaryTree.getSize()));
        System.out.println("Sorted list asc: " + binaryTree.sort(BinaryTree.SortDirection.ASC));
        System.out.println("Sorted list desc: " + binaryTree.sort(BinaryTree.SortDirection.DESC));
        System.out.println(String.format("Find %d: %s", 45, binaryTree.find(45)));
    }

    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private static class BinaryTree {
        private final Node root;
        private int size = 0;

        public BinaryTree(Node root) {
            this.root = root;
            size++;
        }

        public int getSize() {
            return size;
        }

        private void add(int newValue) {
            this.add(newValue, this.root);
        }

        private void add(int newValue, Node node) {
            if (node.value == newValue) {
                return;
            }

            if (newValue < node.value) {
                if (node.left == null) {
                    node.left = new Node(newValue);
                    size++;
                    return;
                }
                add(newValue, node.left);
            } else {
                if (node.right == null) {
                    node.right = new Node(newValue);
                    size++;
                    return;
                }
                add(newValue, node.right);
            }
        }

        private void sort(Node node, List<Integer> listRef) {
            if (node.left != null) {
                sort(node.left, listRef);
            }
            listRef.add(node.value);
            if (node.right != null) {
                sort(node.right, listRef);
            }
        }

        private void inverse(Node node, List<Integer> listRef) {
            if (node.right != null) {
                inverse(node.right, listRef);
            }
            listRef.add(node.value);
            if (node.left != null) {
                inverse(node.left, listRef);
            }
        }

        public enum SortDirection {
            ASC, DESC
        }

        public List<Integer> sort(SortDirection sortDirection) {
            if (size == 0) {
                return Collections.emptyList();
            }
            final List<Integer> sortedList = new LinkedList<>();
            if (SortDirection.ASC.equals(sortDirection)) {
                this.sort(this.root, sortedList);
            } else {
                this.inverse(this.root, sortedList);
            }

            return sortedList;
        }

        private Node find(int value, Node node){
            if(value == node.value){
                return node;
            }

            if(value < node.value){
                if(node.left != null){
                    return find(value, node.left);
                }
            }else{
                if(node.right != null){
                    return find(value, node.right);
                }
            }
            throw new RuntimeException("Not found");
        }

        public Node find(int value){
            return find(value, root);
        }
    }
}
