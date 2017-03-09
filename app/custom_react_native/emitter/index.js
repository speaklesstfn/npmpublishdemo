/**
 * Created by tfn on 17-3-6.
 */

/**
 * 单例emitter对象
 */

// var {EventEmitter} = require('fbemitter');
// var emitter = new EventEmitter();
import MyEventEmitter from './MyEventEmitter';

let emitter = new MyEventEmitter();

export default emitter;
