package nikifor.tatarkin.quizjava;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;

public class Level2 extends AppCompatActivity {

    private TextView textLevels;
    private ImageView imgLeft;
    private ImageView imgRight;
    private Dialog dialogStart;
    private Button buttonBack;
    private TextView textLeft;
    private TextView textRight;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //счетчик правильный ответов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Подключение анимации
        final Animation animation = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

        //Массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        initViews(); //Инициализация данных
        createDialog(); //Создание диалога
        back(); //возвращение назад
        getDataLanguage(animation); //Получение данных из класса Array

        //Убираем строку состояния (во весь экран)
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        touchLeftImg(progress, animation);
        touchRightImg(progress, animation);


    }

    private void getDataLanguage(Animation animation) {
        numLeft = random.nextInt(10);
        imgLeft.setImageResource(array.images1[numLeft]);
        imgLeft.startAnimation(animation);
        textLeft.setText(array.texts1[numLeft]);

        numRight = random.nextInt(10);
        while (numLeft == numRight){
            numRight = random.nextInt(10);
        }
        imgRight.setImageResource(array.images1[numRight]);
        imgRight.startAnimation(animation);
        textRight.setText(array.texts1[numRight]);
    }
    private void createDialog(){
        dialogStart =new Dialog(this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogStart.setContentView(R.layout.previewdialog);
        Objects.requireNonNull(dialogStart.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogStart.setCancelable(false); //диалог нельзя закрыть кнопкой назад

        TextView btnClose = dialogStart.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //вернуться назад к выбору уровня
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish(); //закрыть этот класс(окно)
                }catch (Exception e){
                    //Ничего нет
                }
                dialogStart.dismiss(); //закрываем диалоговое окно
            }
        });

        //Кнопка "Продолжить"
        Button btnСontinue = dialogStart.findViewById(R.id.btncontinue);
        btnСontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogStart.dismiss(); //закрываем диалоговое окно
            }
        });
        dialogStart.show();
    }
    private void initViews() {
        textLevels = findViewById(R.id.text_levels);
        textLevels.setText(R.string.level1);

        imgLeft = findViewById(R.id.img_left);
        imgLeft.setClipToOutline(true);
        imgRight = findViewById(R.id.img_right);
        imgRight.setClipToOutline(true);

        textLeft = findViewById(R.id.text_left);
        textRight = findViewById(R.id.text_right);

        buttonBack = findViewById(R.id.button_back);
    }
    private void back(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //вернуть назад к выбору уровня
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ignored){
                }
            }
        });
    }
    private void touchLeftImg(final int[] progress, final Animation animation){
        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imgRight.setEnabled(false);//Блокируем правую картинку
                    if(numLeft>numRight){
                        imgLeft.setImageResource(R.drawable.img_true);
                    }else {
                        imgLeft.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    if(numLeft > numRight){
                        if(count < 20) {count++;}
                        for(int i= 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else {
                        //Если левая картинка меньше
                        if(count > 0){
                            if(count==1){
                                count = 0;
                            }else {
                                count = count -2;
                            }
                        }
                        for(int i= 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20){
                        //ВЫХОД ИЗ УРОВНЯ
                    }else{
                        getDataLanguage(animation);
                        imgRight.setEnabled(true);
                    }

                }
                return true;
            }
        });
    }
    private void touchRightImg(final int[] progress, final Animation animation){
        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imgLeft.setEnabled(false);//Блокируем левую картинку
                    if(numRight>numLeft){
                        imgRight.setImageResource(R.drawable.img_true);
                    }else {
                        imgRight.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    if(numLeft < numRight){
                        if(count < 20) {count++;}
                        for(int i= 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else {
                        //Если правая картинка меньше
                        if(count > 0){
                            if(count==1){
                                count = 0;
                            }else {
                                count = count -2;
                            }
                        }
                        for(int i= 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20){
                        //ВЫХОД ИЗ УРОВНЯ
                    }else{
                        getDataLanguage(animation);
                        imgLeft.setEnabled(true);
                    }

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
        }
    }
}
