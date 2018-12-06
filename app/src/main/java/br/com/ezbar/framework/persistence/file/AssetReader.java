package br.com.ezbar.framework.persistence.file;

import android.content.Context;
import android.support.annotation.RawRes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetReader {

    public Context context;

    public AssetReader(Context context) {
        this.context = context;
    }

    public String readRawTextFile(@RawRes int resId)
    {
        InputStream inputStream = context.getResources().openRawResource(resId);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            return null;
        }
        return byteArrayOutputStream.toString();
    }
}
