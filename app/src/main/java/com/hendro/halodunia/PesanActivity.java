package com.hendro.halodunia;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.hendro.halodunia.databinding.ActivityPesanBinding;

import java.util.Objects;

public class PesanActivity extends AppCompatActivity {

    private ActivityPesanBinding binding;
    String CHANNEL_NOTIF = "channelku";
    String CHANNEL_ID = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPesanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.topAppBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.btToast.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Pesan Toast", Toast.LENGTH_SHORT).show());

        binding.btSnack.setOnClickListener(view -> {
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

                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
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
                // apakah permission sudah diaktifkan pada ponsel?
                if (NotificationManagerCompat.from(PesanActivity.this).areNotificationsEnabled()) {
                    //notifikasi
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_IMMUTABLE);

                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.setBigContentTitle(getApplicationContext().getResources().getString(R.string.app_name));
                    bigText.setSummaryText(getApplicationContext().getResources().getString(R.string.notifikasi));

                    mBuilder.setContentIntent(pendingIntent);
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                    mBuilder.setContentTitle(getApplicationContext().getResources().getString(R.string.app_name));
                    mBuilder.setContentText(getApplicationContext().getResources().getString(R.string.notifikasi));
                    mBuilder.setPriority(Notification.PRIORITY_MAX);
                    mBuilder.setStyle(bigText);
                    mBuilder.setDefaults(Notification.DEFAULT_SOUND); //suara
                    mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000}); //getar

                    NotificationManager mNotificationManager =
                            (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("notify_001",
                                "channelku",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        mNotificationManager.createNotificationChannel(channel);
                    }

                    mNotificationManager.notify(0, mBuilder.build());
                } else {
                    new MaterialAlertDialogBuilder(PesanActivity.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle("Perhatian")
                            .setMessage("Ingin mengaktifkan Notifikasi?")
                            .setCancelable(true)
                            .setPositiveButton("Ya", (dialog, which) -> {
                                // buka notification setting
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            })
                            .setNegativeButton("Tidak", (dialog, which) -> {

                                    }
                            )
                            .show();

                }
            }
        });
    }
}