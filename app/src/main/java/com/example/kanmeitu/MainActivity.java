package com.example.kanmeitu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kanmeitu.activity.BaseActivity;
import com.example.kanmeitu.activity.ImageDetailActivity;
import com.example.kanmeitu.activity.LoginActivity;
import com.example.kanmeitu.adapter.ImageAdapter;
import com.example.kanmeitu.adapter.response.ListResponse;
import com.example.kanmeitu.api.Api;
import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.util.Constants;
import com.example.kanmeitu.util.SharedPreferencesUtil;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       RecyclerView rv =findViewById(R.id.rv);
       rv.setHasFixedSize(true);
      GridLayoutManager layoutManager= new GridLayoutManager(this,2);
       rv.setLayoutManager(layoutManager);
//        ArrayList<Image> datas = new ArrayList<>();
//        for (int i=1;i<10;i++) {
//            datas.add(new Image(String.format("http://dev-courses-quick.oss-cn-beijing.aliyuncs.com/%d.jpg",i)));
//        }
        adapter=new ImageAdapter(this);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(MainActivity.this,"click"+position,Toast.LENGTH_SHORT).show();
                Image data=adapter.getData(position);
                Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);
                intent.putExtra(Constants.ID,data.getUri());
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
//        adapter.setData(datas);
        fetchData();
    }
    private void fetchData(){
        Api
                .getInstance()
                .images()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponse<Image>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListResponse<Image> imageListResponse) {

                        adapter.setData(imageListResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                    e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onLogoutClick(View view) {
        sp.setLogin(false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
