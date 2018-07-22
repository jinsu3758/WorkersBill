package com.example.jinsu.work2.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import com.example.jinsu.work2.util.Dlog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignThread extends Thread {
    private Handler handler;
    private Bitmap sign_bitmap = null;
    private Context context;

    public SignThread(Bitmap bitmap, Context context)
    {
        this.handler = new Handler();
        this.sign_bitmap = bitmap;
        this.context = context;
    }

    public SignThread(Context context, Handler handler)
    {
        this.context = context;
        this.handler = handler;
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

            FileOutputStream out = null;

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
            }
            handler.post(new Runnable() {
                @Override
                public void run() {

                    //이미지 바로 갤러리에 올리는 작업
                   /* Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(file);
                    mediaScanIntent.setData(contentUri);
                    context.sendBroadcast(mediaScanIntent);*/
                    Toast.makeText(context, "download ok", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else
        {

        }
    }

}
