package com.clover;


/**
 * Description:     节点类
 * File Name:       RBNode.java
 * Crete By:        2020/4/7 10:27
 * Author:          Clover
 * Modify Date:
 * Modifier Author:
 */
public class RBNode<T extends Comparable<T>, D> {

    /**
     * 颜色
     */
    boolean color;
    /**
     * 关键值
     */
    T key;
    /**
     * 具体数据
     */
    D data;
    /**
     * 左节点
     */
    RBNode left;
    /**
     * 右节点
     */
    RBNode right;
    /**
     * 父节点
     */
    RBNode<T, D> parent;

    public RBNode(boolean color, T key, D data, RBNode left, RBNode right, RBNode<T, D> parent) {
        this.color = color;
        this.key = key;
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "RBNode{" +
                "color=" + color +
                ", key=" + key +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
}