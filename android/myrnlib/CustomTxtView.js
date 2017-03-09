/**
 * Created by tfn on 17-2-10.
 */
import React, {PropTypes, Component} from 'react';
import {requireNativeComponent, View} from 'react-native';

class CustomTxtView extends Component {
    render() {
        return (
            <RCTCustomTxt {...this.props}/>
        );
    }
}

CustomTxtView.name = "customtxt";
CustomTxtView.propTypes = {
    bgColor: PropTypes.string,
    singleTxtValue: PropTypes.string,
    singleTxtColor: PropTypes.string,
    singleTxtSize: PropTypes.number,
    totalTxtValue: PropTypes.string,
    totalTxtColor: PropTypes.string,
    totalTxtSize: PropTypes.number,
    ...View.propTypes,
};

let RCTCustomTxt = requireNativeComponent("CustomTxt", CustomTxtView);

module.exports = CustomTxtView;
