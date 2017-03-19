package mac.yk.devicemanagement.net;

import mac.yk.devicemanagement.I;
import mac.yk.devicemanagement.bean.Device;
import mac.yk.devicemanagement.bean.Result;
import mac.yk.devicemanagement.bean.Scrap;
import mac.yk.devicemanagement.bean.Weixiu;
import mac.yk.devicemanagement.bean.Xunjian;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mac-yk on 2017/3/18.
 */

public interface ServerAPI {
    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.YUJING)
    Observable<Result<String>> getyujing();

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.TONGJI)
    Observable<Result> getTongji(@Query(I.TABLENAME) String tableName);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.CHAXUN)
    Observable<Result> chaxun(@Query(I.REQUEST.PARAM) String requset,@Query(I.DEVICE.DID) String Did);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.SAVE)
    Observable<Result> saveDevice(@Query(I.USER.NAME) String name,@Query(I.DEVICE.TABLENAME) Device device);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.LOGIN)
    Observable<Result> login(@Query(I.USER.NAME) String name,@Query(I.USER.PASSWD) String passwd);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.LOGOUT)
    Observable<Result> logOut(@Query(I.USER.NAME) String name);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.DOWNWEIXIU)
    Observable<Weixiu[]> downLoadWeixiu(String request,@Query(I.DEVICE.DID) String did, @Query(I.DOWNLOAD.PAGE) int page, @Query(I.DOWNLOAD.SIZE) int size);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.DOWNXUNJIAN)
    Observable<Xunjian[]> downloadXunJian (@Query(I.DEVICE.DID) String did, @Query(I.DOWNLOAD.PAGE) int page,
                                           @Query(I.DOWNLOAD.SIZE) int size);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.XUNJIAN)
    Observable<Result> xunjian(@Query(I.DEVICE.ISDIANCHI) boolean isdianchi,@Query(I.USER.NAME) String userName
            ,@Query(I.DEVICE.DID) String did,
                               @Query(I.XUNJIAN.STATUS) String status,@Query(I.XUNJIAN.REMARK) String remark);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.XIUJUN)
    Observable<Result> xiujun(@Query(I.DEVICE.ISDIANCHI) boolean isdianchi,@Query(I.USER.NAME) String userName
            ,@Query(I.DEVICE.DID) String did, @Query(I.WEIXIU.TRANSLATE) boolean translate,@Query(I.WEIXIU.REMARK) String remark);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.BAOFEI)
    Observable<Result> baofei(@Query(I.USER.NAME) String name,@Query(I.BAOFEI.DNAME) String Dname
            ,@Query(I.BAOFEI.DID) String Did,@Query(I.BAOFEI.REMARK) String remark);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.CONTROL)
    Observable<Result> control(@Query(I.DEVICE.ISDIANCHI)boolean isDianchi,@Query(I.DEVICE.STATUS) int Cid,@Query(I.DEVICE.DID) String Did);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.YONGHOU)
    Observable<Result> yonghou(@Query(I.DEVICE.DID) String Did);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.DOWNSCRAP)
    Observable<Scrap[]> downScrap(@Query(I.DOWNLOAD.PAGE) int page,@Query(I.DOWNLOAD.SIZE) int size);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.DOWNDEVICE)
    Observable<Device[]> downDevice(@Query(I.DOWNLOAD.PAGE) int page,@Query(I.DOWNLOAD.SIZE) int size);

    @GET(I.REQUEST.PATH+"?request="+I.REQUEST.YONGHOU)
    Observable<Result> getCount(@Query(I.DEVICE.DNAME )int dName,@Query(I.PIC.TYPE) String type);
}
