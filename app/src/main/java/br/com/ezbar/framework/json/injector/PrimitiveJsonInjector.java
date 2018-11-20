package br.com.ezbar.framework.json.injector;

public class PrimitiveJsonInjector extends JsonInjector {
    public PrimitiveJsonInjector() {
        addParser(new JsonInjectorParserBoolean());
        addParser(new JsonInjectorParserInteger());
        addParser(new JsonInjectorParserLong());
        addParser(new JsonInjectorParserFloat());
        addParser(new JsonInjectorParserDouble());
        addParser(new JsonInjectorParserString());
    }
}
