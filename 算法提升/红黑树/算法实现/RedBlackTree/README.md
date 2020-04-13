- # 红黑树

  ## 一、简介

  红黑树（Red Black Tree）是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组。

  红黑树是一种特化的AVL树（平衡二叉树），都是在进行插入和删除操作时通过特定操作保持二叉树的平衡，从而获得较高的查找性能。

  它虽然是复杂的，但它的最坏情况运行时间也是非常良好的，并且在实践中是高效的： 它可以在O(log n)时间内做查找，插入和删除，这里的n 是树中元素的数目。 

  ## 二、红黑树的特征

  - 节点都有颜色（ 在红-黑树中，每个节点的颜色或者是黑色或者是红色的。当然也可以是任意别的两种颜色，这里的颜色用于标记，我们可以在节点类Node中增加一个boolean型变量isRed，以此来表示颜色的信息 ）
  - 在插入和删除的过程中，要遵循保持这些颜色的不同排列规则，在插入或者删除一个节点时，必须要遵守的规则称为红-黑规则 ：
    - 每个节点不是红色就是黑色
    - 根节点总是黑色
    - 如果节点是红色，那他的子节点必须是黑色的（反之不一定， 也就是从每个叶子到根的所有路径上不能有两个连续的红色节点）
    - 从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色亮度）

  ## 三、红黑树的自我调整：

  - 颜色调整

  - 结构调整：

    ①左旋（Rotate Left）：如下图，左旋的过程是将右子树饶x逆时针旋转，使得x的右子树成为x的父亲，同时修改相关节点的引用。旋转之后，红黑树的基本属性仍然满足。

  ![左旋示意图](https://img-blog.csdnimg.cn/20200409165542129.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI4Njk1NTkz,size_16,color_FFFFFF,t_70#pic_center)

    ②右旋（Rotate Left）：如下图，右旋的过程是将x的左子树绕x顺时针旋转，使得x的左子树成为x的父亲，同时修改相关节点的引用。旋转之后，二叉查找树的属性仍然满足。 

  ![右旋示意图](https://img-blog.csdnimg.cn/20200409165559918.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI4Njk1NTkz,size_16,color_FFFFFF,t_70#pic_center)

  ## 四、红黑树代码实现：

  - github地址： [红黑树算法实现](https://github.com/LeeckyNTG/RedBlackTree)