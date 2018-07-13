package com.tan.mvpdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * Created by tanjun on 2017/5/31.
 * 限制表情的EditText
 */

@SuppressLint("AppCompatCustomView")
public class ContainsEmojiEditText extends EditText {

    private Context mContext;

    public ContainsEmojiEditText(Context context) {
        super(context);
        this.mContext = context;

    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new mInputConnecttion(super.onCreateInputConnection(outAttrs),
                false);
    }

    class mInputConnecttion extends InputConnectionWrapper implements
            InputConnection {

        public mInputConnecttion(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        /**
         * 对输入的内容进行拦截
         *
         * @param text
         * @param newCursorPosition
         * @return
         */
        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            // 只能输入汉字  [^a-zA-Z0-9\u4E00-\u9FA5]
            if (!text.toString().matches("[a-zA-Z0-9\u4e00-\u9fa5]+")) {
                    return false;
            }
            return super.commitText(text, newCursorPosition);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean setSelection(int start, int end) {
            return super.setSelection(start, end);
        }

    }
}

