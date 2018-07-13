package com.tan.mvpdemo.uitl;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.tan.mvpdemo.BaseApplication;


/**
 * 打开或关闭软键盘 点击edittext之外 隐藏软键盘
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月11日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class KeyboardUtil {

    /**
     * 显示软键盘
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-5,下午3:41:16
     * <br> UpdateTime: 2016-1-5,下午3:41:16
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         输入框 EditText
     */
    // 显示输入法
    public static void showKeyBord(View view) {
        if (view == null) {
            return;
        }
        Context context = BaseApplication.getInstance().getApplicationContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //同时再使用该方法之前，view需要获得焦点，可以通过requestFocus()方法来设定。
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏软键盘
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-5,下午3:41:16
     * <br> UpdateTime: 2016-1-5,下午3:41:16
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         View
     */
    public static void hideKeyBord(View view) {
        if (view == null) {
            return;
        }
        Context context = BaseApplication.getInstance().getApplicationContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断输入法是否已经打开
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016-1-5,下午3:41:16
     * <br> UpdateTime: 2016-1-5,下午3:41:16
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public static boolean isShowKeyBord() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    /**
     * Android InputMethodManager 导致的内存泄露及解决方案
     * <p>
     * <br> Version: 3.0.0
     * <br> CreateAuthor: 叶青
     * <br> CreateTime: 2017/3/8 9:42
     * <br> UpdateAuthor: 叶青
     * <br> UpdateTime: 2017/3/8 9:42
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param view
     *         View
     */
    public static void fixFocusedViewLeak(View view) {
        if (view == null) {
            return;
        }
        try {
            Context context = BaseApplication.getInstance().getApplicationContext();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            InputMethodManager.class.getDeclaredMethod("windowDismissed", IBinder.class).invoke(imm, view.getWindowToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    /**
    //     * Fix for https://code.google.com/p/android/issues/detail?id=171190 .
    //     * Android InputMethodManager 导致的内存泄露及解决方案
    //     * <p>
    //     * When a view that has focus gets detached, we wait for the main thread to be idle and then
    //     * check if the InputMethodManager is leaking a view. If yes, we tell it that the decor view got
    //     * focus, which is what happens if you press home and come back from recent apps. This replaces
    //     * the reference to the detached view with a reference to the decor view.
    //     * <p>
    //     * Should be called from {@link Activity#onCreate(android.os.Bundle)} )}.
    //     */
    //    public static void fixFocusedViewLeak() {
    //
    //        // Don't know about other versions yet.
    //        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 || Build.VERSION.SDK_INT > 23) {
    //            return;
    //        }
    //
    //        final InputMethodManager imm = (InputMethodManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    //
    //        final Field mServedViewField;
    //        final Field mHField;
    //        final Method finishInputLockedMethod;
    //        final Method focusInMethod;
    //        try {
    //            mServedViewField = InputMethodManager.class.getDeclaredField("mServedView");
    //            mServedViewField.setAccessible(true);
    //            mHField = InputMethodManager.class.getDeclaredField("mServedView");
    //            mHField.setAccessible(true);
    //            finishInputLockedMethod = InputMethodManager.class.getDeclaredMethod("finishInputLocked");
    //            finishInputLockedMethod.setAccessible(true);
    //            focusInMethod = InputMethodManager.class.getDeclaredMethod("focusIn", View.class);
    //            focusInMethod.setAccessible(true);
    //        } catch (Exception unexpected) {
    //            Log.e("IMMLeaks", "Unexpected reflection exception", unexpected);
    //            return;
    //        }
    //
    //        BaseApplication.getInstance().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
    //            @Override
    //            public void onActivityDestroyed(Activity activity) {
    //
    //            }
    //
    //            @Override
    //            public void onActivityStarted(Activity activity) {
    //
    //            }
    //
    //            @Override
    //            public void onActivityResumed(Activity activity) {
    //
    //            }
    //
    //            @Override
    //            public void onActivityPaused(Activity activity) {
    //
    //            }
    //
    //            @Override
    //            public void onActivityStopped(Activity activity) {
    //
    //            }
    //
    //            @Override
    //            public void onActivitySaveInstanceState(Activity activity, Bundle getBundle) {
    //
    //            }
    //
    //            @Override
    //            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    //                ReferenceCleaner cleaner = new ReferenceCleaner(imm, mHField, mServedViewField, finishInputLockedMethod);
    //                View rootView = activity.getWindow().getDecorView().getRootView();
    //                ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
    //                viewTreeObserver.addOnGlobalFocusChangeListener(cleaner);
    //            }
    //        });
    //    }
    //
    //    private static class ReferenceCleaner implements MessageQueue.IdleHandler, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalFocusChangeListener {
    //
    //        private final InputMethodManager imm;
    //        private final Field mHField;
    //        private final Field mServedViewField;
    //        private final Method finishInputLockedMethod;
    //
    //        ReferenceCleaner(InputMethodManager imm, Field mHField, Field mServedViewField, Method finishInputLockedMethod) {
    //            this.imm = imm;
    //            this.mHField = mHField;
    //            this.mServedViewField = mServedViewField;
    //            this.finishInputLockedMethod = finishInputLockedMethod;
    //        }
    //
    //        @Override
    //        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
    //            if (newFocus == null) {
    //                return;
    //            }
    //            if (oldFocus != null) {
    //                oldFocus.removeOnAttachStateChangeListener(this);
    //            }
    //            Looper.myQueue().removeIdleHandler(this);
    //            newFocus.addOnAttachStateChangeListener(this);
    //        }
    //
    //        @Override
    //        public void onViewAttachedToWindow(View v) {
    //        }
    //
    //        @Override
    //        public void onViewDetachedFromWindow(View v) {
    //            v.removeOnAttachStateChangeListener(this);
    //            Looper.myQueue().removeIdleHandler(this);
    //            Looper.myQueue().addIdleHandler(this);
    //        }
    //
    //        @Override
    //        public boolean queueIdle() {
    //            clearInputMethodManagerLeak();
    //            return false;
    //        }
    //
    //        private void clearInputMethodManagerLeak() {
    //            try {
    //                Object lock = mHField.get(imm);
    //                // This is highly dependent on the InputMethodManager implementation.
    //                synchronized (lock) {
    //                    View servedView = (View) mServedViewField.get(imm);
    //                    if (servedView != null) {
    //
    //                        boolean servedViewAttached = servedView.getWindowVisibility() != View.GONE;
    //
    //                        if (servedViewAttached) {
    //                            // The view held by the IMM was replaced without a global focus change. Let's make
    //                            // sure we get notified when that view detaches.
    //                            // Avoid double registration.
    //                            servedView.removeOnAttachStateChangeListener(this);
    //                            servedView.addOnAttachStateChangeListener(this);
    //                        } else {
    //                            // servedView is not attached. InputMethodManager is being stupid!
    //                            Activity activity = extractActivity(servedView.getContext());
    //                            if (activity == null || activity.getWindow() == null) {
    //                                // Unlikely case. Let's finish the input anyways.
    //                                finishInputLockedMethod.invoke(imm);
    //                            } else {
    //                                View decorView = activity.getWindow().peekDecorView();
    //                                boolean windowAttached = decorView.getWindowVisibility() != View.GONE;
    //                                if (!windowAttached) {
    //                                    finishInputLockedMethod.invoke(imm);
    //                                } else {
    //                                    decorView.requestFocusFromTouch();
    //                                }
    //                            }
    //                        }
    //                    }
    //                }
    //            } catch (Exception unexpected) {
    //                Log.e("IMMLeaks", "Unexpected reflection exception", unexpected);
    //            }
    //        }
    //
    //        private Activity extractActivity(Context context) {
    //            while (true) {
    //                if (context instanceof Application) {
    //                    return null;
    //                } else if (context instanceof Activity) {
    //                    return (Activity) context;
    //                } else if (context instanceof ContextWrapper) {
    //                    Context baseContext = ((ContextWrapper) context).getBaseContext();
    //                    // Prevent Stack Overflow.
    //                    if (baseContext == context) {
    //                        return null;
    //                    }
    //                    context = baseContext;
    //                } else {
    //                    return null;
    //                }
    //            }
    //        }
    //    }
}