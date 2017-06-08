# Set

前面，我们已经系统的对List和Map进行了学习。接下来，我们开始可以学习Set。相信经过Map的了解之后，学习Set会容易很多。毕竟，Set的实现类都是基于Map来实现的(HashSet是通过HashMap实现的，TreeSet是通过TreeMap实现的)。

首先，我们看看Set架构。

![](http://oov0wb0gl.bkt.clouddn.com/2017-06-08-14969275258628.jpg)

1. Set 是继承于Collection的接口。它是一个不允许有重复元素的集合。
2. AbstractSet 是一个抽象类，它继承于AbstractCollection，AbstractCollection实现了Set中的绝大部分函数，为Set的实现类提供了便利。
3. HastSet 和 TreeSet 是Set的两个实现类。
    1. HashSet依赖于HashMap，它实际上是通过HashMap实现的。HashSet中的元素是无序的。
    2. TreeSet依赖于TreeMap，它实际上是通过TreeMap实现的。TreeSet中的元素是有序的。

