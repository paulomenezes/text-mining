package org.cogroo.exercise;

import java.util.ArrayList;

/**
 * Created by paulomenezes on 11/05/17.
 */
public class Node {
    private String text;
    private Node parent;
    private ArrayList<Node> children;

    public Node(String text, Node parent, ArrayList<Node> children) {
        this.text = text;
        this.parent = parent;

        if (children != null)
            this.children = children;
        else
            this.children = new ArrayList<>();
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void print() {
        print("", true);
    }

    // Source: http://stackoverflow.com/a/8948691
    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + text);

        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }

        if (children.size() > 0) {
            children.get(children.size() - 1).print(prefix + (isTail ?"    " : "│   "), true);
        }
    }


    @Override
    public String toString() {
        return text;
    }
}
