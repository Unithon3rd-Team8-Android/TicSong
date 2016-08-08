package eu.jpark.ticsong.retrofit2Interface;

import eu.jpark.ticsong.DTO.ScoreDTO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * Created by Daesub Kim on 2016-07-31.
 */
public interface ScoreInterface {

    @FormUrlEncoded
    @POST("score.do")
    Call<ScoreDTO> getScore(
            @Field("service") String service,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("score.do")
    Call<ScoreDTO> insertScore(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("score") String score,
            @Field("userLevel") String userLevel
    );

    @FormUrlEncoded
    @POST("score.do")
    Call<ScoreDTO> updateScore(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("score") String score,
            @Field("userLevel") String userLevel
    );

}
