/**
 * Created by tfn on 17-3-9.
 */
import Rx from 'rxjs/Rx';

export default class MyEventEmitter {

    constructor() {
        //创建单例emitter，能够在每次有订阅的时候自动发送所存储的最近的100个数据
        //若有需要，可以添加第二个参数，限定时间范围，单位是毫秒ms
        this.subject = new Rx.ReplaySubject(100);
    }

    /**
     * 添加监听
     * @param eventType 监听的消息类型
     * @param listener 监听到消息后所做的处理
     */
    addListener(eventType, listener) {
        if (this.subject) {
            this.subject.filter((obj) => {
                //过滤出所存储数据的eventName与订阅的消息名称一样的消息
                return obj.eventName == eventType;
            }).subscribe({
                next: (v) => {
                    listener(v.values);
                },
                error: (e) => {
                    console.log(`error is ${e}`);
                },
                complete: () => {
                    console.log('complete');
                },
            });
        }
    }

    /**
     * 发送消息
     * @param eventName 发送的消息名称
     * @param values 发送的消息内容
     */
    emit(eventName, values) {
        if (this.subject) {
            this.subject.next({
                eventName: eventName,
                values: values,
            });
        }
    }

    /**
     * 移除所有消息监听
     */
    removeAllListeners() {
        if (this.subject) {
            this.subject.complete();
        }
    }
}