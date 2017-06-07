package why.com.rxjavademo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by diannao on 2017/6/5.
 */

public interface GitHubApi {

    //    url  =  http://api.sit.hsb1234.com/housekeeping/userc/v2/serviceCategories
//    @GET("housekeeping/userc/v2/serviceCategories")
//    Call<ResponseBody> getUrl();
    @GET("housekeeping/userc/v2/serviceCategories")
    Observable<ResponseBody> getUrl();

    @Multipart //上传单个文件
    @POST("upload")
    Call<ResponseBody> uploadFile(@Part("description") RequestBody description, MultipartBody.Part file);

    @Multipart //上传多个文件
    @POST("upload")
    Call<ResponseBody> uploadFiles(@Part("description") ResponseBody description, MultipartBody.Part file1, MultipartBody.Part file2);

}
