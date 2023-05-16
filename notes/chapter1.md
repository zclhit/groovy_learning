# Groovy

更多代码相关的内容可以参考：https://github.com/zclhit/groovy_learning/tree/main

## 介绍

groovy代码比java更简单，适合自动化重复任务，编写日常工作脚本。

运行在java平台之上，具备灵活动态的特性。

并不仅仅是脚本语言，而是可以集成到java或者和web应用程序中，或是用groovy编写整个程序。

## 功能

扩展java:更简洁的方式实现java程序，为java应用程序赋予动态行为
写脚本:很方便的在J2EE等环境中直接执行脚本
自动化工具：适用于CI/CD相关流程能力实现，支持Ant和Maven等，以及快捷的TDD集成工作。

## 运行groovy

### groovysh

groovy形式的shell，在命令行中输入一行一行的代码，输入go执行。

```groovy
groovy:000> println("test");
test
===> null
groovy:000> 40+2;
===> 42`
groovy:000> println("test");
test
===> null
groovy:000> 40+2;
===> 42
```

### groovyConsole

见相关codes，是一个使用groovy编写的图形化页面

### groovy命令直接执行

```groovy xxx.groovy```

或者直接

```groovy -e "println("Hello World!");"```
即可

## 编译和运行groovy

将groovy编译成java可以执行的字节码Class文件
```groovyc -d classes xxx.groovy```
然后直接使用Java命令运行生成的class文件

或者直接使用Ant对groovy文件进行编译和运行

## 使用集成开发环境运行Groovy

比如安装IntelliJ IDEA的插件：groovyJ



