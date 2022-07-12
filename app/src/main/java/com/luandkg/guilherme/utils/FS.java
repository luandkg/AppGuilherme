package com.luandkg.guilherme.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import com.luandkg.guilherme.Local;

import java.io.File;

public class FS {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void pedirPermissoes(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);

        }
    }

    public static void verifyStoragePermissions(Activity activity) {


        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    public static boolean dirExiste(String eLocal) {
        File ePasta = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), eLocal);
        return ePasta.exists();

    }

    public static boolean arquivoExiste(String eLocal, String eArquivo) {
        File ePasta = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), eLocal);

        boolean ret = false;

        for (File eArq : ePasta.listFiles()) {
            if (eArq.getName().contentEquals(eArquivo)) {
                ret = true;
                break;
            }
            //   System.out.println("EX ->> " + eArq.getName());
        }


        return ret;

    }


    public static boolean arquivoExisteDireto(String eLocal) {
        File ePasta = new File(eLocal);
        return ePasta.exists();
    }


    public static void dirCriar(String eLocal) {

        File ePasta = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), eLocal);
        if (!ePasta.exists()) {
            ePasta.mkdir();
        }

    }

    public static String getArquivoLocal(String eLocal) {

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), eLocal);

        return file.getAbsolutePath();
    }

    public static File getPasta(String eLocal) {

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), eLocal);

        return file;
    }

    public static String getArquivoLocal(String eLocal, String eNome) {

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), eLocal + "/" + eNome);

        return file.getAbsolutePath();
    }

    public static File[] getListaDeArquivos(String eLocal) {
        File pasta = FS.getPasta(eLocal);
        return pasta.listFiles();
    }
}
