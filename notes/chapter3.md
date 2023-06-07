# Groovy数据类型

groovy在语言层面支持一套数据类型，提供了直接声明和特定的操作符，包括字符、正则和数字类型。也包括范围、列表和映射等。

我将会在这里使用groovy所有的简单类型和集合类型

## 简单类型

### Java的专有类型和引用类型

1. Java中的专有类型：int/double/char/byte/long 等

2. Java中的引用类型：Object/String 等

在java中不能创建自定义的值类型。

而引用类型指向了一个具体的对象，Java不能在专有类型上进行方法调用，也不能向对Object一样操作专用类型，容器只能接受专有类型的包装类，
比如```ArrayList<Integer>```是合法的，但是```ArrayList<int>```就是不合法的。

### Groovy的答案：一切都是对象

对于一个功能，Java实现是：
```java
class Add{
    Integer first = list.get(0);
    Integer second = list.get(1);
    
    int sum = first.intValue() + second.intValue();
    List<Integer> result = new ArrayList<>();
    
    result.add(sum);
}
```

而到了groovy，就只需要一行代码：
```groovy
results.add(first + second)
```

除此之外groovy还支持自己实现操作符

java中有特定的专有类型和包装类型，但是groovy中都是对包装类型对象的引用。

使用groovy调用java的时候，可以自动进行拆箱和装箱操作。

groovy在执行"1+1"的时候，直接使用的是Integer的plus方法，相当于1.plus(1)，生成了一个值为2的新对象

### Groovy可选对象概念

对象声明的一些方式

```groovy
// 隐式声明
def a = 1
def b = 1.0f

// 使用java专有类型声明
int c = 1
float d = 1

// 使用引用类型声明
Integer  e = 1
String f = 'str'
```

但是Groovy不会允许把一个确定类型的对象当成另一个类型的对象，比如'1'就不能是Integer

### 重载操作符

为了达到某个目的，在面向对象的语言中，子类型对父类型进行的方法重写。

重写操作符实战：

```groovy
class Money {
    private int amount
    private String currency

    Money (amountValue, currencyValue) {
        amount = amountValue
        currency = currencyValue
    }

    boolean equals (Object other) {
        if (null == other) return false
        if (! (other instanceof Money)) return false
        if (currency != other.currency) return false
        if (amount != other.amount) return false
        return true
    }

    int hashCode() {
        amount.hashCode() + currency.hashCode()
    }

    Money plus (Money other) {
        if (null == other) reutrn null
        if (other.currency != currency) {
            throw new IllegalArgumentException("can not add different currency")
        }
        return new Money(amount + other.amount, currency)
    }
}

def buck = new Money(1, 'USD')
assert buck
assert buck == new Money(1, 'USD')
assert buck + buck == new Money(2, 'USD')
```

```1+1.5```返回的是Integer, ```1.5+1```返回的是BigDecimal

## 字符串类型

Groovy为处理字符串提供了很多便捷的特性，有两种处理风格：1.GString嚯java.lang.String的实例

GString可以接收占位符并且在运行时对占位符进行解析和计算。

有几种表现形式：

1. ```'str'```单引号，不会使用GString来处理，等价于java字符串
2. ```"str"```双引号，和单引号等价，如果没有$转义符，就会被加工成GString
3. ```'''str'''```三引号，类似于Ruby中的HERE-document
4. ```/```标识字符串，不会转义反斜杠``` \ ``` ，适用于正则表达式的定义

Groovy提供了使用的to*方法，比如toInteger，toLong，toFloat和toDouble

### 使用GString

GString可以根据需要自动转换为java.lang.String

```groovy
me = 'Tarzan'
you = 'Jane'
line = "me $me - you $you"
assert line == 'me Tarzan - you Jane'

date = new Date(0)
out = "Year $date.year Month $date.month Day $date.date"
assert  out == 'Year 70 Month 0 Day 1'

out = "Date is ${date.toGMTString()} !"
assert out == 'Date is 1 Jan 1970 00:00:00 GMT !'

sql = """
SELECT FROM MyTable
WHERE Year = $date.year
"""
assert sql == """
SELECT FROM MyTable
WHERE Year = 70
"""

out = "my 0.02\$"
assert out == 'my 0.02$'
```

Gstring中，通过```$```符号去进行变量引用，并使用它的方法或者属性值

使用```${}```进行完整的闭包运算

### 使用StringBuffer

可以直接在groovy中使用StringBuffer完成对于字符串的操作需求

```groovy
greeting = 'Hello'
greeting <<= ' Groovy'
assert greeting instanceof java.lang.StringBuffer
greeting <<= '!'
assert greeting.toString() == 'Hello Groovy!'
greeting[1..4] = 'i'
assert greeting.toString() == 'Hi Groovy!'
```

## 使用正则表达式

正则表达式可以通过一个模式而不是编程来对字符串进行匹配，并且groovy为其提供了便利性的三个操作符：

1. regex查找"=~"
2. regex匹配"==~"
3. regex模式操作符:~String

