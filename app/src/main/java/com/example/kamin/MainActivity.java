package com.example.kamin;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    @NotNull
    public static String inBufr;
    public static byte btndn = 1;
    private boolean flag;
    private Object v;
    private boolean fla;
    public static String direction;
    public static String ipPub;
    public static String getFromServer;
    public static String ipPu;


    int[] drawable = {R.drawable.frieoff, R.drawable.frielow, R.drawable.friemid, R.drawable.friehigh};//картинки на главной странице огонь
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE); //получение локального IP
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());


        //дублируем ip на всякий
        String[] ipAdds = ipAddress.split("\\."); //режем IP на квартеты
        {
            MainActivity.ipPu = (ipAdds[0] + "." + ipAdds[1] + "." + ipAdds[2] + ".255");
        }
        int hate = 0;
        ipPub = ipPu;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Udp_client udp_client = new Udp_client();
        Udp_receive udp_receive = new Udp_receive();
        executorService.execute(udp_receive);
        executorService.execute(udp_client);




        inBufr = Udp_client.inBuf;


//        if (getFromServer.equals("HATEON")) {
//            hate = 1;
//        }

        int finalHate1 = hate;

        TextView TextView7 = (TextView) findViewById(R.id.textView7);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);// вкл/выкл обогрев
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalHate1 == 0) {
                    TextView7.setText(getFromServer);
                    btndn = 100;
                    direction = "HATEON";
                    imageButton2.setImageResource(R.drawable.obogrev2);
                    btndn = 1;
                }
                if (finalHate1 == 1) {
                    btndn = 100;
                    direction = "HATEOFF";
                    imageButton2.setImageResource(R.drawable.obogrev1);
                    btndn = 1;
                }
            }
        });
        

        final ImageButton imageButton6 = (ImageButton) findViewById(R.id.imageButton6); // вкл/выкл вентилятор
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    imageButton6.setImageResource(R.drawable.ven);
                    //   direction = 6;
                } else
                    // возвращаем первую картинку
                    imageButton6.setImageResource(R.drawable.vent3);
                flag = !flag;
                direction = "ON";
            }
        });

        final ImageButton imageButton7 = (ImageButton) findViewById(R.id.imageButton7); // вкл/выкл розетки на камине
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag)
                    imageButton7.setImageResource(R.drawable.roz1);

                else
                    // возвращаем первую картинку
                    imageButton7.setImageResource(R.drawable.plug2);
                flag = !flag;
            }
        });

        final ImageButton imageButton9 = (ImageButton) findViewById(R.id.imageButton9); // вкл/выкл обогрев звук
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag)
                    imageButton9.setImageResource(R.drawable.z1);

                else
                    // возвращаем первую картинку
                    imageButton9.setImageResource(R.drawable.z2);
                flag = !flag;
            }
        });


        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton); // картинки на главной странице огонь меняются
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i == drawable.length)
                    i = 0;
                imageButton.setImageResource(drawable[i]);
            }
        });


        final ImageButton imageButton11 = (ImageButton) findViewById(R.id.imageButton11); //выход из приложения
        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Выход из приложения");
                builder.setMessage("Вы действительно хотите выйти?");
                builder.setNegativeButton("НЕТ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        });
                builder.setPositiveButton("ДА",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        });
                builder.show();
            }
        });

        final ImageButton imageButton10 = (ImageButton) findViewById(R.id.imageButton10); //переход на др стр настройки
        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }


        });

    }


}






















