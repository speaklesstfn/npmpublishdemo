package com.sddd.tfn.myrnlib;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import android.graphics.Color;
import android.widget.TextView;

/**
 * 自定义TextView组合供JS调用
 * <p>
 * Created by tfn on 17-2-10.
 */

public class CustomTxtManager extends ViewGroupManager<CustomTxt> {
    @Override
    public String getName() {
        return "CustomTxt";
    }

    @Override
    protected CustomTxt createViewInstance(ThemedReactContext themedReactContext) {
        CustomTxt customTxt = new CustomTxt(themedReactContext);
        return customTxt;
    }

    @ReactProp(name = "bgColor")
    public void setBgColor(CustomTxt customTxt, String textColor) {
        customTxt.setBackgroundColor(Color.parseColor(textColor));
    }

    @ReactProp(name = "singleTxtValue")
    public void setSingleTxtValue(CustomTxt customTxt, String textValue) {
        TextView singleTxt = customTxt.getSinglePriceTxt();
        singleTxt.setText(textValue);
    }

    @ReactProp(name = "singleTxtColor")
    public void setSingleTxtColor(CustomTxt customTxt, String textColor) {
        TextView singleTxt = customTxt.getSinglePriceTxt();
        singleTxt.setTextColor(Color.parseColor(textColor));
    }

    /**
     * 设置单价文字的大小
     *
     * @param customTxt 自定义对象
     * @param textSize  文字大小，单位为sp
     */
    @ReactProp(name = "singleTxtSize")
    public void setSingleTxtSize(CustomTxt customTxt, int textSize) {
        TextView singleTxt = customTxt.getSinglePriceTxt();
        singleTxt.setTextSize(textSize);
    }

    @ReactProp(name = "totalTxtValue")
    public void setTotalTxtValue(CustomTxt customTxt, String textValue) {
        TextView totalTxt = customTxt.getTotalPriceTxt();
        totalTxt.setText(textValue);
    }

    @ReactProp(name = "totalTxtColor")
    public void settotalTxtColor(CustomTxt customTxt, String textColor) {
        TextView totalTxt = customTxt.getTotalPriceTxt();
        totalTxt.setTextColor(Color.parseColor(textColor));
    }

    @ReactProp(name = "totalTxtSize")
    public void setTotalTxtSize(CustomTxt customTxt, int textSize) {
        TextView totalTxt = customTxt.getTotalPriceTxt();
        totalTxt.setTextSize(textSize);
    }
}
