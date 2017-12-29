package com.maishealth.maishealth.infra;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.maishealth.maishealth.R;

public class GuiUtil {


    private static void myToast(Context context, String text, int time){
        Toast.makeText(context, text, time).show();
    }

    public static void myToast(Context context, Exception e, int time){
        GuiUtil.myToast(context, e.getMessage(), time);
    }

    public static void myToast(Context context, String text){
        GuiUtil.myToast(context, text, Toast.LENGTH_LONG);
    }

    public static void myToast(Context context, Exception e){
        GuiUtil.myToast(context, e.getMessage(), Toast.LENGTH_LONG);
    }

    public static void myToastShort(Context context, String text){
        GuiUtil.myToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void myToastShort(Context context, Exception e){
        GuiUtil.myToast(context, e.getMessage(), Toast.LENGTH_SHORT);
    }

    private static void myAlertDialog(Context context, String text, String titulo){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle(titulo);
        dialogo.setMessage(text);
        dialogo.setNeutralButton(R.string.dialog_botao_ok, null);
        dialogo.show();
    }

    public static void myAlertDialog(Context context, Exception e, String titulo){
        GuiUtil.myAlertDialog(context, e.getMessage(), titulo);
    }

    public static void myAlertDialog(Context context, Exception e){
        GuiUtil.myAlertDialog(context, e.getMessage(), context.getString(R.string.dialog_titulo_erro));
    }

    public static void myAlertDialog(Context context, String text){
        GuiUtil.myAlertDialog(context, text, context.getString(R.string.dialog_titulo_erro));
    }
}