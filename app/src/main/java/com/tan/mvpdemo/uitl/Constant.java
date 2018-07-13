package com.tan.mvpdemo.uitl;


import com.tan.mvpdemo.BaseApplication;

/**
 * Created by tanjun on 2018/5/2.
 * 常量类
 */

public class Constant {

    /** 是否显示日志 */
    public static final boolean IS_SHOW_LOG = true;

    public static final String LOG_TAG = "tanjun";

    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";

    /**
     * 普通图片压缩裁剪后的后缀
     */
    public static final String IMAGE_LOGO_COMPRESS = "_compress+new.";

    private static final String CONSTANT_KEY_BASE = BaseApplication.getInstance().getApplicationContext().getPackageName() + ".";

    /** sp文件名---用户 */
    public static final String SP_KEY_FILE_USER_INFO = "sp_key_file_user_info";

    /** sp保存在data/data目录下的文件名 */
    public static final String SP_FILE_NAME = CONSTANT_KEY_BASE + ".spData";

    /** posttimeKey请求发送时间 */
    public static final String SERVER_POST_TIME_KEY = "posttime";

    /** signKey :40位的SHA签名 */
    public static final String SERVER_SIGN_KEY = "sign";

    public static final int CONNECT_TIME_OUT = 15000;//超时时间

    public static final String queryLoanList = "/app/auth/queryLoanList";
    public static final String queryMortgageInfo = "/app/auth/task/queryMortgageInfo";
    public static final String generateRequestNo = "/app/auth/task/generateRequestNo";
    public static final String updateEvalCarInfo = "/app/auth/task/updateEvalCarInfo";
    public static final String workflowClaim = "/app/auth/task/workflowClaim"; // 签收 未签收
    //public static final String imgUpload = "/app/auth/image/imgUpload"; //门店初审 old
    public static final String imgUpload = "/app/auth/image/newUploadImg"; //门店初审

    public static final String uploadImg = "/app/auth/task/uploadImg";

    public static final String rescissionMortgageAppl = "/app/auth/task/rescissionMortgageAppl";

    public static final String queryRescissionMortgageList =  "/app/auth/task/queryRescissionMortgageList";

    public static final String completeTask = "/app/auth/task/completeTask";

    public static final String teardown = "/app/auth/task/teardown";

    public static final String detect = "/app/gps/detect";

    public static final String newBinding = "/app/auth/task/newBinding";

    public static final String getLocationInfos = "/app/auth/task/getLocationInfos";

    public static final String SynCustSerialNumber = "/app/auth/loan/SynCustSerialNumber";

    public static final String queryContractNo = "/app/auth/loan/queryContractNo";

    public static final String storeAuthorize =  "/app/gps/storeAuthorize";

    public static final String GROUPURL = "/app/gps/queryCompanyRelUser";//分组授权

    public static final String LOCKOIL = "/app/gps/instructions"; //锁油 断电

    public static final String queryApvProductInf = "/app/auth/queryApvProductInf";

    //public static final String PRODURL = "http://192.168.6.218:8055"; //开发

    public static final String BASE_URL = "http://192.168.5.68:50400"; //测试内网
    //public static final String BASE_URL = "http://192.168.5.16:9004"; //测试
    //public static final String BASE_URL = "http://192.168.5.70:9004"; //集成开发环境
    //public static final String BASE_URL = "http://dytapi-test.p2phx.com:19004"; //外网测试
    //public static final String BASE_URL ="http://test_dyt-platapi.zimilbs.com:50400"; //zmx外网测试
    //public static final String BASE_URL = "http://172.16.20.156:50400/"; //俊焕给的地址
    public static final String METHOD_UPLOADIMG = "/app/auth/task/uploadImg"; //图片上传
    //public static final String BASE_URL = "http://api.daiyetong.com"; //测试

    //public static final String BASE_URL = "http://api.daiyetong.com";//生产

    public static final String SIGN_KEY = "aFaj81aXawkj8XNOF3GFCUn2q903LN8U";

    public static final String DES_KEY = "abc45678901234567890ABCD";

    public static final String SH_NAME = "DYT_APP_SH";

    public static final String PLATFORM = "ANDROID";

    public static final String CHANNEL = "dyt";

    public static final String USER_ID = "userID";

    private static final String TOKEN = "DYT_TOKEN";

    public static final String SIGN_MAP_KEY = "sign";

    public static final String POSTTIME_MAP_KEY = "posttime";

    public static final String TOKEN_MAP_KEY = "token";

    private static final String USER_ID_KEY = "userID";

    private static final String ALIAS = "Alias";

    private static final String BASEDATA_VERSION = "BaseDataVersion";

    private static final String PRODUCTINFOLIST_VERSION = "ProductInfoListVersion";

    private static final String AREALIST_VERSION = "AreaListVersion";

    private static final String JPUSH_MESSAGE = "jpush_message";

    public static final String PRODUCT_TYPE = "product_type";

    public static final int CACHE_SIZE = 10;

    public static final String APK_NAME = "dyt";

    public static final String APK_SUFFIX = ".apk";

    public static final String CACHE_CATALOG_NAME = "dyt";


    public static String getToken() {
        return (String) PreferencesUtil.get( TOKEN, "");
    }

    public static void setToken(String token) {
        PreferencesUtil.put( TOKEN, token);
    }

    public static String getAlias() {
        return (String) PreferencesUtil.get( ALIAS, "");
    }

    public static void setAlias(String alias) {
        PreferencesUtil.put( ALIAS, alias);
    }

    public static String getUserID() {
        return (String) PreferencesUtil.get( USER_ID_KEY, "");
    }

    public static void setUserID(String userID) {
        PreferencesUtil.put(USER_ID_KEY, userID);
    }

    public static String getBaseDataVersion() {
        return (String) PreferencesUtil.get(BASEDATA_VERSION, "");
    }

    public static void setBaseDataVersion(String version) {
        PreferencesUtil.put(BASEDATA_VERSION, version);
    }

    public static String getProductInfoListVersion() {
        return (String) PreferencesUtil.get(PRODUCTINFOLIST_VERSION, "");
    }

    public static void setProductInfoListVersion(String version) {
        PreferencesUtil.put(PRODUCTINFOLIST_VERSION, version);
    }

    public static String getAreaListVersion() {
        return (String) PreferencesUtil.get(AREALIST_VERSION, "");
    }

    public static void setAreaListVersion(String version) {
        PreferencesUtil.put(AREALIST_VERSION, version);
    }

    public static void setMessage(boolean is) {
        PreferencesUtil.put(JPUSH_MESSAGE, is);
    }

    public static boolean getMessage() {
        return (boolean) PreferencesUtil.get(JPUSH_MESSAGE, false);
    }

    // ******************************图片****************************//
    /** 图片相关 */
    public static final int positionImages =1;// gps安装 装车位置
    public static final int materialsImages =2;//
    public static final int contractImages =3;// 车辆抵押
    public static final int carImages =4;//  车辆评估
    public static final int vehicleUnbindMortgage = 5; // 车辆解押

    /** groupId */
    public static final String GROUPID_POSITIONIMAGES = "positionImages"; // gps安装  装车位置
    public static final String GROUPID_MATERIALSIMAGES = "materialsImages"; //
    public static final String GROUPID_CONTRACTIMAGES = "hengxinContractInformation"; // 车辆抵押
    public static final String GROUPID_CARIMAGES = "carImages"; // 车辆评估
    public static final String GROUPID_VEHICLEUNBINDMORTGAGE = "VehicleUnbindMortgage"; //车辆解押

}
