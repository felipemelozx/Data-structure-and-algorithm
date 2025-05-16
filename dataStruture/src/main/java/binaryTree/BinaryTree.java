package binaryTree;

public class BinaryTree {
  private Node root = null;

  public BinaryTree(Node root) {
    this.root = root;
  }

  public void insert(Integer data) {
    if (this.root == null) {
      this.root = new Node(data);
    } else {
      this.insertRecursive(data, this.root);
    }
  }

  private void insertRecursive(Integer data, Node node) {
    if (data < node.getData()) {
      if (node.getLeft() == null) {
        node.setLeft(new Node(data));
      } else {
        insertRecursive(data, node.getLeft());
      }
    } else {
      if (node.getRight() == null) {
        node.setRight(new Node(data));
      } else {
        insertRecursive(data, node.getRight());
      }
    }
  }

  public Boolean search(Integer data) {
    return this.searchRecursive(this.root, data);
  }

  private Boolean searchRecursive(Node node, Integer data) {
    if (node == null) return false;

    if (node.getData().equals(data)) {
      return true;
    }

    if (data < node.getData()) {
      return searchRecursive(node.getLeft(), data);
    } else {
      return searchRecursive(node.getRight(), data);
    }
  }
}
