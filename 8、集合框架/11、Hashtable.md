# Hashtable

## 第1部分 Hashtable介绍

Hashtable 简介

和HashMap一样，Hashtable 也是一个散列表，它存储的内容是键值对(key-value)映射。
Hashtable 继承于Dictionary，实现了Map、Cloneable、java.io.Serializable接口。
Hashtable 的函数都是同步的，这意味着它是线程安全的。它的key、value都不可以为null。此外，Hashtable中的映射不是有序的。

Hashtable 的实例有两个参数影响其性能：初始容量 和 加载因子。容量 是哈希表中桶 的数量，初始容量 就是哈希表创建时的容量。注意，哈希表的状态为 open：在发生“哈希冲突”的情况下，单个桶会存储多个条目，这些条目必须按顺序搜索。加载因子 是对哈希表在其容量自动增加之前可以达到多满的一个尺度。初始容量和加载因子这两个参数只是对该实现的提示。关于何时以及是否调用 rehash 方法的具体细节则依赖于该实现。
通常，默认加载因子是 0.75, 这是在时间和空间成本上寻求一种折衷。加载因子过高虽然减少了空间开销，但同时也增加了查找某个条目的时间（在大多数 Hashtable 操作中，包括 get 和 put 操作，都反映了这一点）。

 

Hashtable的构造函数

复制代码
// 默认构造函数。
public Hashtable() 

// 指定“容量大小”的构造函数
public Hashtable(int initialCapacity) 

// 指定“容量大小”和“加载因子”的构造函数
public Hashtable(int initialCapacity, float loadFactor) 

// 包含“子Map”的构造函数
public Hashtable(Map<? extends K, ? extends V> t)
复制代码
 

Hashtable的API

复制代码
synchronized void                clear()
synchronized Object              clone()
             boolean             contains(Object value)
synchronized boolean             containsKey(Object key)
synchronized boolean             containsValue(Object value)
synchronized Enumeration<V>      elements()
synchronized Set<Entry<K, V>>    entrySet()
synchronized boolean             equals(Object object)
synchronized V                   get(Object key)
synchronized int                 hashCode()
synchronized boolean             isEmpty()
synchronized Set<K>              keySet()
synchronized Enumeration<K>      keys()
synchronized V                   put(K key, V value)
synchronized void                putAll(Map<? extends K, ? extends V> map)
synchronized V                   remove(Object key)
synchronized int                 size()
synchronized String              toString()
synchronized Collection<V>       values()
复制代码
 

第2部分 Hashtable数据结构

Hashtable的继承关系

java.lang.Object
   ↳     java.util.Dictionary<K, V>
         ↳     java.util.Hashtable<K, V>

public class Hashtable<K,V> extends Dictionary<K,V>
    implements Map<K,V>, Cloneable, java.io.Serializable { }
 

Hashtable与Map关系如下图：
![](media/14967324038221.jpg)



从图中可以看出： 
(01) Hashtable继承于Dictionary类，实现了Map接口。Map是"key-value键值对"接口，Dictionary是声明了操作"键值对"函数接口的抽象类。 
(02) Hashtable是通过"拉链法"实现的哈希表。它包括几个重要的成员变量：table, count, threshold, loadFactor, modCount。
　　table是一个Entry[]数组类型，而Entry实际上就是一个单向链表。哈希表的"key-value键值对"都是存储在Entry数组中的。 
　　count是Hashtable的大小，它是Hashtable保存的键值对的数量。 
　　threshold是Hashtable的阈值，用于判断是否需要调整Hashtable的容量。threshold的值="容量*加载因子"。
　　loadFactor就是加载因子。 
　　modCount是用来实现fail-fast机制的

 

第3部分 Hashtable源码解析(基于JDK1.6.0_45)

为了更了解Hashtable的原理，下面对Hashtable源码代码作出分析。
在阅读源码时，建议参考后面的说明来建立对Hashtable的整体认识，这样更容易理解Hashtable。

 View Code
说明: 在详细介绍Hashtable的代码之前，我们需要了解：和Hashmap一样，Hashtable也是一个散列表，它也是通过“拉链法”解决哈希冲突的。


第3.1部分 Hashtable的“拉链法”相关内容

3.1.1 Hashtable数据存储数组

private transient Entry[] table;
Hashtable中的key-value都是存储在table数组中的。

3.1.2 数据节点Entry的数据结构

 View Code
从中，我们可以看出 Entry 实际上就是一个单向链表。这也是为什么我们说Hashtable是通过拉链法解决哈希冲突的。
Entry 实现了Map.Entry 接口，即实现getKey(), getValue(), setValue(V value), equals(Object o), hashCode()这些函数。这些都是基本的读取/修改key、value值的函数。

 

第3.2部分 Hashtable的构造函数

