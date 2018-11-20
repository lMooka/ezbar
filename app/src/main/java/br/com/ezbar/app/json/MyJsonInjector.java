package br.com.ezbar.app.json;

import br.com.ezbar.framework.json.injector.PrimitiveJsonInjector;

public class MyJsonInjector extends PrimitiveJsonInjector {

    public MyJsonInjector() {
        addParser(new JsonInjectorParserUser());
    }
}
