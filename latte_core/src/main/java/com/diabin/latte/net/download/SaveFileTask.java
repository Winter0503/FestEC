package com.diabin.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.diabin.latte.app.Latte;
import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Create by 心跳 on 2019/8/20 11:02
 * Blog : https://mp.csdn.net/
 * escription :异步下载的工具类
 */
public class SaveFileTask  extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String download =(String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody body = (ResponseBody) objects[2];
        final String name = (String) objects[3]; //文件名
        final InputStream is = body.byteStream();

        //下载的路径
        if (download == null || "".equals(download)) {
            download = "down_loads";
        }
        if (extension == null) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, download, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, download, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getAbsolutePath());
        }
        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
