package br.com.ezbar.app.json;

import br.com.ezbar.framework.json.injector.PrimitiveJsonInjector;

public class MyJsonInjector extends PrimitiveJsonInjector {

    private static MyJsonInjector injector;

    static {
        injector = new MyJsonInjector();
        injector.addParser(new JsonInjectorParserUser());
        injector.addParser(new JsonInjectorParserUserAuthentication());
    }

    public static MyJsonInjector getInjector() {
        return injector;
    }
}
