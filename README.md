# BaseKotlinMVVM

Under link say about MVVM but I think it is MVP : 

https://medium.com/@manuelvicnt/rxjava2-android-mvvm-lifecycle-app-structure-with-retrofit-2-cf903849f49e

* If you use Flowable, you must use subscribeWith() to dispose() it
* If you use Maybe, we’re not going to unsubscribe from it.

* if you're multi result:

_ Observable: Emits 0 or n items and terminates with an success or an error event.

_ Flowable: is observable + backpressure: Emits 0 or n items and terminates with an success or an error event. Supports backpressure, which allows to control how fast a source emits items.

* if you’re only expecting a single result:

_ Single: Emits either a single item or an error event. The reactive version of a method call.

_ Completable: Either completes with an success or with an error event. It never emits items. The reactive version of a Runnable.

_ Maybe: Single + Completable: Succeeds with an item, or no item, or errors. The reactive version of an Optional.

