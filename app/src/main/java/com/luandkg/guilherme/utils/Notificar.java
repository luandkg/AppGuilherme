package com.luandkg.guilherme.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.luandkg.guilherme.Utils.AutoInt;

public class Notificar {

    public static void notifique(Context context, String eCanalNome, String titulo, String texto,Bitmap icone) {


        System.out.println("LOGO ID :: " + context.getApplicationInfo().icon);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, eCanalNome)
                .setSmallIcon(context.getApplicationInfo().icon)
                .setLargeIcon(icone)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(AutoInt.getNovoID(), builder.build());


    }

    public static void criarCanal(Context eContexto, String eCanalNome, String eCanalDescricao) {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(eCanalNome, eCanalNome, importance);
            channel.setDescription(eCanalDescricao);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = eContexto.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void criarNotificacaoSimples(Context context, String titulo, String texto) {

        int id = 1;
        int icone = android.R.drawable.ic_dialog_info;

        //   Intent intent = new Intent(this, TextoActivity.class);
        //  PendingIntent p = getPendingIntent(id, intent, context);

        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(context);
        notificacao.setSmallIcon(icone);
        notificacao.setContentTitle(titulo);
        notificacao.setContentText(texto);
        // notificacao.setContentIntent(p);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, notificacao.build());


    }

    private static PendingIntent getPendingIntent(int id, Intent intent, Context context) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);

        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }
}