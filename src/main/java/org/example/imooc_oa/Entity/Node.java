package org.example.imooc_oa.Entity;

public class Node {
    private long nodeId;
    private int nodeType;
    private String nodeName;
    private String url;
    private int nodeCode;
    private long parentNodeId;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(int nodeCode) {
        this.nodeCode = nodeCode;
    }

    public long getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(int parentNodeId) {
        this.parentNodeId = parentNodeId;
    }
}
