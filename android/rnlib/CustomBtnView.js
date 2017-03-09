/**
 * Created by tfn on 17-2-9.
 */
import React, {PropTypes, Component} from 'react';
import {requireNativeComponent, View} from 'react-native';


class CustomBtnView extends Component {

    _onItemClick = (event) => {
        if (!this.props.onItemClick) {
            return;
        }

        this.props.onItemClick(event.nativeEvent.selectNum);
    };

    render() {
        return (
            <RCTCustomBtn {...this.props} onItemClick={this._onItemClick}/>
        );
    }
}

CustomBtnView.name = 'custombtn';
CustomBtnView.propTypes = {
    bgColor: PropTypes.string,
    onItemClick: PropTypes.func,
    source: PropTypes.shape({
        text: PropTypes.array,
    }),
    ...View.propTypes,
};

let RCTCustomBtn = requireNativeComponent('CustomBtn', CustomBtnView);
module.exports = CustomBtnView;