Hashtable共包括4个构造函数

 View Code
 

第3.3部分 Hashtable的主要对外接口

3.3.1 clear()

clear() 的作用是清空Hashtable。它是将Hashtable的table数组的值全部设为null

 View Code
3.3.2 contains() 和 containsValue()

contains() 和 containsValue() 的作用都是判断Hashtable是否包含“值(value)”

 View Code
3.3.3 containsKey()

containsKey() 的作用是判断Hashtable是否包含key

 View Code
3.3.4 elements()

elements() 的作用是返回“所有value”的枚举对象

 View Code
从中，我们可以看出：
(01) 若Hashtable的实际大小为0,则返回“空枚举类”对象emptyEnumerator；
(02) 否则，返回正常的Enumerator的对象。(Enumerator实现了迭代器和枚举两个接口)

我们先看看emptyEnumerator对象是如何实现的

 View Code
我们在来看看Enumeration类

Enumerator的作用是提供了“通过elements()遍历Hashtable的接口” 和 “通过entrySet()遍历Hashtable的接口”。因为，它同时实现了 “Enumerator接口”和“Iterator接口”。

 View Code
entrySet(), keySet(), keys(), values()的实现方法和elements()差不多，而且源码中已经明确的给出了注释。这里就不再做过多说明了。

3.3.5 get()

get() 的作用就是获取key对应的value，没有的话返回null

 View Code
3.3.6 put()

put() 的作用是对外提供接口，让Hashtable对象可以通过put()将“key-value”添加到Hashtable中。

 View Code
3.3.7 putAll()

putAll() 的作用是将“Map(t)”的中全部元素逐一添加到Hashtable中

 View Code
3.3.8 remove()

remove() 的作用就是删除Hashtable中键为key的元素

 View Code
 

第3.4部分 Hashtable实现的Cloneable接口

Hashtable实现了Cloneable接口，即实现了clone()方法。
clone()方法的作用很简单，就是克隆一个Hashtable对象并返回。

 View Code
 

第3.5部分 Hashtable实现的Serializable接口

Hashtable实现java.io.Serializable，分别实现了串行读取、写入功能。

串行写入函数就是将Hashtable的“总的容量，实际容量，所有的Entry”都写入到输出流中
串行读取函数：根据写入方式读出将Hashtable的“总的容量，实际容量，所有的Entry”依次读出

 View Code
 

第4部分 Hashtable遍历方式

4.1 遍历Hashtable的键值对

第一步：根据entrySet()获取Hashtable的“键值对”的Set集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

复制代码
// 假设table是Hashtable对象
// table中的key是String类型，value是Integer类型
Integer integ = null;
Iterator iter = table.entrySet().iterator();
while(iter.hasNext()) {
    Map.Entry entry = (Map.Entry)iter.next();
    // 获取key
    key = (String)entry.getKey();
        // 获取value
    integ = (Integer)entry.getValue();
}
复制代码
 

4.2 通过Iterator遍历Hashtable的键

第一步：根据keySet()获取Hashtable的“键”的Set集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

复制代码
// 假设table是Hashtable对象
// table中的key是String类型，value是Integer类型
String key = null;
Integer integ = null;
Iterator iter = table.keySet().iterator();
while (iter.hasNext()) {
        // 获取key
    key = (String)iter.next();
        // 根据key，获取value
    integ = (Integer)table.get(key);
}
复制代码
 

4.3 通过Iterator遍历Hashtable的值

第一步：根据value()获取Hashtable的“值”的集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

复制代码
// 假设table是Hashtable对象
// table中的key是String类型，value是Integer类型
Integer value = null;
Collection c = table.values();
Iterator iter= c.iterator();
while (iter.hasNext()) {
    value = (Integer)iter.next();
}
复制代码
 

4.4 通过Enumeration遍历Hashtable的键

第一步：根据keys()获取Hashtable的集合。
第二步：通过Enumeration遍历“第一步”得到的集合。

Enumeration enu = table.keys();
while(enu.hasMoreElements()) {
    System.out.println(enu.nextElement());
}   
 

4.5 通过Enumeration遍历Hashtable的值

第一步：根据elements()获取Hashtable的集合。
第二步：通过Enumeration遍历“第一步”得到的集合。

Enumeration enu = table.elements();
while(enu.hasMoreElements()) {
    System.out.println(enu.nextElement());
}
遍历测试程序如下：

 View Code
 

第5部分 Hashtable示例

下面通过一个实例来学习如何使用Hashtable。

 View Code
(某一次)运行结果：

复制代码
table:{two=5, one=0, three=6}
next : two - 5
next : one - 0
next : three - 6
size:3
contains key two : true
contains key five : false
contains value 0 : true
table:{two=5, one=0}
table is empty

