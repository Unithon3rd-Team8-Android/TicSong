package eu.jpark.ticsong.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import eu.jpark.ticsong.DTO.ScoreDTO;
import eu.jpark.ticsong.DTO.UserDTO;
import eu.jpark.ticsong.MainActivity;
import eu.jpark.ticsong.StaticInfo;
import eu.jpark.ticsong.model.CustomPreference;
import eu.jpark.ticsong.retrofit2Interface.LoginInterface;
import eu.jpark.ticsong.retrofit2Interface.RegisterInterface;
import eu.jpark.ticsong.retrofit2Interface.ScoreInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daesub Kim on 2016-07-31.
 */
public class ScoreController {

    private static ScoreController scoreController;

    private Retrofit retrofit = null;
    /**a
     * 성공 했는지 아닌지
     */
    private boolean isSuccess=false;

    public boolean getIsSuccess(){return this.isSuccess;}

    static {
        scoreController = new ScoreController();
    }

    public static ScoreController getInstance() {
        if(scoreController==null)
            scoreController= new ScoreController();
        return scoreController;
    }


    public boolean getScore(final AppCompatActivity activity, String userId) {
        /*
        원래코드

        아래는 바뀐코드이다.
        Retrofit 생성하는 부분을 랩해놓은 부분. 근데 없어도 될듯??조금더 생각해보자.*/
        //retrofit = RetrofitCreator.getRetrofit(LOGIN_URL);
        retrofit = new Retrofit.Builder().baseUrl(StaticInfo.TICSONG_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        /*요청보낼 interface 객체 생성.*/
        ScoreInterface scoreInterface = retrofit.create(ScoreInterface.class);

        /*서버로 요청을 보낼 객체생성.*/
        Call<ScoreDTO> call = scoreInterface.getScore("get", userId);

        /*Call은 동기화 클래스이다.
        * 한번 요청을 보낸 다음, 재 요청을 보낼 경우 에러가 발생한다.
        * 그렇기 때문에 값싼 clone()메소드를 호출하여 복사하고,
        * 복사한 Call객체로 요청큐에 넣는다. */
        call.clone().enqueue(new Callback<ScoreDTO>() {
            @Override
            public void onResponse(Call<ScoreDTO> call, Response<ScoreDTO> response) {
                /* 응답코드가 200번대가 아니라면*/
                if(!response.isSuccess()) {
                    Log.d("로그인 코드_",response.body().getResultCode()+"");
                    return ; // 아무 코드를 실행하지 않고 리턴.
                }
                Log.d("로그인_성공코드 -", response.code() + ""); // 디버깅용

                ScoreDTO scoreDTO = response.body();
                if(scoreDTO.getResultCode().equals("0")) {
                    Log.e("Score Get 실패 ", response.code() + "");
                    return;
                }

                Log.e("Score Get 성공 ", scoreDTO.getResultCode() + "");

                CustomPreference customPreference = CustomPreference.getInstance(activity);

                customPreference.put("userId",scoreDTO.getUserId());
                customPreference.put("score",scoreDTO.getScore());
                customPreference.put("userLevel",scoreDTO.getUserId());


                /*CustomPreference customPreference = CustomPreference.getInstance(activity);

                customPreference.put("userId",userDTO.getUserId());
                customPreference.put("name",userDTO.getName());
                customPreference.put("login",true);
                Log.e("login_login","true");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.startActivity(new Intent(activity, MainActivity.class));
                            }
                        });
                    }
                }).start();*/
            }

            @Override
            public void onFailure(Call<ScoreDTO> call, Throwable t) {
                isSuccess = false;
                Log.d("로그인_실패코드-",call.toString()+"__"+t.getMessage());
                Log.d("로그인_왜실패?",t.toString());
            }
        });
        return isSuccess;
    }


    public boolean insertScore(final AppCompatActivity activity, String userId, String score, String userLevel) {
        /*
        원래코드

        아래는 바뀐코드이다.
        Retrofit 생성하는 부분을 랩해놓은 부분. 근데 없어도 될듯??조금더 생각해보자.*/
        //retrofit = RetrofitCreator.getRetrofit(LOGIN_URL);
        retrofit = new Retrofit.Builder().baseUrl(StaticInfo.TICSONG_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        /*요청보낼 interface 객체 생성.*/
        ScoreInterface scoreInterface = retrofit.create(ScoreInterface.class);

        /*서버로 요청을 보낼 객체생성.*/
        Call<ScoreDTO> call = scoreInterface.insertScore("insert", userId, score, userLevel);

        /*Call은 동기화 클래스이다.
        * 한번 요청을 보낸 다음, 재 요청을 보낼 경우 에러가 발생한다.
        * 그렇기 때문에 값싼 clone()메소드를 호출하여 복사하고,
        * 복사한 Call객체로 요청큐에 넣는다. */
        call.clone().enqueue(new Callback<ScoreDTO>() {
            @Override
            public void onResponse(Call<ScoreDTO> call, Response<ScoreDTO> response) {
                /* 응답코드가 200번대가 아니라면*/
                if(!response.isSuccess()) {
                    Log.d("로그인 코드_",response.body().getResultCode()+"");
                    return ; // 아무 코드를 실행하지 않고 리턴.
                }
                Log.d("로그인_성공코드 -", response.code() + ""); // 디버깅용

                ScoreDTO scoreDTO = response.body();
                if(scoreDTO.getResultCode().equals("0")) {

                    // 이미 가입한 회원.
                    /*LoginController loginController = LoginController.getInstance();
                    loginController.requestLogin(activity, "123123", "Daesub");*/

                    return;
                }

                Log.e("Score Insert 성공 ", scoreDTO.getResultCode() + "");


                /*CustomPreference customPreference = CustomPreference.getInstance(activity);

                customPreference.put("userId",scoreDTO.getUserId());
                customPreference.put("score",scoreDTO.());
                customPreference.put("Register",true);
                Log.e("Register","true");*/
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.startActivity(new Intent(activity, MainActivity.class));
                            }
                        });
                    }
                }).start();*/
            }

            @Override
            public void onFailure(Call<ScoreDTO> call, Throwable t) {
                isSuccess = false;
                Log.d("로그인_실패코드-",call.toString()+"__"+t.getMessage());
                Log.d("로그인_왜실패?",t.toString());
            }
        });
        return isSuccess;
    }


    public boolean updateScore(final AppCompatActivity activity, String userId, String score, String userLevel) {
        /*
        원래코드

        아래는 바뀐코드이다.
        Retrofit 생성하는 부분을 랩해놓은 부분. 근데 없어도 될듯??조금더 생각해보자.*/
        //retrofit = RetrofitCreator.getRetrofit(LOGIN_URL);
        retrofit = new Retrofit.Builder().baseUrl(StaticInfo.TICSONG_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        /*요청보낼 interface 객체 생성.*/
        ScoreInterface scoreInterface = retrofit.create(ScoreInterface.class);

        /*서버로 요청을 보낼 객체생성.*/
        Call<ScoreDTO> call = scoreInterface.updateScore("update", userId, score, userLevel);

        /*Call은 동기화 클래스이다.
        * 한번 요청을 보낸 다음, 재 요청을 보낼 경우 에러가 발생한다.
        * 그렇기 때문에 값싼 clone()메소드를 호출하여 복사하고,
        * 복사한 Call객체로 요청큐에 넣는다. */
        call.clone().enqueue(new Callback<ScoreDTO>() {
            @Override
            public void onResponse(Call<ScoreDTO> call, Response<ScoreDTO> response) {
                /* 응답코드가 200번대가 아니라면*/
                if(!response.isSuccess()) {
                    Log.d("로그인 코드_",response.body().getResultCode()+"");
                    return ; // 아무 코드를 실행하지 않고 리턴.
                }
                Log.d("로그인_성공코드 -", response.code() + ""); // 디버깅용

                ScoreDTO scoreDTO = response.body();
                if(scoreDTO.getResultCode().equals("0")) {



                    return;
                }

                Log.e("Score Update 성공 ", scoreDTO.getResultCode() + "");


                /*CustomPreference customPreference = CustomPreference.getInstance(activity);

                customPreference.put("userId",scoreDTO.getUserId());
                customPreference.put("score",scoreDTO.());
                customPreference.put("Register",true);
                Log.e("Register","true");*/
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.startActivity(new Intent(activity, MainActivity.class));
                            }
                        });
                    }
                }).start();*/
            }

            @Override
            public void onFailure(Call<ScoreDTO> call, Throwable t) {
                isSuccess = false;
                Log.d("로그인_실패코드-",call.toString()+"__"+t.getMessage());
                Log.d("로그인_왜실패?",t.toString());
            }
        });
        return isSuccess;
    }



}
