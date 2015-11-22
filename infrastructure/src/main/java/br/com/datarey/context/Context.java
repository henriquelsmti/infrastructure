package br.com.datarey.context;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Context {
    private static Weld weld;
    private static WeldContainer container;

    static {
        weld = new Weld();
        container = weld.initialize();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clazz) {
        return (T) container.instance().select(clazz).get();
    }
}
