package com.example.jinsu.work2.network;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;

import com.example.jinsu.work2.common.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class SignThread extends Thread {
    private Handler handler;
    private Bitmap sign_bitmap = null;
    private String img;
    private Callback callback;

    public SignThread(Bitmap bitmap, Callback call)
    {
        this.callback = call;
        this.handler = new Handler();
        this.sign_bitmap = bitmap;
    }

    public interface Callback
    {
        void finish();
    }
    @Override
    public void run() {
        if (sign_bitmap != null) {
            File file = new File(Environment.getExternalStorageDirectory() + "/sign.png");

            //배경 투명 설정
            int width = sign_bitmap.getWidth();
            int height = sign_bitmap.getHeight();
            int[] pixel = new int[width * height];
            sign_bitmap.getPixels(pixel, 0, width, 0, 0, width, height);
            for (int i = 0; i < pixel.length; i++) {
                if (pixel[i] != Color.BLACK)
                {
                    pixel[i] = Color.TRANSPARENT;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(pixel, 0, width, width, height,
                    Bitmap.Config.ARGB_8888);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG , 100, out);
            byte[] bytes = out.toByteArray();
            img = Base64.encodeToString(bytes, 0);
            BaseApplication.join.signature = img;

           /* FileOutputStream out = null;

            try {
                file.createNewFile();
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                //bitmap = 갤러리또는 리소스에서 불러온 비트맵 파일에 포맷
                Dlog.d("서명저장 성공");
                out.close();

            } catch (IOException e) {
                Dlog.d("서명저장 실패" + e.getMessage());
                e.printStackTrace();
            }*/
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.finish();
                    //이미지 바로 갤러리에 올리는 작업
                   /* Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(file);
                    mediaScanIntent.setData(contentUri);
                    context.sendBroadcast(mediaScanIntent);*/

                }
            });
        }
        else
        {

        }
    }

}
