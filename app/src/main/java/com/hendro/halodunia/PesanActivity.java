package com.hendro.halodunia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hendro.halodunia.databinding.ActivityPesanBinding;

public class PesanActivity extends AppCompatActivity {

    private ActivityPesanBinding binding;
    String CHANNEL_NOTIF = "channelku" ;
    String CHANNEL_ID = "default" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPesanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.btToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Pesan Toast", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackBar = Snackbar
                        .make(view, "Snack Bar!", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Snack Bar menutup", Toast.LENGTH_SHORT).show();
                            }
                });
                snackBar.setActionTextColor(Color.BLUE);
                snackBar.show();
            }
        });

        binding.btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PesanActivity.this);
                alertDialogBuilder.setMessage("Ingin menutup Activity ini?");

                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        binding.btNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(PesanActivity.this, CHANNEL_ID )
                        .setSmallIcon(R.drawable.ic_refresh_white_24)
                        .setContentTitle( "Android!" )
                        .setContentText( "Anda sedang mempelajarinya    " );

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;

                if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                    int importance = NotificationManager. IMPORTANCE_HIGH ;
                    NotificationChannel notificationChannel = new
                            NotificationChannel( CHANNEL_NOTIF , "contoh channel" , importance) ;
                    notificationChannel.enableLights( true ) ;
                    notificationChannel.setLightColor(Color. RED ) ;
                    mBuilder.setChannelId( CHANNEL_NOTIF ) ;
                    assert mNotificationManager != null;
                    mNotificationManager.createNotificationChannel(notificationChannel) ;
                }
                assert mNotificationManager != null;
                mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
            }
        });
    }
}