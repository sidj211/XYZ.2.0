package com.example.xyzreader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xyzreader.model.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ArticleDetailActivity extends AppCompatActivity {

    ImageView article_photo;
    TextView article_title;
    TextView article_body;
    String title;
    String body;
    String imageurl;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    // Use default locale format
    private SimpleDateFormat outputFormat = new SimpleDateFormat("E, dd MMM yyyy");
    // Most time functions can only handle 1902 - 2037
    private GregorianCalendar START_OF_EPOCH = new GregorianCalendar(2,1,1);

    private TextView article_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().requestFeature((Window.FEATURE_CONTENT_TRANSITIONS));



        setContentView(R.layout.activity_article_detail);

        article_title = findViewById(R.id.article_title);
        article_body = findViewById(R.id.article_body);
        article_photo = findViewById(R.id.photo);
        article_by = findViewById(R.id.article_byline);




        Intent intent = getIntent();
        Article article = intent.getExtras().getParcelable("article");
        title = article.getTitle();
        body = article.getBody();
        imageurl = article.getPhoto_url();
        Log.e("PAKDO","checking log"+article.getTitle());
       article_title.setText(title);
             bindControl();
        Glide.with(this).asBitmap().dontAnimate().load(imageurl).into(article_photo);
        article_body.setText(Html.fromHtml(body));

        Log.e("PAKDO","Date "+article.getPublished_date()+" by "+article.getAuthor());


        Date data = null;
        try {
            data = dateFormat.parse(article.getPublished_date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String god = outputFormat.format(data);
            String byline = god+" by "+article.getAuthor();
            Log.d("TAG", "onCreate: time is  "+god+" date is "+data.getDate()+" month"+data.getMonth()+" year "+data.getYear());

        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.body_animation);
        animation.setDuration(500);
        article_body.setAnimation(animation);
        article_body.animate();
        animation.start();
        article_by.setText(byline);

    }

    private void setWindowFlag(ArticleDetailActivity activity, int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void bindControl() {

    }
}
