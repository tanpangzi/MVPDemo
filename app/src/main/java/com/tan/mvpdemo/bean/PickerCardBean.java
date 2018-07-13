package com.tan.mvpdemo.bean;


import java.util.List;

/**
 * <br> Description 用于pickerView最基础的共用bean
 * 也可以自己定义其他的bean
 * <br> Author: 谭俊
 * <br> PackageName com.tan.mvpdemo.bean
 * <br> Date: 2018/6/5
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class PickerCardBean{

    /**
     * code : 0
     * msg : 操作成功
     * data : {"returnList":[{"name":"环球车享（广州）","id":48}]}
     */

    private List<ReturnListBean> returnList;

    public List<ReturnListBean> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ReturnListBean> returnList) {
        this.returnList = returnList;
    }


    public static class ReturnListBean  implements IPickerViewBean{
        /**
         * name : 环球车享（广州）
         * id : 48
         */
        private String name;
        private String id;

        public ReturnListBean(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String getTextId() {
            return id;
        }

        @Override
        public String getTextContent() {
            return name;
        }
    }

}
