# Java 变量

## 变量的概述
通常，根据内存地址可以找到这块内存空间的位置，也就找到了存储的数据。但是内存地址非常不好记，因此，我们给这块空间起一个别名，通过使用别名找到对应空间存储的数据。变量是一个数据存储空间的表示。通过变量名可以简单快速地找到它存储的数据。变量是存储数据的一个基本单元，不同的变量相互独立。

按照用法 可分为 **基本数据类型** 和 **引用数据类型**。

*基本数据类型不存在“引用”的概念，基本数据类型都是直接存储在内存中的内存栈上的，数据本身的值就是存储在栈空间里面，而Java语言里面八种数据类型是这种存储模型；*

*引用类型继承于Object类（也是引用类型）都是按照Java里面存储对象的内存模型来进行数据存储的，使用Java内存堆和内存栈来进行这种类型的数据存储，简单地讲，“引用”是存储在有序的内存栈上的，而对象本身的值存储在内存堆上的；*

区别:基本数据类型和引用类型的区别主要在于基本数据类型是分配在栈上的，而引用类型是分配在堆上的（需要理解java中的栈、堆概念）。

-
## 基本数据类型（8种）

| 类型 | 占用空间 | 表值范围 | 说明 |默认值|
| --- | --- | --- | --- |---|
| bolean | 1字节=8bit | false；true | 不能取null |false|
| char | 2字节 | 0 ~ 2^16 - 1| 2^16 = 65536 | 为空|
| byte | 1字节 |  - 2^7~ 2^7 - 1 | 两byte相加，变 int |0|
| short | 2字节 | - 2^15 ~ 2^15 - 1 | 2^15 = 32768 |0|
| int | 4字节 |   - 2^31 ~ 2^31 - 1 | 约20亿,10位有效数字 |0|
| long | 8字节 | - 2^63 ~ 2^63 - 1 | 约900亿亿,20位有效数字 |0|
| float | 4字节 | 9位有效数字|小数算，正负号不算|0.0|
| double| 8字节| 18位有效数字 | 同 float |0.0|

```
注：
1、java中所有的数据类所占据的字节数量与平台无关，java也没有任何无符号类型

2、float 和 double 的小数部分不可能精确，只能近似。比较小数时，用 
   double i=0.01; 
   if ( i - 0.01 < 1E-6) ...
   // 不能直接 if (i==0.01)...
   
3、float 与 double 的区别在于float类型有效小数点只有6~7位 ， 
   double 有15~16位。
```

补充：按照在类中存在的位置的不同：成员变量 vs 局部变量

-
## 引用数据类型（类、接口、数组）
       
Java 中除去基本数据类型以外的数据类型都是引用数据类型，包括我们自己创建的类。

### Java中什么叫做引用数据类型？

引用：**就是按内存地址查询**
比如：`Object o = new Object();` 这个其实是在栈内存里分配一块内存空间为o，在堆内存里 new 了一个Object 类型的空间，在运行时是栈内存里的 o 指向堆内存里的那一块存储空间。

引用类型变量是以间接方式去获取数据。引用类型变量都属于对象类型，如：数组、类、字符串等都属于引用类型变量。所以，引用类型变量里面存放的是数据的地址。说白了基本数据类型变量就像是直接放在柜子里的东西，而引用数据类型变量就是这个柜子对应编码的钥匙。钥匙号和柜子对应。

## 进制（了解）
| 进制|缩写|英文|说明|
| --- | --- | --- | --- |
|十进制 | D| Decimal | 0-9 ；满10进1|
|二进制 | B |  binaries|  0,1 ；满2进1，以0b或0B开头|
|八进制 | O | octal  | 0-7 ，满8进1， 以数字0开头 |
|十六进制| H | Hexagon  | 0-9及A-F(a-f)，满16进1,以0x或0X开头。|


二进制：计算机底层都是用二进制来存储、运算。
>二进制 与十进制之间的转换。
二进制在底层存储：正数、负数都是以补码的形式存储的。
四种进制间的转换 ？？？

## 变量的数据类型转换

### 自动类型转换：
容量小的类型自动转换为容量大的类型。
    
```
short s = 12;
int i = s + 2;
```
**注意：byte  short char之间做运算，结果为int型！**

### 强制类型转换：

是自动类型转换的逆过程，使用 "( )" 实现强转，会丢失精度或者出现异常。

