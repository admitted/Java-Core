# Java 关键字与标识符
## 关键字

**关键字**：被Java语言赋予了特殊含义，用做专门用途的字符串（单词）

```
关键字列表
abstract    boolean     break       byte        case 
catch       char        class       continue    default 
do          double      else        extends     enum    
false       final       finally     float       for
if          implements  import      instanceof  int
interface   long        native      new         null
package     private     protected   public      return
short       static      super       switch      synchronized
this        throw       throws      transient   true
try         void        volatile    while 
```

Java 中 `true false`不是关键字，而是 boolean 类型的字面量。但也不能当作变量用。
所有的关键字都是小写，`friendly sizeof` 不是 java 的关键字 。


## 保留字

**保留字**：指在高级语言中已经定义过的字，使用者不能再将这些字作为变量名或过程名使用。
保留字：`const goto`  这两个已经削去意义，但同样不能用作变量名。


## 标识符

**标识符**：用来给一个类、变量或方法命名的符号（凡是自己可以起名字的地方都叫标识符）

**标识符的命名规则**：（一定要遵守，不遵守就会报编译的错误）

* 由26个英文字母大小写，0-9 , 或者下划线"\_"和"$"开头，数字不可以开头。
* Java中严格区分大小写，
* 不可以使用关键字和保留字，但能包含关键字和保留字。
* Java中长度无限制。
* 标识符不能包含空格。

**Java中的名称命名规范**：（不遵守，也不会出现编译的错误）

* 包名：                    多单词组成时所有字母都小写：`xxxyyyzzz`
* 类名、接口名：       多单词组成时，所有单词的首字母大写：`XxxYyyZzz`
* 变量名、方法名：   多单词组成时，第一个单词首字母小写，第二个单词开始每个单词首字母大写：`xxxYyyZzz`
* 常量名：                 所有字母都大写。多单词时下划线连接：`XXX_YYY_ZZZ`
* 建议使用JavaBeans规则命名，并根据方法的目的，以 `set`、`get`、`is`、`add` 或 `remove` 开头。




