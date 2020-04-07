public class RBNode<T extends Comparable<T>>{
    boolean color;//颜色
    T key;//关键值
    RBNode<T> left;
    RBNode<T> right;
    RBNode<T> parent;
}