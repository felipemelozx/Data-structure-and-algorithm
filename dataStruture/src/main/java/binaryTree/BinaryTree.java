package binaryTree;

import java.util.ArrayList;
import java.util.List;

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

  public List<Integer> preorderTraversal() {
    List<Integer> result = new ArrayList<>();
    preorderRecursive(this.root, result);
    return result;
  }
  private List<Integer> preorderRecursive(Node node, List<Integer> result) {
    if(node != null) {
      result.add(node.getData());
      preorderRecursive(node.getLeft(), result);
      preorderRecursive(node.getRight(), result);
    }
    return result;
  }

  public List<Integer> inorderTraversal() {
    List<Integer> result = new ArrayList<>();
    inorderRecursive(this.root, result);
    return result;
  }
  private List<Integer> inorderRecursive(Node node, List<Integer> result) {
    if(node != null) {
      inorderRecursive(node.getLeft(), result);
      result.add(node.getData());
      inorderRecursive(node.getRight(), result);
    }
    return result;
  }

  public List<Integer> postorderTraversal() {
    List<Integer> result = new ArrayList<>();
    postorderRecursive(this.root, result);
    return result;
  }
  private List<Integer> postorderRecursive(Node node, List<Integer> result) {
    if(node != null) {
      postorderRecursive(node.getLeft(), result);
      postorderRecursive(node.getRight(), result);
      result.add(node.getData());
    }
    return result;
  }
}
