/**
 * Created by tfn on 17-2-13.
 */

import React, {PropTypes, Component} from 'react';

import {StyleSheet, View, Text, Dimensions} from 'react-native';

import emitter from '../emitter/index';

/**
 * 自定义组合Text
 */
class MyText extends Component {

    constructor(props) {
        super(props);
        this.state = {
            totalPrice: '0.00',
        };
    }

    componentWillMount() {
        this.subscription = emitter.addListener('copies', (copies) => {
            console.log('click get to text :' + copies);
            this.setState({
                totalPrice: (parseFloat(this.props.singlePrice) * copies).toFixed(2) + '',
            });
        });
    }

    componentDidUnMount() {
        emitter.removeListener(this.subscription);
        // emitter.removeAllListeners();
    }

    render() {
        let space = '    ';

        // console.log('Text render');

        return (
            <View
                style={[styles.textView,{width:this.props.width,height:this.props.height,backgroundColor:this.props.bgColor}]}>
                <Text style={{alignItems:'center'}}>
                    <Text
                        style={[
                        styles.single,
                        {
                            color:this.props.singleColor,
                            fontSize:this.props.singleSize,
                        }
                    ]}>
                        单价：{this.props.singlePrice}{space}
                    </Text>
                    <Text
                        style={[
                        styles.total,
                        {
                            color:this.props.totalColor,
                            fontSize:this.props.totalSize,
                        }
                    ]}>
                        总价：{this.state.totalPrice}
                    </Text>
                </Text>

            </View>
        );
    }
}

//定义属性类型
MyText.propTypes = {
    width: PropTypes.number,
    height: PropTypes.number,
    bgColor: PropTypes.string,
    singleColor: PropTypes.string,
    singleSize: PropTypes.number,
    singlePrice: PropTypes.string,
    totalColor: PropTypes.string,
    totalSize: PropTypes.number,
};

//定义属性默认值
MyText.defaultProps = {
    width: Dimensions.get('window').width,
    height: 50,
    bgColor: '#ffffe0',
    singleColor: '#daa520',
    singleSize: 20,
    singlePrice: '130.48',
    totalColor: '#ff0000',
    totalSize: 20,
};

const styles = StyleSheet.create({
    textView: {
        alignItems: 'center',
        justifyContent: 'center'
    },
    single: {
        textAlign: 'center',

    },
    total: {
        textAlign: 'center',
    },
});

module.exports = MyText;