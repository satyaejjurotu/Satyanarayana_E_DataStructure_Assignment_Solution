
public class BinarySearch {

    static class Node {
        Node left;
        Node right;
        int data;
    }

    static Node newNode(int data) {
        Node node = new Node();
        node.data = data;
        node.left = null;
        node.right = null;
        return node;
    }

    Node root;
    static Node prevNode = null;
    static Node headNode = null;

    public static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.data);
        inOrder(node.right);
    }

    /// Till root is null we will travel to each node and print value.
    static void travelNode(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        travelNode(root.right);
    }

    static void bstToSkewed(Node root, Order order) {

        // Base Case
        if (root == null) {
            return;
        }

        if (order == Order.ascendingOrder) {
            bstToSkewed(root.left, order);
        } else {
            bstToSkewed(root.right, order);
        }

        Node rightNode = root.right;
        Node leftNode = root.left;

        // Condition to check if the root Node
        // of the skewed tree is not defined
        if (headNode == null) {
            headNode = root;
            root.left = null;
            prevNode = root;
        } else {
            prevNode.right = root;
            root.left = null;
            prevNode = root;
        }

        if (order == Order.ascendingOrder) {
            bstToSkewed(rightNode, order);
        } else {
            bstToSkewed(leftNode, order);
        }
    }

}

enum Order {
    ascendingOrder, descendingOrder
}