public class Test {
    public static void main(String[] args) {
        /*PierceArrayUnorderedList<Integer> AUL = new PierceArrayUnorderedList<>();
        AUL.addToFront(1);
         System.out.println(AUL);
        AUL.addToFront(3);
        AUL.addToRear(2);
        System.out.println(AUL);
        AUL.removeFirst();
        System.out.println(AUL);
        System.out.println(AUL.removeLast());
        System.out.println(AUL);*/

        /*//Instantiate with root
        PierceLinkedBinaryTree<Integer> LBT = new PierceLinkedBinaryTree<>(2);
        //Build left tree
        BinaryTreeNode<Integer> left11 = new BinaryTreeNode<Integer>(7);
        LBT.getRootNode().setLeft(left11);
        BinaryTreeNode<Integer> left21 = new BinaryTreeNode<Integer>(2);
        left11.setLeft(left21);
        BinaryTreeNode<Integer> left22 = new BinaryTreeNode<Integer>(6);
        left11.setRight(left22);
        BinaryTreeNode<Integer> left31 = new BinaryTreeNode<Integer>(5);
        left22.setLeft(left31);
        BinaryTreeNode<Integer> left32 = new BinaryTreeNode<Integer>(11);
        left22.setRight(left32);

        //Build right tree
        BinaryTreeNode<Integer> right11 = new BinaryTreeNode<Integer>(5);
        LBT.getRootNode().setRight(right11);
        BinaryTreeNode<Integer> right21 = new BinaryTreeNode<Integer>(9);
        right11.setRight(right21);
        BinaryTreeNode<Integer> right31 = new BinaryTreeNode<Integer>(4);
        right21.setLeft(right31);

        PierceArrayUnorderedList<Integer> tempList = new PierceArrayUnorderedList<>();
        LBT.inOrder(LBT.getRootNode(), tempList);
        System.out.println(tempList);*/

        //Linked Binary Search Tree tests
        PierceLinkedBinarySearchTree<Integer> LBST = new PierceLinkedBinarySearchTree<>(8);
        LBST.addElement(1);
        LBST.addElement(3);
        LBST.addElement(6);
        LBST.addElement(4);
        LBST.addElement(7);
        LBST.addElement(10);
        LBST.addElement(14);
        System.out.println(LBST.find(4));
        System.out.println(LBST.contains(4));
    }
}
