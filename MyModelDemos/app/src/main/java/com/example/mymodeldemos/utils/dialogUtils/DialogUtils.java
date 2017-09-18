package com.example.mymodeldemos.utils.dialogUtils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by 吴城林 on 2017/9/14.
 */

public class DialogUtils {

    private static String items[] = {"从相册里选择","拍摄图片"};

    public static void createDialog(Context context, String title, final IDialogItemClickListener listener){
        AlertDialog alertDialog =new  AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onItemClicked(dialogInterface,i);
                    }
                })
                .show();
    }

}
