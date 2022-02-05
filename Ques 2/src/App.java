public class App {
    public static void main(String[] args) throws Exception {
        BinarySearch binrySearch = new BinarySearch();
        binrySearch.root = BinarySearch.newNode(50);
        binrySearch.root.left = BinarySearch.newNode(30);
        binrySearch.root.right = BinarySearch.newNode(60);
        binrySearch.root.left.left = BinarySearch.newNode(10);
        binrySearch.root.right.left = BinarySearch.newNode(55);
        BinarySearch.bstToSkewed(binrySearch.root, Order.ascendingOrder);
        BinarySearch.travelNode(BinarySearch.headNode);
    }
}
