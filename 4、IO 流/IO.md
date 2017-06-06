# IO
## 总览
字节流：一次读入或读出是8位二进制。
字符流：一次读入或读出是16位二进制。

字节流和字符流的原理是相同的，只不过处理的单位不同而已。后缀是Stream是字节流，而后缀是Reader，Writer是字符流。
 
节点流：直接与数据源相连，读入或读出。
![](http://oov0wb0gl.bkt.clouddn.com/2017-06-06-14948633565434.jpg)

直接使用节点流，读写不方便，为了更快的读写文件，才有了处理流。
![](http://oov0wb0gl.bkt.clouddn.com/2017-06-06-14948633652124.jpg)

处理流：与节点流一块使用，在节点流的基础上，再套接一层，套接在节点流上的就是处理流。


![](http://oov0wb0gl.bkt.clouddn.com/2017-06-06-14948631079354.jpg)


## 1、java.io包下的File类
 File类：java程序中的此类的一个对象，就对应着硬盘中的一个文件或网络中的一个资源。
 
 ```
 File file1 = new File("d:\\io\\helloworld.txt");
 File file2 = new File("d:\\io\\io1"); 
 //或者  
 File file3 = new File("d:/io/io1"); 
 ```
File的静态属性`String separator`存储了当前系统的路径分隔符。
在UNIX中，此字段为‘/’，在Windows中，为‘\\’

1. File既可以表示一个文件（.doc  .xls   .mp3  .avi   .jpg  .dat），也可以表示一个文件目录！
2. File类的对象是与平台无关的。
3. File类针对于文件或文件目录，只能进行新建、删除、重命名、上层目录等等的操作。如果涉及到访问文件的内容，File是无能为力的，只能使用IO流下 提供的相应的输入输出流来实现。
4. 常把File类的对象作为形参传递给相应的输入输出流的构造器中！

### File类的常见构造方法：
```
public File(String pathname)
         以pathname为路径创建File对象，可以是绝对路径或者相对路径，如果pathname是相对路径，则默认的当前路径在系统属性user.dir中存储。
         
public File(String parent,String child)
          以parent为父路径，child为子路径创建File对象。
```
![](http://oov0wb0gl.bkt.clouddn.com/2017-06-06-14948455642362.png)


## 2、IO 流的结构

| 分类 | 字节输入流 | 字节输出流 | 字符输入流 | 字符输出流 |
| --- | --- | --- | --- | --- |
| 抽象基类 |  InputStream | OutputStream | Reader | Writer |
| 访问文件 | FileInputStream | FileOutputStream | FileReader | FileWriter |
| 访问数组 | ByteArrayInputStream | ByteArrayOutputStream | CharArrayReader | CharArrayWriter |
| 访问管道 | PipedInputStream | PipedOutputStream | PipedReader | PipedWriter |
| 访问字符串 |  |  | StringReader | StringWriter |
| 缓冲流 | BufferedInputStream | BufferedOutputStream | BufferedReader | BufferedWriter |
| 转换流 |  |  | InputStreamReader | OutputStreamWriter |
| 对象流 | ObjectInputStream | ObjectOutputStream |  |  |
|  | FilterInputStream | FilterOutputStream | FilterReader | FilterWriter |
| 打印流 |  | PrintStream |  | PrintWriter |
| 推回输入流 | PushbackInputStream |  | PushbackReader |  |
| 特殊流 | DataInputStream | DataOutputStream |  |  |



## 3、IO流的划分
  1. 按照流的流向的不同：输入流   输出流  (站位于程序的角度)
  2. 按照流中的数据单位的不同：字节流   字符流  （纯文本文件使用字符流 ，除此之外使用字节流）
  3. 按照流的角色的不同：节点流   处理流（流直接作用于文件上是节点流（4个），除此之外都是处理流）

## 4、重点掌握

|抽象基类| 节点流(文件流)|缓冲流（处理流的一种,可以提升文件操作的效率）|
| --- | --- | --- |
|InputStream |  FileInputStream （int read(byte[] b)）|    BufferedInputStream  (int read(byte[] b))|
|OutputStream|  FileOutputStream (void write(b,0,len)) |   BufferedOutputStream  (flush())  (void write(b,0,len))|
|Reader |   FileReader (int read(char[] c))               |  BufferedReader  (readLine())  (int read(char[] c))或String readLine()|
|Writer  |  FileWriter (void write(c,0,len))     |     BufferedWriter  (flush()) (void write(c,0,len)或void write(String str))|

 注：
 
1. 从硬盘中读入一个文件，要求此文件一定得存在。若不存在，报`FileNotFoundException`的异常；
2. 从程序中输出一个文件到硬盘，此文件可以不存在。若不存在，就创建一个实现输出。若存在，则将已存在的文件覆盖；
3. 真正开发时，就使用缓冲流来代替节点流；
4. 主要最后要关闭相应的流。先关闭输出流，再关闭输入流。将此操作放入`finally`。
 

## 5、其它的流
### 1、转换流：实现字节流与字符流之间的转换

```
InputStreamReader:  输入时，实现字节流到字符流的转换，提高操作的效率（前提是，数据是文本文件）  
               ===>解码：字节数组--->字符串
               
OutputStreamWriter：输出时，实现字符流到字节流的转换。
               ===>编码：  字符串---->字节数组
```

###  2、标准的输入输出流
```
System.in:  The "standard" input stream:  从键盘输入数据   
System.out: The "standard" output stream： 从显示器输出数据
```
### 3、打印流 (都是输出流)  PrintStream(处理字节)  PrintWriter(处理字符)


### 4、数据流（处理基本数据类型、String类、字节数组）
     
     DataInputStream  DataOutputStream
### 5、对象流(用来处理对象的)
对象的序列化机制：允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。当其它程序获取了这种二进制流，就可以恢复成原来的Java对象

```
// ObjectInputStream（Object readObject();）   
// ObjectOutputStream  (void writeObject(Object obj))
     如何创建流的对象：
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("person.txt")));

ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person.txt")));
```            
实现序列化机制的对象对应的类的要求：

1. 要求类要实现Serializable接口
2. 同样要求类的所有属性也必须实现Serializable接口
3.  要求给类提供一个序列版本号：private static final long serialVersionUID;
4. 属性声明为static 或transient的，不可以实现序列化


### 6、随机存取文件流:RandomAccessFile

1. 既可以充当一个输入流，又可以充当一个输出流：`public RandomAccessFile(File file, String mode)`
2. 支持从文件的开头读取、写入。若输出的文件不存在，直接创建。若存在，则是对原有文件内容的覆盖。
3. 支持任意位置的“插入”。

```
// 构造器
public RandomAccessFile(File file, String mode) 
public RandomAccessFile(String name, String mode)
 
// 创建 RandomAccessFile 类实例需要指定一个 mode 参数，该参数指定 RandomAccessFile 的访问模式：
r: 以只读方式打开
rw：打开以便读取和写入
rwd:打开以便读取和写入；同步文件内容的更新
rws:打开以便读取和写入；同步文件内容和元数据的更新
```


