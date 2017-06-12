# 集合与数组

## 都是对象的存储：

1. 数组（基本数据类型  & 引用数据类型）  
2. 集合（引用数据类型）

引用数据类型数组的元素是对象引用，初值为空，必须实例化；而基本类型数组元素都有默认初值。

## 数组存储数据的弊端：

1. 一旦创建，其长度不可变。
2. 真实的数组存放的对象的个数是不可知。


## 为什么集合不能存储基本数据类型？

集合中存放的可都是对象的引用，实际内容都在堆上面或者方法区里面，但是基本数据类型是在栈上分配空间的。随时就被收回的。但是通过自动包装类就可以把基本类型转为对象类型，存放引用就解决了这个问题。


# 总体框架
![](http://oov0wb0gl.bkt.clouddn.com/2017-06-06-14954338994489.jpg)
看上面的框架图，先抓住它的主干，即Collection和Map。

## Collection是一个接口，是高度抽象出来的集合，它包含了集合的基本操作和属性。

Collection包含了List和Set两大分支。

1. List是一个有序的队列，每一个元素都有它的索引。第一个元素的索引值是0。
    List的实现类有LinkedList, ArrayList, Vector, Stack。

2. Set是一个不允许有重复元素的集合。
    Set的实现类有HastSet和TreeSet。HashSet依赖于HashMap，它实际上是通过HashMap实现的；TreeSet依赖于TreeMap，它实际上是通过TreeMap实现的。

## Map是一个映射接口，即key-value键值对。Map中的每一个元素包含“一个key”和“key对应的value”。

AbstractMap是个抽象类，它实现了Map接口中的大部分API。而HashMap，TreeMap，WeakHashMap都是继承于AbstractMap。

Hashtable虽然继承于Dictionary，但它实现了Map接口。

接下来，再看Iterator。它是遍历集合的工具，即我们通常通过Iterator迭代器来遍历集合。我们说Collection依赖于Iterator，是因为Collection的实现类都要实现iterator()函数，返回一个Iterator对象。

ListIterator是专门为遍历List而存在的。

再看Enumeration，它是JDK 1.0引入的抽象类。作用和Iterator一样，也是遍历集合；但是Enumeration的功能要比Iterator少。在上面的框图中，Enumeration只能在Hashtable, Vector, Stack中使用。

最后，看Arrays和Collections。它们是操作数组、集合的两个工具类。

有了上面的整体框架之后，我们接下来对每个类分别进行分析。

