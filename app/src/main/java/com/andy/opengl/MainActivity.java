package com.andy.opengl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andy.opengl.demo.game.hero.GameHeroActivity;
import com.andy.opengl.demo.game.plane.GamePlaneActivity;
import com.andy.opengl.demo.picture.PictureActivity;
import com.andy.opengl.demo.rotate.RotateTriangleActivity;
import com.andy.opengl.demo.shot.ShotActivity;
import com.andy.opengl.demo.triangle.TriangleActivity;
import com.andy.opengl.demo.videoplayer.VideoPlayerActivity;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    // 如果加了权限，记得在这里增加
    private final static String[] REQUEST_PERMISSION_ARRAY = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

    /**
     * 注意：每次加一个例子就往这里加String和跳转的Activity就行，其他代码基本可以不用动 start
     **/
    private String[] mDataList = new String[]{
            "简单图形",
            "旋转三角形",
            "拍摄",
            "播放视频（内空）",
            "图片demo",
            "小飞机游戏",
            "积木超人游戏"
    };

    private Class<?>[] mActivityClassList = new Class<?>[]{
            TriangleActivity.class,
            RotateTriangleActivity.class,
            ShotActivity.class,
            VideoPlayerActivity.class,
            PictureActivity.class,
            GamePlaneActivity.class,
            GameHeroActivity.class
    };
    /** 每次加一个例子就往这里加String和跳转的Activity就行，其他代码基本可以不用动 end **/


    /******************************************************************  万恶的分割线  ***********************************************************************************************************/


    /**
     * 以下代码杀伤力较强，请勿触摸，以免受伤！！ start
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPermissions();
    }

    @Override
    protected boolean isShowBackIcon() {
        return false;
    }

    private void initView() {
        ListView mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mDataList));
        mListView.setOnItemClickListener(this);
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean needPermission = false;
            for (String permission : REQUEST_PERMISSION_ARRAY) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    needPermission = true;
                    break;
                }
            }

            if (needPermission) {
                ActivityCompat.requestPermissions(this,
                        REQUEST_PERMISSION_ARRAY,
                        0);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, mActivityClassList[position]));
    }

    /** 以下代码杀伤力较强，请勿触摸，以免受伤！！ end **/
}
