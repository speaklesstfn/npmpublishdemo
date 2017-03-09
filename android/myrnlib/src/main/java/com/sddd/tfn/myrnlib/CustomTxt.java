package com.sddd.tfn.myrnlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义TextView
 * <p>
 * Created by tfn on 17-2-10.
 */

public class CustomTxt extends RelativeLayout {
    private TextView singlePriceTxt = null;
    private TextView totalPriceTxt = null;

    private int singlePriceTxtColor = 0;
    private int singlePriceTxtSize = 0;
    private int totalPriceTxtColor = 0;
    private int totalPriceTxtSize = 0;

    public CustomTxt(Context context) {
        this(context, null);
    }

    public CustomTxt(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    public TextView getSinglePriceTxt() {
        return singlePriceTxt;
    }

    public TextView getTotalPriceTxt() {
        return totalPriceTxt;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.custom_txt, this, true);
        singlePriceTxt = (TextView) findViewById(R.id.single_price_txt);
        totalPriceTxt = (TextView) findViewById(R.id.total_price_txt);
        TextView singlePrice = (TextView) findViewById(R.id.singe_price);
        TextView totalPrice = (TextView) findViewById(R.id.total_price);

        //获取自定义属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTxt, defStyleAttr, 0);
        if (null != typedArray) {
            //单价文字颜色
            singlePriceTxtColor = typedArray.getColor(R.styleable.CustomTxt_single_price_txt_color, Color.GRAY);
            singlePriceTxt.setTextColor(singlePriceTxtColor);
            singlePrice.setTextColor(singlePriceTxtColor);

            //单价文字大小
            singlePriceTxtSize = typedArray.getDimensionPixelSize(R.styleable.CustomTxt_single_price_txt_size, DisplayUtils.sp2px(context, 20));
            //setTextSize需要的int参数是sp为单位的，因此需要将像素值px转换为sp
            singlePriceTxt.setTextSize(DisplayUtils.px2sp(context, singlePriceTxtSize));
            singlePrice.setTextSize(DisplayUtils.px2sp(context, singlePriceTxtSize));

            //总价文字颜色
            totalPriceTxtColor = typedArray.getColor(R.styleable.CustomTxt_total_price_txt_color, Color.GRAY);
            totalPriceTxt.setTextColor(totalPriceTxtColor);
            totalPrice.setTextColor(totalPriceTxtColor);

            //总价文字大小
            totalPriceTxtSize = typedArray.getDimensionPixelSize(R.styleable.CustomTxt_total_price_txt_size, DisplayUtils.sp2px(context, 20));
            totalPriceTxt.setTextSize(DisplayUtils.px2sp(context, totalPriceTxtSize));
            totalPrice.setTextSize(DisplayUtils.px2sp(context, totalPriceTxtSize));

            typedArray.recycle();
        }
    }
}
