public class SplayTree {
    private Node root;

    public SplayTree() {
        root = null;
    }

    private void splay(Node x) {
        while (x.getParent() != null) {
            if (x.getParent().getParent() == null) {
                if (x == x.getParent().getLeft()) {
                    //zig rotation
                    rightRotate(x.getParent());
                } else {
                    //zag rotation
                    leftRotate(x.getParent());
                }
            } else if (x == x.getParent().getLeft() && x.getParent() == x.getParent().getParent().getLeft()) {
                //zig-zig rotation
                rightRotate(x.getParent().getParent());
                rightRotate(x.getParent());
            } else if (x == x.getParent().getRight() && x.getParent() == x.getParent().getParent().getRight()) {
                //zag-zag rotation
                leftRotate(x.getParent().getParent());
                leftRotate(x.getParent());
            } else if (x == x.getParent().getRight() && x.getParent() == x.getParent().getParent().getLeft()) {
                //zig-zag rotation
                leftRotate(x.getParent());
                rightRotate(x.getParent());
            } else {
                //zag-zig rotation
                rightRotate(x.getParent());
                leftRotate(x.getParent());
            }
        }
    }

    private void leftRotate(Node p) {
        Node x = p.getRight();
        p.setRight(x.getLeft());
        if (x.getLeft() != null) {
            x.getLeft().setParent(p);
        }
        x.setParent(p.getParent());
        if (p.getParent() == null) {
            this.root = x;
        } else if (p == p.getParent().getLeft()) {
            p.getParent().setLeft(x);
        } else {
            p.getParent().setRight(x);
        }
        x.setLeft(p);
        p.setParent(x);
    }

    private void rightRotate(Node p) {
        Node x = p.getLeft();
        p.setLeft(x.getRight());
        if (x.getRight() != null) {
            x.getRight().setParent(p);
        }
        x.setParent(p.getParent());
        if (p.getParent() == null) {
            this.root = x;
        } else if (p == p.getParent().getRight()) {
            p.getParent().setRight(x);
        } else {
            p.getParent().setLeft(x);
        }
        x.setRight(p);
        p.setParent(x);
    }

    public void insert(int key) {
        Node node = new Node(key);
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            if (node.getKey() < current.getKey()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        node.setParent(parent);
        if (parent == null) {
            root = node;
        } else if (node.getKey() < parent.getKey()) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        splay(node);
    }

    public void delete(int key) {
        Node current = root;
        Node node = search(key);
        if (node == null) {
            return;
        }
        splay(node);
        if (node.getLeft() == null) {
            replace(node, node.getRight());
        } else if (node.getRight() == null) {
            replace(node, node.getLeft());
        } else {
            Node minRight = findMin(node.getRight());
            if (minRight.getParent() != node) {
                replace(minRight, minRight.getRight());
                minRight.setRight(node.getRight());
                minRight.getRight().setParent(minRight);
            }
            replace(node, minRight);
            minRight.setLeft(node.getLeft());
            minRight.getLeft().setParent(minRight);
        }
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private void replace(Node node, Node newNode) {
        if (node.getParent() == null) {
            root = newNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(newNode);
        } else {
            node.getParent().setRight(newNode);
        }
        if (newNode != null) {
            newNode.setParent(node.getParent());
        }
    }

    public Node search(int key) {
        Node current = root;
        while (current != null) {
            if (key < current.getKey()) {
                current = current.getLeft();
            } else if (key > current.getKey()) {
                current = current.getRight();
            } else {
                splay(current);
                return current;
            }
        }
        return null;
    }
}
