package nikifor.tatarkin.quizjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    private Button buttonBack;
    private TextView buttonLevel1;
    private TextView buttonLevel2;
    private TextView buttonLevel3;
    private TextView buttonLevel4;
    private TextView buttonLevel5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        //инициализация
        initViews();

        //Назад (к главному экрану)
        back();

        startLevel1();
        startLevel2();
        startLevel3();

        //Убераем строку состояния (во весь экран)
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void back() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                }
            }
        });
    }

    private void initViews() {
        buttonBack = findViewById(R.id.button_back);
        buttonLevel1 = findViewById(R.id.textView1);
        buttonLevel2 = findViewById(R.id.textView2);
        buttonLevel3 = findViewById(R.id.textView3);

    }

    private void startLevel1(){
        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
    }
    private void startLevel2(){
        buttonLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
    }
    private void startLevel3(){
        buttonLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
    }

    //Системная кнопка назад
    @Override
    public void onBackPressed() {
        try{
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);finish();
        }catch (Exception e){
        }
    }
}
