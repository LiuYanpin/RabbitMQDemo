本项目Demo是从官网看示例，并且自己简化了一些步骤，
从而使得程序运行过程变得更加简洁明了。

#  Hello World

一个最简单的发送消息-接收消息的Demo。
代码在`/src/main/java/javaversion/tutorial1`底下。
发消息的代码是`Send.java`，接收消息的代码是`Recv.java`。
一个Producing，有一个Queue，一个Consumer。
先运行`Recv`的主函数，让消费者在后台等待接收消息。
在运行`Send`，每运行一次就可以看到接受者的后台打印出相应的信息。
DeliverCallback机制。回调。

# Work Queue 

用于分发耗时的任务。用于避免资源紧张时的情况，特别是网络环境复杂的情况。
对应于本程序，将`FirstWorker`和`SecondWorker`都启动之后，在`NewTask`里启动主函数，
就可以发送100条不同的信息，这些信息会被分到这两个Task上。结果就是两个Task的控制台都会打印出很多自己处理了的信息。  
任务分配原则：`round-robin`，就是循环赛，平均分配任务的原则。  

这里还引入了一种机制叫做`message-ack`，为了确保信息被及时处理，需要再信息处理后返回一个
ack信息。没有超时机制。

# Publish/Subscribe 

使用这种模式的话，只要有消息发布出来，消息的订阅者们都会收到消息。
参考tutorial3的内容，将三个Receive的主函数都跑起来，那么在`EmitLog`运行之后所有
的接受者都会收到一样的信息。
这里引入了一个`exchange type`，消息的发布者不直接将消息发给Queue，而是将消息发给exchange。
`exchange type`有很多类型，其中的fanout代表的就是发布/订阅模式。

# Routing

使用这种模式的话，需要设置一个`routingKey`，每条消息发到对应的routingKey上，
只有绑定了对应key的消息才能收到相应的信息。一个Queue可以有多个`bindingKey`，这样的话对应的消息就会
发到这个Q上。

# Topics
基于模式的一种消息订阅方式。
`*`对应一个单词。
`#`对应多个单词。
例如，`kathy.*`可以匹配`kathy.Alice`和`kathy.Bob`，但是不能匹配`kathy.alice.bob`。
而`kathy.#`可以匹配`kathy.alice.bob`。


