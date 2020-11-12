package com.cvte.taobaounion.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2020/11/12.
 */

public class TextFlowLayout extends ViewGroup {
    private static final String TAG = "TextFlowLayout";
    private List<String> mTextList = new ArrayList<>();

    public static final float DEFAULT_SPACE = 10;
    private float mItemHorizonticalSpace = DEFAULT_SPACE;
    private float mItemVerticalSpace = DEFAULT_SPACE;

    public float getItemHoriaontalSpace() {
        return mItemHorizonticalSpace;

    }

    public void setItemHoriaontalSpace(float itemHoriaontalSpace) {
        mItemHorizonticalSpace = itemHoriaontalSpace;
    }

    public float getItemVerticalSpace() {
        return mItemVerticalSpace;
    }

    public void setItemVerticalSpace(float itemVerticalSpace) {
        mItemVerticalSpace = itemVerticalSpace;
    }

    public TextFlowLayout(Context context) {
        this(context,null);
    }

    public TextFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TextFlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        /*拿到style.xml自定义的属性*/
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.FlowTextStyle);
        //得到属性
        mItemHorizonticalSpace = ta.getDimension(R.styleable.FlowTextStyle_horizontalSpace,DEFAULT_SPACE);
        mItemVerticalSpace = ta.getDimension(R.styleable.FlowTextStyle_verticalSpace,DEFAULT_SPACE);

        ta.recycle();
    }
    
    public void setTextList(List<String> textList){
        this.mTextList = textList;
        for (String text : mTextList) {
            //TextView textView = new TextView(getContext());
            /*创建TextView或者直接载入XML布局文件*/
            //true = false + addView()
            TextView itemView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_text_view, this, false);
            itemView.setText(text);

            addView(itemView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*1. 测量*/
        LogUtils.d(TAG,"onMeasure--> childCount"+getChildCount());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*2. viewGroup 摆放子控件 */
        
    }
}
