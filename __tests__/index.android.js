// Note: test renderer must be required after react-native.

import emitter from '../app/custom_react_native/emitter/index';

it('ttt', () => {
    this.subscription = emitter.addListener('copies', (copies) => {
        console.log('click get to text :' + copies);

    });

    console.log('dfdf');

    emitter.emit('copies', 'ffddf');

    this.subscription.unsubscribe();

    emitter.emit('copies', 'fuck');
});
