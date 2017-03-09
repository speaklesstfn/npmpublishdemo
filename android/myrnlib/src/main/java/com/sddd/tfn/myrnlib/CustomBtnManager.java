package com.sddd.tfn.myrnlib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 自定义Button组合供JS调用
 * <p>
 * Created by tfn on 17-2-8.
 */

public class CustomBtnManager extends ViewGroupManager<CustomBtn> {
    private Context mContext;

    @Override
    public String getName() {
        return "CustomBtn";
    }

    @Override
    protected CustomBtn createViewInstance(ThemedReactContext reactContext) {
        this.mContext = reactContext;
        CustomBtn customBtn = (CustomBtn) LayoutInflater.from(reactContext).inflate(R.layout.custom_btn, null);

        return customBtn;
    }

    /**
     * 自定义事件
     */
    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();

        //onBtnClick是原生端发送事件的名称，也即发送时间方法receiveEvent方法的第二个参数值
        // onItemClick是定义在JS端的回调方法
        builder.put("onBtnClick", MapBuilder.of("registrationName", "onItemClick"));
        return builder.build();
    }

    @ReactProp(name = "bgColor")
    public void setBgColor(CustomBtn customBtn, String textColor) {
        customBtn.setBackgroundColor(Color.parseColor(textColor));
    }

    @ReactProp(name = "source")
    public void setSource(final CustomBtn customBtn, ReadableMap map) {
        int screen_width = this.mContext.getResources().getDisplayMetrics().widthPixels;

        ViewGroup.MarginLayoutParams mlp = new ViewGroup.MarginLayoutParams(
                new ViewGroup.LayoutParams((screen_width - 2 * (DisplayUtils.dip2px(this.mContext, 20))) / 3, ViewGroup.LayoutParams.WRAP_CONTENT));

        final List<Button> btnList = new ArrayList<>();

        if (null != map) {
            if (map.hasKey("text")) {
                ReadableArray textArray = map.getArray("text");
                for (int i = 0; i < textArray.size(); i++) {
                    final String eventValue = (i + 1) + "";
                    String value = textArray.getString(i);
                    final Button btn = new Button(this.mContext);
                    btn.setText(value);
                    btn.setBackgroundColor(Color.GRAY);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //                    Button clickBtn = (Button) v;
                            for (int j = 0; j < btnList.size(); j++) {
                                if (btn == btnList.get(j)) {
                                    btnList.get(j).setBackgroundColor(Color.BLUE);

                                    //发送点击事件，将数据传递给js端
                                    WritableMap event = Arguments.createMap();
                                    event.putString("selectNum", eventValue);
                                    ((ReactContext) mContext).getJSModule(RCTEventEmitter.class).receiveEvent(customBtn.getId(), "onBtnClick", event);
                                } else {
                                    btnList.get(j).setBackgroundColor(Color.GRAY);
                                }
                            }
                        }
                    });
                    customBtn.addView(btn, mlp);
                    btnList.add(btn);
                }
            }
        }
    }
}
