package com.sddd.tfn.myrnlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义组合按钮控件
 * <p>
 * Created by tfn on 17-2-8.
 */

public class CustomBtn extends ViewGroup {

    private int childHorizontalSpace;
    private int childVerticalSpace;

    public CustomBtn(Context context) {
        this(context, null);
    }

    public CustomBtn(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        // 遍历每个子元素
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            Location location = (Location) child.getTag();
            child.layout(location.getLeft(), location.getTop(), location.getRight(), location.getBottom());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        // 记录warp_content情况下所需要的宽和高
        int width = 0;
        int height = 0;
        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度
         */
        int lineHeight = 0;

        int count = getChildCount();

        int left = getPaddingLeft();
        int top = getPaddingTop();

        // 遍历每个子元素
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + childHorizontalSpace;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + childVerticalSpace;

            if ((i + 1) % 3 == 1) {
                width = Math.max(lineWidth, childWidth);// 取最大的
                lineWidth = childWidth; // 重新开启新行，开始记录
                // 叠加当前高度，
                height += lineHeight;
                // 开启记录下一行的高度
                lineHeight = childHeight;
                child.setTag(new Location(left, top + height, childWidth + left - childHorizontalSpace, height + child.getMeasuredHeight() + top));
            } else {// 否则累加值lineWidth,lineHeight取最大高度
                child.setTag(new Location(lineWidth + left, top + height, lineWidth + childWidth - childHorizontalSpace + left,
                        height + child.getMeasuredHeight() + top));
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
        }
        width = Math.max(width, lineWidth) + getPaddingLeft() + getPaddingRight();
        height += lineHeight;
        sizeHeight += getPaddingTop() + getPaddingBottom();
        height += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取自定义属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomBtn, defStyleAttr, 0);

        if (null != typedArray) {
            childHorizontalSpace = typedArray.getDimensionPixelSize(R.styleable.CustomBtn_btnHorizontalSpace, 0);
            childVerticalSpace = typedArray.getDimensionPixelSize(R.styleable.CustomBtn_btnVerticalSpace, 0);
            typedArray.recycle();
        }

    }

    /**
     * 记录子控件的坐标
     */
    class Location {
        private int left;
        private int top;
        private int right;
        private int bottom;

        public Location(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }
    }
}
