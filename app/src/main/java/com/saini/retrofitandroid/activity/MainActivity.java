package com.saini.retrofitandroid.activity;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.saini.retrofitandroid.R;
import com.saini.retrofitandroid.interf.RetrofitInterface;
import com.saini.retrofitandroid.model.Student;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private  RetrofitInterface service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButtonArray = (Button) findViewById(R.id.RetrofitArray);
        Button ButtonObject = (Button) findViewById(R.id.RetrofitObject);
        Button ButtonPostStringData = (Button) findViewById(R.id.postStringData);
        Button ButtonPostStringimgData = (Button) findViewById(R.id.postStringimgData);

        ButtonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitArray();
            }
        });

        ButtonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitObject();
            }

        });

        ButtonPostStringData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postStringData();
            }
        });

        ButtonPostStringimgData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postStringandImageData();
            }
        });


    }

    public void getRetrofitArray() {
        Retrofit retrofit = RetrofitInit.getRetroObject("http://www.androidtutorialpoint.com/");
        service = retrofit.create(RetrofitInterface.class);

        Call<List<Student>> call = service.getStudentDetails();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Response<List<Student>> response, Retrofit retrofit) {

                try {
                    List<Student> StudentData = response.body();

                    for (int i = 0; i < StudentData.size()-1; i++) {
                        Log.e("StudentId  :  ", "" + StudentData.get(i).getStudentId());
                        Log.e("StudentName  :  ", "" + StudentData.get(i).getStudentName());
                        Log.e("StudentMarks  :  ", "" + StudentData.get(i).getStudentMarks());
                    }

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }

    public void getRetrofitObject() {
        Retrofit retrofit = RetrofitInit.getRetroObject("http://www.androidtutorialpoint.com/");
        service = retrofit.create(RetrofitInterface.class);

        Call<Student> call = service.getStudentOject();

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                try {
                    Log.e("StudentId  :  ", "" + response.body().getStudentId());
                    Log.e("StudentName  :  ", "" + response.body().getStudentName());
                    Log.e("StudentMarks  :  ", "" + response.body().getStudentMarks());

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }

    private void postStringandImageData() {
        Retrofit retrofit = RetrofitInit.getRetroObject("http://lawzgrid.com/lawyer/CustomerServices/");
        service = retrofit.create(RetrofitInterface.class);

        try {
            String path = "/storage/emulated/0/lawzgrid_croped_images/Image-6391.jpg"; // define your file path here
            File file = new File(path);
            RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);

            Call<ResponseBody> callback = service.updateProfile(fbody,
                    "58afcbd2-3d6c-4b30-8353-6d0468ee5ec4");// file , token
            callback.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                    try {
                        String json = response.body().string();
                        JSONObject jsonObj = new JSONObject(json);
                        Log.d("responce", "" + json);
                        Log.d("message success", "" + (response.raw().code() == 200));
                        Log.d(" json value ", jsonObj.getString("status"));
                    } catch (Exception e) {
                        System.out.print("" + e);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("RESPO", "e");
                }
            });

        } catch (Exception e) {
            System.out.print(""+e);
        }
    }

    private void postStringData() {
        Retrofit retrofit = RetrofitInit.getRetroObject("http://lawzgrid.com/lawyer/CustomerServices/");
        service = retrofit.create(RetrofitInterface.class);

        String device_id = Settings.Secure.getString(this.getContentResolver(),Settings.Secure.ANDROID_ID);
        Call<ResponseBody> callback= service.postData("7836056049","+91","qwer1@",device_id+"8ew1dsf48dsf2d5f45d1f21sdf5","Android");
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    String json=response.body().string();
                    JSONObject jsonObj = new JSONObject(json);
                    Log.d("responce",""+json);
                    Log.d("message success",""+(response.raw().code()== 200));
                    Log.d(" json value ",jsonObj.getString("status"));
                }catch (Exception e){
                    System.out.print(""+e);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d("RESPO","e");
            }
        });

    }

}
