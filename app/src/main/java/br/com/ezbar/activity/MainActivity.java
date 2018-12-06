package br.com.ezbar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.com.ezbar.R;
import br.com.ezbar.app.business.Placeholder;
import br.com.ezbar.app.service.ServiceFakerBuilder;
import br.com.ezbar.app.service.ServicePlaceholder;
import br.com.ezbar.framework.service.ServiceException;

public class MainActivity extends AppCompatActivity implements ServicePlaceholder.IServicePlaceholder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ServiceFakerBuilder.build(getApplicationContext());

        ServicePlaceholder servicePlaceholder = new ServicePlaceholder(this);
        servicePlaceholder.run();
    }

    @Override
    public void onPlaceholder(Placeholder placeholder) {
        Log.d("response", placeholder.getTitle());
    }

    @Override
    public void serviceError(ServiceException e) {
    }
}
