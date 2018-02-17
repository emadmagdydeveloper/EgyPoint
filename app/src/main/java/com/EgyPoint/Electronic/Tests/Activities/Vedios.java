package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.EgyPoint.Electronic.Tests.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


public class Vedios extends AppCompatActivity {


    private ListView mVideosListView;
    private List<String> mVideosList = new ArrayList<>();
    private  List <String> videosLink=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedios);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"JannaLT-Regular.ttf",true);
        mVideosList.add("نشر الاختبار الاكتروني");
        mVideosList.add("ضبط نموزج الاختبار الالكتروني");
        mVideosList.add("ضبط خصائص الاختبار");
        mVideosList.add("تنسيق الاساله");
        mVideosList.add("واجهه الاختبار");
        mVideosList.add("تحميل برنامج الاختبارات الالكترونى");
        mVideosList.add("تثبيت برنامج الاختبارات الالكترونى");
        mVideosList.add("انشاء اختبار الكترونى جديد");
        mVideosList.add("اضافه اختبار من نوع صح وخطا");
        mVideosList.add("اضافه اختبار من نوع بنك الكلمات");
        mVideosList.add("اضافة اختبار من نوع املئ الفراغ التالى");
        mVideosList.add("اضافه اختبار من نوع رتب القائمه المنسدله");
        mVideosList.add("اضافه اختبار من نوع الترتيب");
        mVideosList.add("اضافه اختبار من نوع اختيار من متعدد");
        mVideosList.add("اضافه اختبار من نوع اختيار من متعدد");

        videosLink.add("https://drive.google.com/file/d/1x2EeN-1frpFDdW_NyTZYT7V4Ahu3vPji/preview");
        videosLink.add("https://drive.google.com/file/d/1UD0hWVpTSVfI6_Y0avJOtOyXGpCzp7oh/preview");
        videosLink.add("https://drive.google.com/file/d/1UrPWDyJr8Rp_vOQmHpyExYtrRRypeOZc/preview");
        videosLink.add("https://drive.google.com/file/d/1dsVTDSkB407zn-bFtVbWFk04dhvDDrsF/preview");
        videosLink.add("https://drive.google.com/file/d/11uu44Tp4oO7RXckhAEyECVD4fK8fv6VU/preview");
        videosLink.add("https://drive.google.com/file/d/1FLm0o35sN1N5x4J8ZG6TTchZqa72OkqC/preview");
        videosLink.add("https://drive.google.com/file/d/1Jg6IaUr4ELl75qeiJW7K8g6cOm0znYJe/preview");
        videosLink.add("https://drive.google.com/file/d/1sSjAlSk_LYf2PXOiW7obsnefRK37PPaO/preview");
        videosLink.add("https://drive.google.com/file/d/1QvkVngMMSYPCx18Z_OqQduArU7bUHqCG/preview");
        videosLink.add("https://drive.google.com/file/d/1QtQFSVv46Q4B6llWPOZcNX18OfRkhibQ/preview");
        videosLink.add("https://drive.google.com/file/d/1wTGIpLao5b_OPGZQbdf76JATKCV45Kh3/preview");
        videosLink.add("https://drive.google.com/file/d/1dIEVnUm8HeCIGlD_pFULBjEOpyPj3WqT/preview");
        videosLink.add("https://drive.google.com/file/d/1QeLsiV_LwBYwcl-OoU3BmoZLC2MCcibC/preview");
        videosLink.add("https://drive.google.com/file/d/1KV3LEglY0MlP9OW_gSZjeLACQUoFH_lW/preview");
        videosLink.add("https://drive.google.com/file/d/1ln3gUn1vQQITgTp4UJwUBnCcJZFIzMDw/preview");

        //assign video
        mVideosListView =  findViewById(R.id.videoListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,mVideosList);
        mVideosListView.setAdapter(adapter);

        mVideosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(Vedios.this,WebVedio.class);
                intent.putExtra("url",videosLink.get(i));
                startActivity(intent);
            }
        });
        //create videos
       /* VideoModel video1 = new VideoModel("https://vanya.convertio.me/p/QKiFxx-2EosQx1HD1KIbxg/45b7cd830aaebe4bcfd20c973aee6bad/open_id_1x2EeN-1frpFDdW_NyTZYT7V4Ahu3vPji.mp4");
        VideoModel video2 = new VideoModel("https://petya.convertio.me/p/Nx43blzl1dwABfwYxW5crg/45b7cd830aaebe4bcfd20c973aee6bad/open_id_1UD0hWVpTSVfI6_Y0avJOtOyXGpCzp7oh.mp4");
        VideoModel video3 = new VideoModel("https://kolya.convertio.me/p/nYnYIQk5X_WKpMi_KM5pRQ/45b7cd830aaebe4bcfd20c973aee6bad/open_id_1UrPWDyJr8Rp_vOQmHpyExYtrRRypeOZc.mp4");
        VideoModel video4 = new VideoModel("https://kolya.convertio.me/p/5C2F4e3ecjjf5qScqwwwUA/45b7cd830aaebe4bcfd20c973aee6bad/open_id_1dsVTDSkB407zn-bFtVbWFk04dhvDDrsF.mp4");
        VideoModel video5 = new VideoModel("https://serge.convertio.me/p/kO67564UwILwQDfSwlFiCQ/45b7cd830aaebe4bcfd20c973aee6bad/open_id_11uu44Tp4oO7RXckhAEyECVD4fK8fv6VU.mp4");
        VideoModel video6 = new VideoModel("https://roman.convertio.me/p/5LI6CxPc5MlJ1GwybKDe_g/45b7cd830aaebe4bcfd20c973aee6bad/open_id_1FLm0o35sN1N5x4J8ZG6TTchZqa72OkqC.mp4");
        VideoModel video7 = new VideoModel("https://www30.online-convert.com/dl/web1/download-file/c11e9afc-f94c-4747-b592-a7cc44fb65cc/mp4-1Jg6IaUr4ELl75qeiJW7K8g6cOm0znYJe.mp4");
        VideoModel video8 = new VideoModel("https://serge.convertio.me/p/8G4EaWDZuFjy0wyUeLeeJA/45b7cd830aaebe4bcfd20c973aee6bad/open_id_1sSjAlSk_LYf2PXOiW7obsnefRK37PPaO.mp4");
        VideoModel video9 = new VideoModel("https://www6.online-convert.com/dl/web1/download-file/4f384a0e-e8d2-43ed-87ef-cdeed055ed16/True-_False_mp4-1QvkVngMMSYPCx18Z_OqQduArU7bUHqCG.mp4");
        VideoModel video10 = new VideoModel("https://www8.online-convert.com/dl/web1/download-file/f517cc5e-373d-4ef6-a5c4-bcbe82796754/mp4-1QtQFSVv46Q4B6llWPOZcNX18OfRkhibQ.mp4");
        VideoModel video11= new VideoModel("https://www37.online-convert.com/dl/web1/download-file/540ecfa0-b6a0-4644-890a-cf0fdfd7593b/Fill_In_The_Blank_mp4-1wTGIpLao5b_OPGZQbdf76JATKCV.mp4");
        VideoModel video12 = new VideoModel("https://www36.online-convert.com/dl/web1/download-file/515c72ec-4a6f-404d-8ab2-7f0d15bc9998/mp4-1dIEVnUm8HeCIGlD_pFULBjEOpyPj3WqT.mp4");
        VideoModel video13= new VideoModel("https://www4.online-convert.com/dl/web1/download-file/74ba8388-9a2a-48ba-8ffe-d228dd48bb96/Sequence_mp4-1QeLsiV_LwBYwcl-OoU3BmoZLC2MCcibC.mp4");
        VideoModel video14 = new VideoModel("https://www4.online-convert.com/dl/web1/download-file/aabc2daa-86fb-46f2-b85c-99ba84c6808a/Multi_Choice_Multiple_Answers_1_mp4-1KV3LEglY0MlP9.mp4");
        VideoModel video15= new VideoModel("https://www4.online-convert.com/dl/web1/download-file/214c5f0b-a00e-4c91-9559-54698ef9e8c8/Multi_Choice_Single_Answer_mp4-1ln3gUn1vQQITgTp4UJ.mp4");
        mVideosList.add(video1);
        mVideosList.add(video2);
        mVideosList.add(video3);
        mVideosList.add(video4);
        mVideosList.add(video5);
        mVideosList.add(video6);
        mVideosList.add(video7);
        mVideosList.add(video8);
        mVideosList.add(video9);
        mVideosList.add(video10);
        mVideosList.add(video11);
        mVideosList.add(video12);
        mVideosList.add(video13);
        mVideosList.add(video14);
        mVideosList.add(video15);*/

//        mVideoAdapter = new VideoAdapter(this, mVideosList);
//        mVideosListView.setAdapter(mVideoAdapter);
//


    }
}