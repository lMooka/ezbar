package br.com.ezbar.app.service;

import android.content.Context;

import org.json.JSONException;

import br.com.ezbar.R;
import br.com.ezbar.framework.persistence.file.AssetReader;
import br.com.ezbar.framework.service.ServiceFaker;

public class ServiceFakerBuilder {
    private static boolean isBuilt = false;

    public static void build(Context context) {
        if(isBuilt)
            return;

        AssetReader ar = new AssetReader(context);
        try {

            ServiceFaker.addFakeResponse("http://www.url.com.br/service/auth.php?", ar.readRawTextFile(R.raw.ServiceUserAuthenticationFaker));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        isBuilt = true;
    }
}
