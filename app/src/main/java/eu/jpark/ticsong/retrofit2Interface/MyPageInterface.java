package eu.jpark.ticsong.retrofit2Interface;

import eu.jpark.ticsong.DTO.MyPageDTO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * Created by Daesub Kim on 2016-07-31.
 */
public interface MyPageInterface {

    @FormUrlEncoded
    @POST("myPage.do")
    Call<MyPageDTO> getMyPage(
            @Field("service") String service,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("myPage.do")
    Call<MyPageDTO> insertDefaultMyPage(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("color") String color,
            @Field("acc") String acc
    );

    @FormUrlEncoded
    @POST("myPage.do")
    Call<MyPageDTO> insertMyPage(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("color") String color,
            @Field("acc") String acc
    );
    @FormUrlEncoded
    @POST("myPage.do")
    Call<MyPageDTO> updateColor(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("color") String color,
            @Field("acc") String acc
    );
    @FormUrlEncoded
    @POST("myPage.do")
    Call<MyPageDTO> updateAcc(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("color") String color,
            @Field("acc") String acc
    );





}