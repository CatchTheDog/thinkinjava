package com.thinkinjava.example.annotation;
/**
 * java 内置了三种注解：@Override   @Deprecated  @SuperessWarnings
 *除此之外，java内置了三个元注解，用来注解注解类；
 * @Target      定义该注解用于什么地方
 * @Retention   定义该注解在哪一个级别可用
 * @Documented 将此注解包含在javadoc中
 * @Inherited 允许子类继承父类中的注解
 *
 * 注解元素可用类型如下：
 *  所有基本类型
 *  String
 *  Class
 *  enum
 *  Annotation
 *  以上类型的数组
 *
 * 默认值限制：注解元素必须具有确定的值，要么在指定默认值，要么在外部指定，不可以值为null
 *      可以采用定义特殊值如0，-1,""等值来绕开此限制
 *
 * 注解不支持继承
 *
 *
 *
 */