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

public class Level3 extends AppCompatActivity {

    private TextView textLevels;
    private ImageView imgTop;
    private ImageView imgCenter;
    private ImageView imgBottom;
    private Dialog dialogStart;
    private Dialog dialogEnd;
    private Button buttonBack;

    public int numTop;
    public int numCenter;
    public int numBottom;
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //счетчик правильный ответов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_two);

        //Подключение анимации
        final Animation animation = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);

        //Массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        initViews(); //Инициализация данных
        createDialog(); //Создание диалога
        createDialogEnd();
        back(); //возвращение назад
        getDataLanguage(animation); //Получение данных из класса Array

        //Убираем строку состояния (во весь экран)
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        touchTopImg(progress, animation);
        touchCenterImg(progress, animation);
        touchBottomImg(progress, animation);
    }



    private void getDataLanguage(Animation animation) {
        numTop = random.nextInt(36);
        imgTop.setImageResource(array.images3[numTop]);
        imgTop.startAnimation(animation);

        numCenter = random.nextInt(36);
        numBottom = random.nextInt(36);
        while ((array.strong[numTop] == 0 && array.strong[numCenter] == 0 && array.strong[numBottom] == 0  )||
                (array.strong[numTop] == 1 && array.strong[numCenter] == 1 && array.strong[numBottom] == 0 )||
                        (array.strong[numTop] == 0 && array.strong[numCenter] == 1 && array.strong[numBottom] == 1 )||
                                (array.strong[numTop] == 1 && array.strong[numCenter] == 0 && array.strong[numBottom] == 1 )||
                                        (array.strong[numTop] == 1 && array.strong[numCenter] == 1 && array.strong[numBottom] == 1)
        ){
            numCenter = random.nextInt(36);
            numBottom = random.nextInt(36);

        }
        imgCenter.setImageResource(array.images3[numCenter]);
        imgCenter.startAnimation(animation);

        imgBottom.setImageResource(array.images3[numBottom]);
        imgBottom.startAnimation(animation);
    }
    private void createDialog(){
        dialogStart =new Dialog(this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogStart.setContentView(R.layout.previewdialog);
        Objects.requireNonNull(dialogStart.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogStart.setCancelable(false); //диалог нельзя закрыть кнопкой назад

        TextView tv = dialogStart.findViewById(R.id.textdescription);
        tv.setText(R.string.levelTwoStart);

        ImageView img = dialogStart.findViewById(R.id.previewImg);
        img.setImageResource(R.drawable.previewimgtwo);

        TextView btnClose = dialogStart.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //вернуться назад к выбору уровня
                    Intent intent = new Intent(Level3.this, GameLevels.class);
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
    private void createDialogEnd() {
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialog_end);
        Objects.requireNonNull(dialogEnd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //диалог нельзя закрыть кнопкой назад

        TextView btnClose = dialogEnd.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //вернуться назад к выбору уровня
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish(); //закрыть этот класс(окно)
                } catch (Exception e) {
                    //Ничего нет
                }
                dialogEnd.dismiss(); //закрываем диалоговое окно
            }
        });
    }
    private void initViews() {
        textLevels = findViewById(R.id.text_levels);
        textLevels.setText(R.string.level1);

        imgTop = findViewById(R.id.img_one);
        imgTop.setClipToOutline(true);
        imgCenter = findViewById(R.id.img_two);
        imgCenter.setClipToOutline(true);
        imgBottom = findViewById(R.id.img_three);
        imgBottom.setClipToOutline(true);

        buttonBack = findViewById(R.id.button_back);
    }
    private void back(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //вернуть назад к выбору уровня
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ignored){
                }
            }
        });
    }
    private void touchTopImg(final int[] progress, final Animation animation){
        imgTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imgCenter.setEnabled(false);//Блокируем центральную и нижнюю картинки
                    imgBottom.setEnabled(false);
                    if(array.strong[numTop] == 1){
                        imgTop.setImageResource(R.drawable.img_true_horizontal);
                    }else {
                        imgTop.setImageResource(R.drawable.img_false_horizontal);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    if(array.strong[numTop] == 1){
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
                        dialogEnd.show();
                    }else{
                        getDataLanguage(animation);
                        imgCenter.setEnabled(true);
                        imgBottom.setEnabled(true);
                    }

                }
                return true;
            }

        });
    }
    private void touchCenterImg(final int[] progress, final Animation animation){
        imgCenter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imgTop.setEnabled(false);//Блокируем верхунюю и нижнюю картинки
                    imgBottom.setEnabled(false);
                    if(array.strong[numCenter] == 1){
                        imgCenter.setImageResource(R.drawable.img_true_horizontal);
                    }else {
                        imgCenter.setImageResource(R.drawable.img_false_horizontal);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    if(array.strong[numCenter] == 1){
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
                        dialogEnd.show();
                    }else{
                        getDataLanguage(animation);
                        imgTop.setEnabled(true);
                        imgBottom.setEnabled(true);
                    }

                }
                return true;
            }
        });
    }
    private void touchBottomImg(final int[] progress, final Animation animation){
        imgBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imgTop.setEnabled(false);//Блокируем верхнюю картинку
                    imgCenter.setEnabled(false); //Блокируем центральную
                    if(array.strong[numBottom] == 1){
                        imgBottom.setImageResource(R.drawable.img_true_horizontal);
                    }else {
                        imgBottom.setImageResource(R.drawable.img_false_horizontal);
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    if(array.strong[numBottom] == 1){
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
                        dialogEnd.show();
                    }else{
                        getDataLanguage(animation);
                        imgTop.setEnabled(true);
                        imgCenter.setEnabled(true);
                    }

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception ignored){
        }
    }
}
