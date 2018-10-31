package br.com.ezbar.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.ezbar.R;
import br.com.ezbar.model.business.LoginCredentials;
import br.com.ezbar.model.service.IServiceListener;
import br.com.ezbar.model.service.LoginService;
import br.com.ezbar.model.service.Service;

public class MainActivity extends AppCompatActivity implements IServiceListener {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        LoginService ls = new LoginService(this, new LoginCredentials("Guilherme@gmail.com", "123456"));
        ls.request();
    }

    @Override
    public void requestComplete(Service service) {
        if(service instanceof LoginService) {
            // login success
        }
    }

    @Override
    public void requestError(Service service) {
        if(service instanceof LoginService) {
            // login fail
        }
    }
}
