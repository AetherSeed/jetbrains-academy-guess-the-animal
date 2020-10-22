package animals.domain;

import java.util.Objects;

public class NodeData {
    protected String data;

    public NodeData() {
    }

    public NodeData(final String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeData nodeData = (NodeData) o;
        return data.equals(nodeData.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
