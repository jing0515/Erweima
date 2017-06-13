package com.bawei.lvwenjing.erweima;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;


//http://blog.csdn.net/zhengxiaoyao0716/article/details/50129437
public class ZxingActivity extends Activity {

    private TextView textView;

    private ImageView qrImgImageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);

        //打开扫描界面扫描条形码或二维码

        textView = (TextView) findViewById(R.id.result_button_view);

        qrImgImageView = (ImageView)findViewById(R.id.gen_id);



    }


    //扫描
    public void scannner(View view){
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    public void gener_pic(View view){
        String contentString = "11111";
        if (!contentString.equals("")) {
            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,
                            BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            qrImgImageView.setImageBitmap(qrCodeBitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            textView.setText(scanResult);
        }
    }

}
