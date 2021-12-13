1.The example of eventBus
    1.1 create eventbus
    1.2 register the subscriber
    1.3 post event
    1.4 unregister the subscriber

2.Subscriber
    2.1 subscribe the event
    2.2 inherit listener
    2.3 inherit event

3.Exception handler

4.DeadEvent

5.Comprehensive pracatice
    5.1 listenter communication each other by eventbus
    5.2 monitor the directory

==Summary==

1.the subscriber object is a normal object.
2.the subscribe method must public and void return value.
3.the subscribe method have only one argument.
4.the subscribe method must be annotated by @Subscribe.

