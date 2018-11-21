package br.com.ezbar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.ezbar.R;
import br.com.ezbar.app.business.User;
import br.com.ezbar.framework.service.ServiceException;

public class MainActivity extends AppCompatActivity implements ServiceUser.IServiceUser {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onUserResult(User user) {

    }

    @Override
    public void serviceError(ServiceException e) {

    }
}
