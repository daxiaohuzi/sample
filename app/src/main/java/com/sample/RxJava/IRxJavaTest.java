package com.sample.RxJava;

/**
 * Created by xiaoye on 2016/4/24.
 */
public interface IRxJavaTest {

    /**
     * 创建
     */
    void create();

    /**
     * 过滤
     */
    void filter();

    /**
     * 转换
     */
    void map();

    /**
     * 装换Obserable
     */
    void flatMap();

    void lift();

    /**
     * 延迟
     */
    void delay();

    /**
     * 各种命令运行之前调用
     */
    void Do();

    /**
     * 超时
     */
    void timeout();

    /**
     * 可以 获取onNext之间的时间
     * 1秒的话 getIntervalInMilliseconds得到为1000
     * 和timestamp类似 不过timestamp得到的是系统的时间 getTimestampMillis
     */
    void timeInterval();
    /** 跳过前面几个元素 */
    void skip();
    /** 取前面几个元素 */
    void take();
}
