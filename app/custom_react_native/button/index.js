/**
 * Created by tfn on 17-2-13.
 */
import React, {PropTypes, Component} from 'react';

import {StyleSheet, View, Text, Dimensions, TouchableHighlight} from 'react-native';

import emitter from '../emitter/index';
/**
 * 自定义组合Button
 */
class MyButton extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedIndex: -1,
        };
    }

//点击按钮
    _onItemClick = (index) => {
        this.setState({
            selectedIndex: index,
        });

        // console.log('click button number:' + index + 1);
        //将按钮对应的份数以消息的形式发送出去，不考虑接收者
        emitter.emit('copies', index + 1);
    };

//渲染每个Button
    _renderButton = (index) => {
        let {text} = this.props.source;
        let totalRow = text.length % 3 === 0 ? Math.floor(text.length / 3) : Math.floor(text.length / 3) + 1;

        // console.log('button render le:' + index);

        return (
            <TouchableHighlight
                style={[styles.btnView,{width:(this.props.width - 40)/3,height:(this.props.height - ((totalRow + 1)*10))/totalRow,backgroundColor:(this.state.selectedIndex === index ?'#0000ff':'#808080')}]}
                key={index}
                disabled={this.state.selectedIndex === index}
                onPress={()=>this._onItemClick(index)}>
                <Text>{text[index]}</Text>
            </TouchableHighlight>
        );
    };

    render() {
        return (
            <View
                style={[styles.view,{width:this.props.width,height:this.props.height,backgroundColor:this.props.bgColor}]}>
                {this.props.source.text.map((textValue, index) => {
                    return this._renderButton(index);
                })}
            </View>
        );
    }
}

//自定义属性类型
MyButton.propTypes = {
    width: PropTypes.number,
    height: PropTypes.number,
    bgColor: PropTypes.string,
    source: PropTypes.shape({
        text: PropTypes.array,
    }),
};

//自定义属性默认值
MyButton.defaultProps = {
    width: Dimensions.get('window').width,
    height: 125,
    bgColor: '#d8bfd8',
    source: {
        text: [
            "一份",
            "两份",
            "三份",
            "四份",
        ]
    },
};

const styles = StyleSheet.create({
    view: {
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'center',
        padding: 5,
        marginTop: 20,
        flexWrap: 'wrap',
    },
    rowView: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        // flex: 1,
    },
    btnView: {
        justifyContent: 'center',
        alignItems: 'center',
        margin: 5,
    },
});

module.exports = MyButton;
