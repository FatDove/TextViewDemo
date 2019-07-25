package com.example.textviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：章鱼哥00 on 2019/7/24.
 * CSDN：翻滚吧章鱼 https://blog.csdn.net/weixin_37558974
 * 邮箱：287651776@qq.com
 * 版本：v2.0
 * <p>
 * 「」
 */
public class MyTextView extends View {
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private Rect rect;
    float[] cutWidth = new float[1];

    private Paint namePaint;
    private TextPaint contentPaint;
    private int nickNameColor;
    private String nickName="abab";
    private String content="123456789987654321123456789987654321123456789987654321";
    private float nickNameSize;
    private float contentSize;
    private int cententColor;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        //获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        nickNameColor = typedArray.getColor(R.styleable.MyTextView_nickNameColor, Color.BLUE);
        cententColor = typedArray.getColor(R.styleable.MyTextView_chatContentColor, Color.GRAY);
        nickNameSize = typedArray.getDimension(R.styleable.MyTextView_nickNameSize, 30);
        contentSize = typedArray.getDimension(R.styleable.MyTextView_chatContentSize, 30);
        nickName = typedArray.getString(R.styleable.MyTextView_nickName);
        content = typedArray.getString(R.styleable.MyTextView_chatContent);
        typedArray.recycle();
        init();

    }

    //设置对话item 信息 + 颜色
    public void setChatMessage(String nickName,String content,int nickNameColor,int contentColor) {

        this.nickNameColor =nickNameColor;
        this.cententColor =contentColor;
        this.nickName = nickName;
        this.content = content;
        invalidate();
    }
    //设置对话item 信息
    public void setChatMessage(String nickName,String content) {

        this.nickName = nickName;
        this.content = content;
        invalidate();
    }



    private void init() {
        rect = new Rect();
        namePaint = new TextPaint();
        namePaint.setColor(nickNameColor);
        namePaint.setTextSize(nickNameSize);
        namePaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点到底是left,center,还是right  这里我设置为center
        namePaint.setTextAlign(Paint.Align.LEFT);

        contentPaint = new TextPaint();
        contentPaint.setColor(cententColor);
        contentPaint.setTextSize(contentSize);
        contentPaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点到底是left,center,还是right  这里我设置为center
        contentPaint.setTextAlign(Paint.Align.LEFT);
//        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
//        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
//        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
    }

    @Override
    protected void onDraw(Canvas canvas) {super.onDraw(canvas);

        namePaint.getTextBounds(nickName, 0, nickName.length(), rect);
        int index = contentPaint.breakText(content, 0, content.length(), true, getWidth() - rect.right, cutWidth);
        canvas.drawText(nickName, 0, nickName.length(), 0, namePaint.getFontSpacing(), namePaint);
        canvas.drawText(content, 0, index, rect.right, namePaint.getFontSpacing(), contentPaint);

//        contentPaint.setLetterSpacing(20);
//        canvas.drawText(content, index, content.length(), 0, 50 + contentPaint.getFontSpacing(), contentPaint);
//        contentPaint.breakText(content, start, length, true, maxWidth, cutWidth);
        canvas.save();
        canvas.translate(0,namePaint.getFontSpacing()+5);
        StaticLayout staticLayout = new StaticLayout
                (content.substring(index),
                        contentPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        staticLayout.draw(canvas);
        int offset = (rect.right + rect.left) / 2;
//        canvas.drawText(str,0,str.length(),offset,getHeight()/2,textPaint);
//        canvas.drawText("1111",0,0,paintLeft);
//        canvas.drawText("2222",100,0,paintLeft);
    }
}
