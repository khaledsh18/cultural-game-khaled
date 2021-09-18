package com.khaledshalabi.culturalwordsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int index;
    private int[] images = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_12,
            R.drawable.icon_13,
    };
    private String[] answers;
    private String[] answersDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app_lang","");
        if (!appLang.equals(""))
            LocaleHelper.setLocale(this,appLang);

        setContentView(R.layout.activity_main);

        answers = getResources().getStringArray(R.array.answers);
        answersDescribe = getResources().getStringArray(R.array.answer_description);
        imageView = (ImageView) findViewById(R.id.culturalImageView);
        changeQues();


    }

    public void changeLang(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.choose_lang)
                .setItems(R.array.langs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String lang = "ar";
                        switch (which){
                            case 0:
                                lang = "ar";
                                break;
                            case 1:
                                lang = "en";
                                break;
                        }
                        saveLang(lang);
                        LocaleHelper.setLocale(MainActivity.this,lang);
                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).create();

        alertDialog.show();
    }

    public void changeQues(View view) {
        Random random = new Random();
        index = random.nextInt(images.length);
        Drawable drawable = getDrawable(images[index]);
        imageView.setImageDrawable(drawable);

    }

    public void changeQues() {
        Random random = new Random();
        index = random.nextInt(images.length);
        Drawable drawable = getDrawable(images[index]);
        imageView.setImageDrawable(drawable);

    }

    public void getAnswer(View view) {
        Intent intent = new Intent(this,AnswerActivity.class);
        intent.putExtra("ANSWER",answers[index]);
        intent.putExtra("ANSWER_DESCRIBE",answersDescribe[index]);
        startActivity(intent);

    }

    public void share(View view) {
        Intent intent = new Intent(MainActivity.this, ShareActivity.class);
        intent.putExtra("IMAGE", images[index]);
        startActivity(intent);

    }

    public void saveLang(String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang",lang);
        editor.apply();

    }
}