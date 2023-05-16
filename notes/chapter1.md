# Groovy

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
