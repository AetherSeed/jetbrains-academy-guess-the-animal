package animals.repository;

import animals.domain.Question;

public class Node {
    private Question data;
    private Node yes;
    private Node no;

    Node(final Question data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return no == null && yes == null;
    }

    public Question getData() {
        return data;
    }

    public void setData(final Question data) {
        this.data = data;
    }

    public Node getYes() {
        return yes;
    }

    public void setYes(Node yes) {
        this.yes = yes;
    }

    public Node getNo() {
        return no;
    }

    public void setNo(Node no) {
        this.no = no;
    }
}