package binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BstSearchOrder<E extends Comparable<E>> {

    class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            size++;
        }
    }

    private Node root = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addNode(E e) {
        if (root == null) {
            root = new Node(e);
        }
        root = addNode(e, root);
    }

    /**
     * 向以node为根的二叉树中插入新的节点
     *
     * @return 插入新节点后的二叉树的根
     */
    private Node addNode(E e, Node node) {
        if (node == null) {
            return new Node(e);
        } else if (e.compareTo(node.e) < 0) {
            /* 往左加节点，可以想象，如果node.left没有插入新节点，addNode(e, node.left)在node.left节点执行到最后return了当前node，
            也就是node.left = addNode(e, node.left) → node.left = node.left，
            如果添加了，那么node.left = new Node(e)，在左子树上添加了节点 */
            node.left = addNode(e, node.left);
        } else if (e.compareTo(node.e) > 0) {
            node.right = addNode(e, node.right);
        }
        return node;//把新加节点后的二叉树通过递归层层返回，获得新的二叉树
    }

    public boolean getNode(E e) {
        if (root == null) {
            root = new Node(e);
        }
        return getNode(e, root);
    }

    private boolean getNode(E e, Node node) {
        if (node == null) {
            return false;
        } else if (e.compareTo(node.e) < 0) {
            return getNode(e, node.left);
        } else if (e.compareTo(node.e) > 0) {
            return getNode(e, node.right);
        }
        return true;
    }


    /**
     * 使用非递归实现前序遍历
     */
    public void preOrderNR() {
        preOrderNR(root);
    }

    private void preOrderNR(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node node1 = stack.pop();
            System.out.println(node1.e);
            if (node1.left != null) {
                stack.push(node1.left);
            }
            if (node1.right != null) {
                stack.push(node1.right);
            }
        }
    }

    /**
     * 使用递归实现中序遍历
     */
    StringBuffer stringBuffer = new StringBuffer("");

    public String toString() {
        midOrder(root, 0, stringBuffer);
        return stringBuffer.toString();
    }

    private void midOrder(Node node, int depth, StringBuffer stringBuffer) {
        if (node == null) {
            stringBuffer.append(getDepthString(depth)).append("n").append("\n");
            return;
        }
        midOrder(node.left, depth + 1, stringBuffer);
        stringBuffer.append(getDepthString(depth)).append(node.e).append("\n");
        midOrder(node.right, depth + 1, stringBuffer);
    }

    private String getDepthString(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        while (depth-- > 0) {
            stringBuilder.append("--------");
        }
        return stringBuilder.toString();
    }

    public void floorOrder() {
        floorOrder(root);
    }

    public void floorOrder(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node node1 = queue.remove();
            System.out.println(node1.e);
            if (node1.left != null) {
                queue.add(node1.left);
            }
            if (node1.right != null) {
                queue.add(node1.right);
            }
        }
    }


    public static void main(String[] args) {
        BstSearchOrder<Integer> bstSearchOrder = new BstSearchOrder<>();
        bstSearchOrder.addNode(1);
        bstSearchOrder.addNode(3);
        bstSearchOrder.addNode(5);
        bstSearchOrder.addNode(2);
        bstSearchOrder.addNode(11);
        bstSearchOrder.addNode(0);
        bstSearchOrder.floorOrder();
    }

}
