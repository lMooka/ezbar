package br.com.ezbar.app.service;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ezbar.app.business.User;
import br.com.ezbar.app.business.UserAuthentication;
import br.com.ezbar.app.json.MyJsonInjector;
import br.com.ezbar.framework.service.IServiceCallback;
import br.com.ezbar.framework.service.Service;
import br.com.ezbar.framework.service.ServiceException;
import br.com.ezbar.framework.service.ServiceProtocol;
import br.com.ezbar.framework.service.ServiceRequest;

public class ServiceUserAuthentication extends Service<ServiceUserAuthentication.ILoginService> {

    private UserAuthentication userAuth;
    private User user;

    public ServiceUserAuthentication(ServiceProtocol protocol, ILoginService callback) {
        super(protocol, ServiceRequest.RequestMethod.post, callback);
    }

    @Override
    protected boolean before() throws ServiceException {
        if(getServiceProtocol().getParam("post") == null) {
            throw new ServiceException(this, "Credentials was not set. Please use setCredentials() method before.");
        }

        return true;
    }

    @Override
    protected void process(JSONObject json) {
        try {
            if (json.has("error")) {
                return;
            }

            if (json.has("response")) {
                JSONObject response = json.getJSONObject("response");

                if (user == null)
                    userAuth = new UserAuthentication();

                MyJsonInjector.getInjector().inject(userAuth, response.getJSONObject("authentication"));

                if (userAuth == null)
                    user = new User();

                MyJsonInjector.getInjector().inject(user, response.getJSONObject("user"));
            }
        } catch (JSONException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void ready(ILoginService callback) {
        callback.onUserAuthentication(userAuth);
        callback.onUserReceived(user);
    }

    @Override
    public String getUrl() {
        return "http://www.url.com.br/service/auth.php?";
    }

    public ServiceUserAuthentication setCredentials(String email, String password) {

        String credentials = email + "&" + password;

        getServiceProtocol().setParamValue(
                "json",
                String.format("{'request':{'credentials_type':'email','credentials':'%s'}", credentials)
        );
        return this;
    }

    public ServiceUserAuthentication setUserAuth(UserAuthentication userAuth) {
        this.userAuth = userAuth;
        return this;
    }

    public ServiceUserAuthentication setUser(User user) {
        this.user = user;
        return this;
    }

    public interface ILoginService extends IServiceCallback {
        void onUserAuthentication(UserAuthentication auth);

        void onUserReceived(User user);
    }
}
