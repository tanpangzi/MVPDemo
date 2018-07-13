package com.tan.mvpdemo.bean;

import android.content.Context;
import android.text.TextUtils;


import com.tan.mvpdemo.BaseApplication;
import com.tan.mvpdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 所有Bean的基类，需要处理Json解析的Bean都需要继承自该类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月30日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public abstract class BaseBean implements Serializable, Cloneable {

    private static final long serialVersionUID = 3611224074993323709L;

    /**
     * 无参构造函数
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:29:32
     * <br> UpdateTime: 2016年12月30日,下午11:29:32
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     */
    public BaseBean() {
    }

    /**
     * 构造方法，根据传入的Json字符串生成对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:28:54
     * <br> UpdateTime: 2016年12月30日,下午11:28:54
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param jsonSrc
     *         son字符串
     */
    public BaseBean(String jsonSrc) {
        init(jsonSrc);
    }

    /**
     * 将json数据解析为bean对象，需要实现这个方法
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:24:05
     * <br> UpdateTime: 2016年12月30日,下午11:24:05
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param jSon
     *         传入的json对象
     *
     * @throws JSONException
     *         抛出的json异常
     */
    protected abstract void init(JSONObject jSon) throws JSONException;

    /**
     * 将json数据解析为bean对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:25:31
     * <br> UpdateTime: 2016年12月30日,下午11:25:31
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param jsonSrc
     *         输入的json字符串,用于转换成接送对象
     */
    public void init(String jsonSrc) {
        if (TextUtils.isEmpty(jsonSrc)) {
            return;
        }

        try {
            JSONObject jSon = new JSONObject(jsonSrc);
            init(jSon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 析json数据为cls类型的对象，并赋值于ResponseBean对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:45:21
     * <br> UpdateTime: 2016年12月30日,下午11:45:21
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param responseBean
     *         状态属性对象
     * @param cls
     *         需要解析的class类型
     */
    public static void setResponseObject(ResponseBean responseBean, Class<? extends BaseBean> cls) {
            try {
                BaseBean bean = newInstance(cls, (String) responseBean.getObject());
                responseBean.setObject(bean);
            } catch (JSONException e) {
                e.printStackTrace();
                Context context = BaseApplication.getInstance().getApplicationContext();
                responseBean.setStatus(context.getString(R.string.exception_local_json_code));
                responseBean.setInfo(context.getString(R.string.exception_local_json_message));
        }
    }

    /**
     * 解析json数据为cls类型的对象列表，并赋值于ResponseBean对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:34:07
     * <br> UpdateTime: 2016年12月30日,下午11:34:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param responseBean
     *         状态属性对象
     * @param cls
     *         解析之后生成的对象类
     * @param listKeyName
     *         列表字段的名字(如果是解析子列表需要把自列表的key传入，如果直接是list对象可以不传次参数)
     */
    public static void setResponseObjectList(ResponseBean responseBean, Class<? extends BaseBean> cls, String listKeyName) {
        try {
            String listStr = (String) responseBean.getObject();
            if (listKeyName != null) {
                listStr = new JSONObject(listStr).getString(listKeyName);
            }
            responseBean.setObject(toModelList(listStr, cls));
        } catch (JSONException e) {
            e.printStackTrace();
            Context context = BaseApplication.getInstance().getApplicationContext();
            responseBean.setStatus(context.getString(R.string.exception_local_json_code));
            responseBean.setInfo(context.getString(R.string.exception_local_json_message));
        }
    }
    //
    //	/**
    //	 * 解析json数据为cls类型的对象列表，并赋值于ResponseBean对象
    //	 *
    //	 * <br> Version: 1.0.0
    //	 * <br> CreateTime: 2016年12月30日,下午11:34:07
    //	 * <br> UpdateTime: 2016年12月30日,下午11:34:07
    //	 * <br> CreateAuthor: 叶青
    //	 * <br> UpdateAuthor: 叶青
    //	 * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
    //	 *
    //	 * @param responseBean
    //	 *            状态属性对象
    //	 * @param cls
    //	 *            解析之后生成的对象类
    //	 */
    //	public static void setResponseObjectList(ResponseBean responseBean, Class<? extends BaseBean> cls) {
    //		try {
    //			String listStr = (String) responseBean.getObject();
    //			responseBean.setObject(new ListBean(listStr, cls));
    //		} catch (JSONException e) {
    //			e.printStackTrace();
    //          Context context = BaseApplication.getInstance().getApplicationContext();
    //			responseBean.setStatus(context.getString(R.string.exception_local_json_code));
    //			responseBean.setInfo(context.getString(R.string.exception_local_json_message));
    //		}
    //	}

    /**
     * 对象生成器，根据json字符串和cls类型，new一个BaseBean子类对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:38:39
     * <br> UpdateTime: 2016年12月30日,下午11:38:39
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param cls
     *         解析之后生成的对象类
     * @param jsonSrc
     *         传入的json字符串
     *
     * @return 解析之后的cls对象
     *
     * @throws JSONException
     *         json解析异常
     */
    private static BaseBean newInstance(Class<? extends BaseBean> cls, String jsonSrc) throws JSONException {
        if (jsonSrc == null) {
            return null;
        }
        return newInstance(cls, new JSONObject(jsonSrc));
    }

    /**
     * 对象生成器，根据json对象和clazz类型，new一个BaseBean对象
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:42:07
     * <br> UpdateTime: 2016年12月30日,下午11:42:07
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param cls
     *         需要解析成的class类对象
     * @param jSon
     *         json对象
     *
     * @return 解析之后的cls对象
     *
     * @throws JSONException
     *         json解析异常
     */
    private static BaseBean newInstance(Class<? extends BaseBean> cls, JSONObject jSon) throws JSONException {
        BaseBean model = null;
        try {
            model = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (model != null) {
            model.init(jSon);
        }
        return model;
    }

    /**
     * 将json字符串解析成指定cls对象的列表
     * <p>
     * <br> Version: 1.0.0
     * <br> CreateTime: 2016年12月30日,下午11:53:04
     * <br> UpdateTime: 2016年12月30日,下午11:53:04
     * <br> CreateAuthor: 叶青
     * <br> UpdateAuthor: 叶青
     * <br> UpdateInfo: (此处输入修改内容,若无修改可不写.)
     *
     * @param jsonSrc
     *         需要解析的json字符串
     * @param cls
     *         需要解析成的class对象
     *
     * @return 解析后的cls对象列表
     *
     * @throws JSONException
     *         json解析异常
     */
    public static ArrayList<? extends BaseBean> toModelList(String jsonSrc, Class<? extends BaseBean> cls) throws JSONException {
        ArrayList<BaseBean> modelList = new ArrayList<>();
        if (TextUtils.isEmpty(jsonSrc) || cls == null) {
            return modelList;
        }
        JSONArray jSonArray = new JSONArray(jsonSrc);
        for (int i = 0; i < jSonArray.length(); i++) {
            BaseBean model = newInstance(cls, jSonArray.getJSONObject(i));
            modelList.add(model);
        }
        return modelList;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "BaseBean [toString()=" + super.toString() + "]";
    }
}