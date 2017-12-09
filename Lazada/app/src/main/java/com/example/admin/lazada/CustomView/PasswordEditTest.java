package com.example.admin.lazada.CustomView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.admin.lazada.R;

/**
 * Created by ADMIN on 12/5/2017.
 */

@SuppressLint("AppCompatCustomView")
public class PasswordEditTest extends EditText {

    Drawable eye, eyeStrike;
    boolean visible = false;
    boolean useStrike = false;
    Drawable drawable;
    int ALPHA = (int)(255 * .55F);

    public PasswordEditTest(Context context) {
        super(context);
        init(null);
    }

    public PasswordEditTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PasswordEditTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs){

        if (attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditTest, 0, 0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditTest_useStrike, false);
        }

        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        setting();

    }

    private void setting(){
        setInputType(InputType.TYPE_CLASS_TEXT | (visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            setting();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
