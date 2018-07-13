package com.tan.mvpdemo.common.http;


import com.tan.mvpdemo.bean.gpsInstall.CustIdBean;
import com.tan.mvpdemo.bean.ImgBean;
import com.tan.mvpdemo.bean.PickerCardBean;
import com.tan.mvpdemo.bean.UpdateInfoBean;
import com.tan.mvpdemo.bean.UserInfoLoginBean;
import com.tan.mvpdemo.bean.gpsInstall.FiledBean;
import com.tan.mvpdemo.bean.gpsInstall.GPSBean;
import com.tan.mvpdemo.bean.gpsInstall.ZMXGPSDeviceInfoBean;
import com.tan.mvpdemo.bean.gpsMonitor.GpsMonitor;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by tanjun on 2018/5/31.
 * 请求接口
 */

public interface RequestBiz {

    /** 登录 */
    @POST("/app/enc/login")
    Observable<Result<UserInfoLoginBean>> login(@QueryMap Map<String, String> map);

    /** 版本更新 */
    @POST("/app/version/check")
    Observable<Result<UpdateInfoBean>> checkVersion(@QueryMap Map<String, String> map);

    /** 获取组织信息 */
    @POST("/app/auth/getOrganization")
    Observable<Result<PickerCardBean>> getOrgInfo(@QueryMap Map<String, String> map);

    /** 提交信息 */
    @POST("/app/auth/createCustInfo")
    Observable<Result<CustIdBean>> createCustomerInfo(@QueryMap Map<String,String> map);

    /** 根据Imei 查询信息 */
    @POST("/app/auth/task/getImeiInfo")
    Observable<Result<ZMXGPSDeviceInfoBean>> getDeviceInfo(@QueryMap Map<String, String> map);

    /** GPS安装 设备信息 */
    @POST("/app/auth/detect")
    Observable<Result<Object>> detect(@QueryMap Map<String, String> map);

    /***********************装车位置***************************/
    /** 绑定设备 */
    @POST("/app/auth/task/newBinding")
    Observable<Result<Object>> newBinding(@QueryMap Map<String, String> map);

    /** 查询图片信息 */
    @POST("/app/auth/task/queryAllImgByParams")
    Observable<Result<ImgBean>> queryAllImg(@QueryMap Map<String, String> map);

    /** 图片上传 */
    @Multipart
    @POST()
    Observable<Result<FiledBean>> upLoadImg(@Url String url, @QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> fileMap);

    /** 图片删除 */
    @POST("/app/auth/task/deleteImg")
    Observable<Result<Object>> deleteImg(@QueryMap Map<String, String> map);

    /** 获取gps定位信息 */
    @POST("/app/auth/task/getLocationInfos")
    Observable<Result<GPSBean>> getGpsLocationBean(@QueryMap Map<String, String> map);

    /** 拆除 */
    @POST("/app/auth/task/teardown")
    Observable<Result<Object>> tearDown(@QueryMap Map<String, String> map);

    /** 安装完成 */
    @POST("/app/auth/task/completeTask")
    Observable<Result<Object>> installComplete(@QueryMap Map<String, String> map);

    /** 锁油断电 */
    @POST("/app/auth/instructions")
    Observable<Result<Object>> lockInstructiom(@QueryMap Map<String, String> map);

    @POST("app/auth/getStoreList")
    Observable<Result<GpsMonitor.HomeBean>> getStoreList(@QueryMap Map<String, String> map);

    @POST("/app/auth/mine/logout")
    Observable<Result<Object>> logOut(@QueryMap Map<String, String> map);

    /** GPS监控获取门店下设备列表        @POST("/app/gps/getEquipmentList") */
    @POST("/app/gps/getEquipmentList")
    Observable<Result<GpsMonitor.StoryList>> getEquipmentList(@QueryMap Map<String, String> map);
    /** 超级管管理员GPS监控获取门店下设备列表 */
    @POST("app/auth/getEquipmentListForSuperManager")
    Observable<Result<GpsMonitor.StoryList>> getEquipmentListForSuperManager(@QueryMap Map<String, String> map);