```
int a = 32767;
int b = 32768;
short s1 = (short) a;  
short s2 = (short) b;  
System.out.println(s1); // 32767
System.out.println(s2); // -32768
```

### 实例测试类

```
class TestVeriable {
       public static void main(String[] args) {
             // 1.变量得先定义后使用
            int myInt1 = 10;
            double d = 12.3;
            System.out.println(myInt1);
            System.out.println(myInt1 + d);
            // i1 超出了其作用的范围，不可使用。
            // System.out.println(i1);

            // 2.整型：byte(-128~+127)  short  int（默认类型） long
            byte b1 = 12;           //byte b2 = 128;
            short s1 = 128;
            int i1 = 12;
            // 定义long型变量，值的末尾加“L”或“l”
            long l1 = 2134123351345325L;
            System.out.println(l1);

            // 3.浮点型（带小数点的数值）：double 是默认类型  float  
            double d1 = 12.3;
            //声明float类型的浮点型数据，末尾要加“F”或者“f”
            float f1 = 12.3F;
            System.out.println(f1);

            // 4.字符型（两个字节）：
            // char 只能表示一个字符(英文、中文、标点符号、日文、。。。)
            char c1 = 'a';          //char c2 = ' ab'; 
            String str = "ab";
            char c3 = '中';
            String str1 = "中国";
            // 可以表示转义字符
            char c4 = '\t';
            char c5 = '\n' ;
            System.out.println("abc" + c5 + "def");
            // 了解
            char c6 = '\u1234' ;        //unicode值 == ？
            System.out.println(c6);    //char 类型是可以运算的，因为它都对应有Unicode值

            // 5.布尔类型：boolean  只能够取值为true 或 false 。不能取值null
            boolean bool1 = true;
            if(bool1)
                System.out.println("哈哈！周末不上班 );
    }
} 
```
### 变量间的运算
两个数相运算时，默认是 int 类型
如果有更高级的，就按高级的那个类型
   if(其中一个是double型)double型；
   else if(其中一个是float型)float型；
   else if(其中一个是long型)long型；
   else int 型。

```
/*
变量之间的运算：(不考虑boolean ：char byte short int long float double)
1.自动类型转换
2.强制类型转换
*/
class TestVeriable{
       public static void main(String[] args){
            // 1.自动类型转换:当容量小的数据类型与容量大的数据类型做运算时，
            // 容量小的会自动转换为容量大的数据类型:
            // char,byte,short ===> int ===>long ===>float===double
            int i1 = 12;
            short s1 = 2;
            int i2 = i1 + s1;
            float f1 = 12.3F;
            float f2 = f1 + i2;
            //float d1 = f2 + 12.3;

            long l = 12L;
            float f3 = l;
            System.out.println(i2);
            System.out.println(f2);

            char c1 = 'a' ;//97
            c1 = 'A';//65
            int i3 = c1 + 1;
            System.out.println(i3);

            //需要注意的：当char\byte\short之间做运算时，默认的结果为 int类型
            short ss1 = 12;
            byte bb1 = 1;
            char cc1 = 'a' ;
            //short ss2 = ss1 + bb1;
            int ii1 = ss1 + bb1;
            //char cc2 = cc1 + bb1;
            int ii2 = cc1 + bb1;
            short ss2 = 11;
            //short ss3 = ss1 + ss2;
            
            //2.强制类型转换：容量大转换为容量小的.要使用强制类型转换符：()
            //强制类型转换的问题：导致精度的损失
            long l1 = 12345L;
            int m1 = (int)l1;
            System.out.println(m1);

            byte by1 = (byte)m1;
            System.out.println(by1);

            //平时常用的字符串,也是一种数据类型：String
            String nation = "我是一个中国人" ;
            System.out.println(nation);
            //字符串与基本数据类型之间的运算:只能是连接运算：+。得到的结果仍为一个字符串
            String str = "abc";
            String str1 = str + m1; //abc12345
            System.out.println(str1);

            //题目：
            String st1 = "hello";
            int myInt1 = 12;
            char ch1 = 'a' ;//97
            System.out.println(str1 + myInt1 + ch1);  // hello12a
            System.out.println(myInt1 + ch1 + str1);  // 109hello
            System.out.println(ch1 + str1 + myInt1);  // ahello12

            String st2 = "12";
            st2 = 12 + ""; 
      }
}

```


