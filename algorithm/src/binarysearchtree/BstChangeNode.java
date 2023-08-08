package binarysearchtree;

public class BstChangeNode<E extends Comparable<E>> {
//main-modify
    class Node {
        private E e;
        private Node left, right;
        private int inSize;

        public Node(E e) {
            this.e = e;
            inSize = 1;
            left = null;
            right = null;
        }
    }

    private Node root = null;

    public int getSize() {
        return root == null ? 0 : root.inSize;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void addNode(E e) {
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
        node.inSize++;
        return node;//把新加节点后的二叉树通过递归层层返回，获得新的二叉树
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


//--------------------------------------------- 以上为模板-----------------------------------------

    /**
     * 删除E值为e的节点
     *
     * @param e E
     */
    public void removeNode(E e) {
        removeNode(e, root);
    }

    public Node removeNode(E e, Node node) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) > 0) {
            node.right = removeNode(e, node.right);
        } else if (e.compareTo(node.e) < 0) {
            node.left = removeNode(e, node.left);
        } else { //当前节点就是要删除的点
            if (node.left == null) { //左子树为空，直接把右节点当成当前节点返回
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else { //待删除节点左右子树均不为空，找到一个值最接近的节点（左子树最大或者右子树最小），代替当前节点
                Node replaceNode = getMax(node.left); //新节点是左子树的最大节点
                replaceNode.left = removeMax(node.right); //新节点A的左子树为原节点B的删去A节点后的左子树
                replaceNode.right = node.right;//新节点A的右子树为原节点B的右子树
                node.left = null;
                node.right = null;
                return replaceNode; //将新节点作为当前节点返回
            }
        }
        node.inSize--;
        return node;
    }

    /**
     * 删除最小节点
     */
    public Node removeMin() {
        Node minNode = getMin(root);
        root = removeMin(root);
        return minNode;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {//返回右子树
            Node rightNode = node.right; //此时rightNode指向右子树的根
            node.right = null;//当前节点的右节点指向空
            return rightNode;
        }
        node.left = removeMin(node.left);
        node.inSize--;
        return node;
    }

    /**
     * 返回被删除的最大节点
     *
     * @return
     */
    public Node removeMax() {
        Node maxNode = getMax(root);
        root = removeMax(root);
        return maxNode;
    }

    /**
     * 返回删除最大节点后该二叉树的根
     */
    public Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) { //右子树为空，当前节点是最大节点，删去，返回左节点作为当前节点
            Node leftNode = node.left;
            node.left = null;
            return leftNode;
        }
        node.right = removeMax(node.right);
        node.inSize--;
        return node;
    }

    /**
     * @return 最小值
     */
    public Node getMin(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        Node node = rootNode;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * @return 最大值
     */
    public Node getMax(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        Node node = rootNode;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * @return 该值在此二叉树中排名（从小到大，从1开始数）
     */
    private boolean hasThis = false;

    public int getRank(E e) {
        int rank = getRank(root, e);
        return hasThis ? rank : -1;
    }

    public int getRank(Node node, E e) {
        if (node == null) {
            return 0;
        }
        if (e.compareTo(node.e) > 0) {
            int leftSize = node.left == null ? 0 : node.left.inSize;
            return leftSize + 1 + getRank(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            return getRank(node.left, e);
        } else {
            hasThis = true;
            return getRank(node.left, e) + 1; //只会触发一次，如果从1开始就+1;
        }
    }


    public static void main(String[] args) {
        BstChangeNode<Integer> bstChangeNode = new BstChangeNode<>();
        bstChangeNode.addNode(1);
        bstChangeNode.addNode(3);
        bstChangeNode.addNode(5);
        bstChangeNode.addNode(2);
        bstChangeNode.addNode(11);
        bstChangeNode.addNode(0);
        System.out.println(bstChangeNode.getSize());
        bstChangeNode.removeMax();
        System.out.println(bstChangeNode.getSize());
        bstChangeNode.removeMin();
        System.out.println(bstChangeNode.getSize());
        bstChangeNode.removeNode(5);
        System.out.println(bstChangeNode.getSize());


    }

}
