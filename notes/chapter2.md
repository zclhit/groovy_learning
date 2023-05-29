# Groovy相关基础信息

## 代码结构

### 注释

```#!```注释，只允许出现在groovy脚本的第一行，通过这种注释可以方便Unix shell进行定位启动并运行
```//```单行注释
```/* xxxxx */```多行注释
```/** xxxxx */```doc风格的注释，可以使用groovydoc生成注释文档


### 自动导入类

groovy会自动导入：

```groovy
import groovy.lang.*
import groovy.util.*
import java.lang.*
import java.util.*
import java.net.*
import java.io.*
import java.math.BigInteger
import java.math.BigDecimal
```

而java只会自动导入：

```java
import java.lang.*;
```

## 断言

```groovy
assert (true)
assert 1==1
def x=1
assert x==1
def y=1; assert y==1
```

1.断言可以显示当前程序的运行状态
2.断言可以替换注释语句，用来表示结果符合自己的期望

## 开始写程序

### 定义类

```groovy
class Book {
    private String title
    Book (String theTitle) {
        title = theTitle
    }

    String getTitle() {
        return title
    }
}
```

和Java类很像

### 使用类

```groovy
Book gina = new Book('Groovy in Action')
assert gina.title == 'Groovy in Action'
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'

String getTitleBackwards(book) {
    title = book.getTitle()
    return title.reverse()
}
```

### 定义Beans

groovyBean可以：
1. 自动生成访问方法
2. JavaBeans简化访问方式
3. 事情处理器简化使用方式

```groovy
class BookBean {
    String title //属性
}

def groovyBook = new BookBean()

//通过显示的方法调用来使用属性
groovyBook.setTitle('Groovy conquers the world')
assert groovyBook.getTitle() == 'Groovy conquers the world'

//通过groovy的快捷方式来使用属性
groovyBook.title = 'Groovy in Action'
assert groovyBook.title == 'Groovy in Action'
```

### 文本处理

#### GStrings变量替换

```groovy
def nick = 'Gina'
def book = 'Groovy in Action'
assert "$nick is $book" == 'Gina is Groovy in Action'
```

#### 数字对象

Groovy中的数字是类对象，而不是专有的类型

```groovy
def x = 1
def y = 2
assert x + y == 3
assert x.plus(y) == 3
assert x instanceof Integer
```

#### groovy中的集合

1.Lists

```groovy
//罗马数字列表
def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']
//访问列表
assert roman[4] == 'IV'
//扩张列表
roman[8] = 'VIII'
assert roman.size() == 9
```

2.映射maps

map通过k-v形式取值，lists根据位置取值。

```groovy
def http = [
100 : 'CONTINUE', 
200 : 'OK',
400 : 'BAD REQUEST' ]
assert http[200] == 'OK'
http[500] = 'INTERNAL SERVER ERROR' 
assert http.size() == 4
```

3.范围ranges

设置开始的点和结束点，生成一个range，并且可以随时进行倒转的操作

```groovy
def x = 1..10
assert x.contains(5)
assert !x.contains(15)
assert x.size() == 10
assert x.from == 1
assert x.to == 10
assert x.reverse() == 10..1
```

4.代码块：闭包

闭包是groovy中非常常见但是在java中类似于函数式编程的东西。

一个闭包就是一个花括号围起来的语句块，闭包有一个可选的参数列表，通过```->```进行参数传递

例如```[1,2,3].each{ entry -> println entry}```就行一个典型的闭包形式

5.结构控制语句

```groovy
//在一行的if语句
if (false) assert false

//null表示 false
if (null) {
    assert false
} else {
    assert true
}

//典型的while
def i = 0
while (i < 10) {
    i++
}
assert i == 10

//迭代一个range
def clinks = 0
for (remainingGuests in 0..9) {
    clinks += remainingGuests
}
assert clinks == (10 * 9) / 2

//迭代一个列表
def list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
for (j in list) {
    assert j == list[j]
}

//以闭包为参数的each方法
list.each() { item ->
    assert item == list[item]
}

//典型的switch
switch (3) {
    case 1: assert false; break
    case 3: assert true; break
    default: assert false
}
```

## 在Java环境中运行groovy

groovy代码和java代码都是运行在jvm中，有两种运行方式：

1. groovyc编译*.groovy代码，生成java的*.class文件，并将这些class文件放置到java类路径中，使用java类加载器进行加载

2. 通过groovy类加载器直接加载groovy代码，不会生成*.class，但是会生成java.lang.Class对象实例。

groovy在源代码级别增强了java，但是在字节码上和java又是一样的。

### GDK：Groovy类库

groovy类库是jdk的扩展，增加了一些新的类，并提供了java类所不具备的新的功能。比如GDK中的size方法，就可以作用于不同的对象并产生不同的结果。

Groovy允许为对象分配动态的方法，这个就是用MetaClass进行控制的

### groovy的生命周期

java是如何在运行时理解.groovy文件的呢？

groovy的代码并不是一行一行被转换的，而是整个文件被完整的转化为java可以执行的类。

groovy的类加载器可以直接从*.groovy的文件中加载类，在放入jvm缓存之前就已经完成了转换和生成的相关工作。

### groovy的动态特性

groovy是在编译后就确定的语言，那么动态特性是如何实现的呢？

因为groovy的方法调用是通过：
```getMetaClass().invokeMethod(this, "foo", EMPTY_PARAM_ARRAY)```的方式进行调用的，那么就相当于是交给了对象的MetaClass进行处理

这个MetaClass就可以在运行时进行拦截、重定向、增加删除方法等操作行为

另一种动态代码的方式是将要执行的动态代码放置在字符串中

```groovy
def code= '1+'
code += System.getProperty('os.version')

println code

println evaluate(code)
```

