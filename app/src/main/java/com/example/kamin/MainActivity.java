package com.example.kamin;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static byte btndn = 100;
    private boolean flag;
    public static String direction;
    public static String ipPub;
    public static String getFromServer;
    public static String ipPu;

    int[] drawable = {R.drawable.frieoff, R.drawable.frielow, R.drawable.friemid, R.drawable.friehigh};//картинки на главной странице огонь
    int i = 0;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE); //получение локального IP
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        String[] ipAdds = ipAddress.split("\\.");
        {
            MainActivity.ipPu = (ipAdds[0] + "." + ipAdds[1] + "." + ipAdds[2] + ".255");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Блок кнопок
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);// вкл/выкл обогрев

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.imageButton6); // вкл/выкл вентилятор
        ImageButton imageButton7 = (ImageButton) findViewById(R.id.imageButton7); // вкл/выкл розетки на камине
        ImageButton imageButton9 = (ImageButton) findViewById(R.id.imageButton9); // вкл/выкл обогрев звук
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton); // картинки на главной странице огонь меняются
        ImageButton imageButton11 = (ImageButton) findViewById(R.id.imageButton11); //выход из приложения
        ImageButton imageButton10 = (ImageButton) findViewById(R.id.imageButton10); //переход на др стр настройки
//конец блока кнопок

        int hate = 0;
        ipPub = ipPu;

        new Udp_client().start();
        int[] sost = {2};
        Handler handler_for_udpReceive = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

//                byte[] bytes = getFromServer.getBytes();
//                for (byte s : bytes) {
//                    System.out.print(s);
//                }


                getFromServer = msg.obj.toString();
                if (getFromServer.contains("HEATON")) {
                    imageButton2.setImageResource(R.drawable.obogrev2);
                    textView7.setText(getFromServer);
                    sost[0] = 1;
                }
                if (getFromServer.contains("HEATOFF")) {
                    imageButton2.setImageResource(R.drawable.obogrev1);
                    textView7.setText(getFromServer);
                    sost[0] = 2;
                }

            }
        };

        new Udp_receive(handler_for_udpReceive).start();
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sost[0] == 1) {
                    btndn = 1;
                    direction = "HEATOFF";
                    btndn = 100;
                }
                if (sost[0] == 2) {
                    btndn = 1;
                    direction = "HEATON";
                    btndn = 100;
                }
            }
        });


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


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i == drawable.length)
                    i = 0;
                imageButton.setImageResource(drawable[i]);
            }
        });


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


        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }


        });

    }


}






















