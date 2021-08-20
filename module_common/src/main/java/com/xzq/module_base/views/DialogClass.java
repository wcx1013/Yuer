package com.xzq.module_base.views;

import android.app.Activity;

import com.xzq.module_base.R;

import java.util.ArrayList;
import java.util.List;

public class DialogClass {
    //拍照提示框
    public static SelectDialog showDialogPic(SelectDialog.SelectDialogListener listener, Activity activity) {
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("从相册选择");
        SelectDialog dialog = new SelectDialog(activity, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
}