    /*@POST("/app/version/check")
//@Query("verCode") String verCode, @Query("type") String type, @Query("posttime") String posttime,@Query("sign") String sign
    Observable<Result<Version>> check(@QueryMap Map<String, Object> map);

//    @POST("/app/main/base")
//    Observable<Result<Object>> base(@Query("posttime") String posttime,@Query("sign") String sign);

    @POST("/app/auth/main/message")
//@Query("token") String token, @Query("pageIndex") String pageIndex, @Query("posttime") String posttime,@Query("sign") String sign
    Observable<Result<Object>> message(@QueryMap Map<String, Object> map);

    @POST("/app/enc/login")
    Observable<Result<UserInfo>> login(@QueryMap Map<String, Object> map);

    @POST("/app/auth/enc/password/update")
    Observable<Result<Object>> update(@QueryMap Map<String, Object> map);

    @POST("/app/auth/mine/logout")
        //@Query("token") String token, @Query("posttime") String posttime,@Query("sign") String sign
    Observable<Result<Object>> logout(@QueryMap Map<String, Object> map);

//    @POST("/app/auth/loan/queryCustomerInfo")
//    Observable<Result<UserInfo>> queryCustomerInfo(@Query("loanInfoId") String loanInfoId, @Query("token") String token,@Query("posttime") String sign);

    @POST("/app/auth/loan/saveCustomerInfo")
    Observable<Result<LoanInfoID>> saveCustomerInfo(@QueryMap Map<String, Object> map);

//    @POST("/app/auth/loan/queryLoanInfo")
//    Observable<Result<LoanInfo>> queryLoanInfo(@Query("loanInfoId") String loanInfoId, @Query("token") String token,@Query("posttime") String sign);

    @POST("/app/auth/loan/saveLoanInfo")
    Observable<Result<Object>> saveLoanInfo(@QueryMap Map<String, Object> map);

//    @POST("/app/auth/loan/queryCarInfo")
//    Observable<Result<Vehicleinfo>> queryCarInfo(@Query("loanInfoId") String loanInfoId, @Query("token") String token,@Query("sign") String sign);

    @POST("/app/auth/loan/saveCarInfo")
    Observable<Result<Object>> saveCarInfo(@QueryMap Map<String, Object> map);

    @POST("/app/common/getSelectData")
//@Query("posttime") String posttime,@Query("sign") String sign
    Observable<Result<BaseData>> getSelectData(@QueryMap Map<String, Object> map);

    @POST("/app/common/getAreaList")
//@Query("posttime") String posttime,@Query("sign") String sign
    Observable<ResultT<AreaList>> getAreaList(@QueryMap Map<String, Object> map);

    @Multipart
    @POST()
//@Query("token") String token, @Query("loanId") String loanId, @Query("posttime") String posttime,@Query("sign") String sign
    Observable<Result<CountTotal>> imgUpload(@Url String url, @QueryMap Map<String, Object> map,
                                             @PartMap() Map<String, RequestBody> params);

//    @POST("/app/auth/image/imgReadAll")
//    Observable<Result<List<ImageInfo>>> imgReadAll(@Query("token") String token, @Query("loanId") String loanId, @Query("posttime") String posttime,@Query("sign") String sign);

    //@POST("/app/auth/loan/infolist")
    @POST("/app/auth/queryLoanList")
        //@Query("token") String token, @Query("pageIndex") String pageIndex, @Query("queryParameter") String queryParameter, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<OrderInfo>> infolist(@QueryMap Map<String, Object> map);

    //@POST("/app/auth/loan/loandetail")
    @POST("/app/auth/businessdetail")
//@Query("token") String token, @Query("orderNo") String orderNo, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<OrderDefultInfo>> loandetail(@QueryMap Map<String, Object> map);

    @POST("/app/auth/messageList")
//@Query("token") String token, @Query("pageIndex") String pageIndex, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<MessageList>> messageList(@QueryMap Map<String, Object> map);

    @POST("/app/auth/queryApvProductInf")
    //@POST("/app/auth/common/getProductInfoList")
    //@Query("token") String token, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<ProductInfoList>> getProductInfoList(@QueryMap Map<String, Object> map);

    @POST("/app/auth/main/mainInfo")
    Observable<Result<CountTotal>> mainInfo(@QueryMap Map<String, Object> map);

    @Streaming
    @GET
    Call<ResponseBody> downAPK(@Url String url);

    @POST("/app/auth/workflowStart")
        //@Query("token") String token, @Query("loanId") String loanId, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<Object>> syncAppData(@QueryMap Map<String, Object> map);

    //@POST("/app/auth/image/deleteFile")
    @POST("/app/auth/image/newDeleteImg")
        //@Query("token") String token, @Query("name") String name, @Query("loanId") String loanId, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<Object>> deleteFile(@QueryMap Map<String, Object> map);

    @POST("/app/auth/readImageSample")
        //@Query("token") String token, @Query("posttime") String posttime, @Query("sign") String sign
    Observable<Result<Information>> readImageSample(@QueryMap Map<String, Object> map);

    @POST("/app/auth/getJingZhenGuData")
    Observable<Result<JsonArray>> getJingZhenGuData(@QueryMap Map<String, Object> map);

    @POST("/app/auth/getJingZhenGuObjectData")
    Observable<Result<JingZhengu>> getJingZhenGuObjectData(@QueryMap Map<String, Object> map);

    //@POST("/app/auth/loan/recordLoan")
    @POST("/app/auth/submissionAssessment")
    Observable<Result<LoanInfoID>> recordLoan(@QueryMap Map<String, Object> map);

    //@POST("/app/auth/loan/customerInformationInput")
    @POST("/app/auth/saveCustInfo")
    Observable<Result<LoanInfoID>> customerInformationInput(@QueryMap Map<String, Object> map);

    @POST("/app/auth/saveSupplyInformation")
    Observable<Result<LoanInfoID>> loanAndCarMessageInput(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/getAssessmentTask")
    Observable<Result<Task>> getAssessmentTask(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/saveAssessmentData")
    Observable<Result<Object>> saveAssessmentData(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/evaluateInformationDetails")
    Observable<Result<CarData>> evaluateInformationDetails(@QueryMap Map<String, Object> map);


    //============================GPS=======================================================================================

    @POST("/app/gps/getStoreList")
    Observable<Result<PurpleStar>> getStoreList(@QueryMap Map<String, Object> map);

    @POST("/app/gps/getEquipmentList")
    Observable<Result<Container>> getEquipmentList(@QueryMap Map<String, Object> map);


    @POST("/app/gps/getEquipmentListForSuperManager")
    Observable<Result<Container>> getEquipmentListForSuperManager(@QueryMap Map<String, Object> map);

    @POST("/app/gps/getAlarmList")
    Observable<Result<Container>> getAlarmList(@QueryMap Map<String, Object> map);

    @POST("/app/gps/getAlarmScreeningList")
    Observable<Result<AlarmScreen>> getAlarmScreeningList(@QueryMap Map<String, Object> map);

    @POST("/app/gps/getLocationInfo")
    Observable<Result<GPS>> getLocationInfo(@QueryMap Map<String, Object> map);

    @POST("/app/gps/getTrackPlayback")
    Observable<Result<RecorShow>> getTrackPlayback(@QueryMap Map<String, Object> map);

    @POST("/app/gps/queryAllUser")
    Observable<Result<DeviceLocation>> queryAllUser(@QueryMap Map<String, Object> map);

    @POST("/app/gps/equipmentAuthorize")
    Observable<Result<Object>> equipmentAuthorize(@QueryMap Map<String, Object> map);

    @POST("/app/gps/updateUploadTime")
    Observable<Result<Object>> updateUploadTimek(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/queryTaskListData")
    Observable<Result<GPSInstall>> queryTaskListData(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/queryGpsCarInfo")
    Observable<Result<CarInfo>> queryGpsCarInfo(@QueryMap Map<String, Object> map);

    *//**
     * 设备信息
     * @param map
     * @return
     *//*
    @POST("/app/auth/task/getImeiInfo")
    Observable<Result<DeviceInfo>> getImeiInfo(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/binding")
    Observable<Result<Object>> binding(@QueryMap Map<String, Object> map);

    @POST("/app/gps/detect")
    Observable<Result<Object>> detect(@QueryMap Map<String, Object> map);



    *//**
     * 继续安装
     *//*
    @POST("/app/auth/task/newBinding")
    Observable<Result<GPS>> newBinding(@QueryMap Map<String, Object> map);

    *//**
     * 获取多车定位信息参数
     *//*
    @POST("/app/auth/task/getLocationInfos")
    Observable<Result<GPS>> getLocationInfos (@QueryMap Map<String, Object> map);

    @POST("/app/gps/queryCompanyRelUser")
    Observable<Result<GroupItem>> queryCompanyRelUser(@QueryMap Map<String, Object> map);

    *//**
     * 提交授权
     * @param map
     * @return
     *//*
    @POST("/app/gps/storeAuthorize")
    Observable<Result<Object>> storeAuthorize(@QueryMap Map<String, Object> map);

    *//**
     * 锁油/断电
     * @return
     *//*
    @POST("/app/gps/instructions")
    Observable<Result<Object>> instructions(@QueryMap Map<String, Object> map);

    *//**
     * 合同、合同流水号  入库
     * @param map
     * @return
     *//*
    @POST("/app/auth/loan/SynCustSerialNumber")
    Observable<Result<Object>> SynCustSerialNumber(@QueryMap Map<String, Object> map);

    *//**
     * 根据车牌号  和  客户名称  查询合同号
     * @param map
     * @return
     *//*
    @POST("/app/auth/loan/queryContractNo")
    Observable<Result<ContractBean>> queryContractNo(@QueryMap Map<String, Object> map);


    @Multipart
    @POST()
    Observable<Result<Object>> uploadImg(@Url String url, @QueryMap Map<String, Object> map,
                                         @PartMap() Map<String, RequestBody> params);

    @POST("/app/auth/task/deleteImg")
    Observable<Result<Object>> deleteImg(@QueryMap Map<String, Object> map);


    @POST("/app/auth/task/queryAllImgByParams")
    Observable<Result<Loading>> queryAllImgByParams(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/queryMortgageInfo")
    Observable<Result<VehicleEvaluation>> queryMortgageInfo(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/updateImeiInfo")
    Observable<Result<ImeiInfo>> updateImeiInfo(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/teardown")
    Observable<Result<Object>> teardown(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/completeTask")
    Observable<Result<Object>> completeTask(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/updateLoanAudiByBussinessId")
    Observable<Result<Object>> updateLoanAudiByBussinessId(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/queryEvalCarInfo")
    Observable<Result<com.hxyd.dyt.appraiser.modle.entity.CarInfo>> queryEvalCarInfo(@QueryMap Map<String, Object> map);

    @POST("/app/auth/task/updateEvalCarInfo")
    Observable<Result<Object>> updateEvalCarInfo(@QueryMap Map<String, Object> map);

    @POST( "/app/auth/task/saveCarEvaluation")
    Observable<Result<Object>> saveCarEvaluation(@QueryMap Map<String, Object> map);*/

}
