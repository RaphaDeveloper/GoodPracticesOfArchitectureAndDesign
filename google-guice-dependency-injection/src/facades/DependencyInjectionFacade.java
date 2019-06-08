package facades;

import com.google.inject.Guice;
import com.google.inject.Injector;
import module.ConcreteModule;

public class DependencyInjectionFacade {

    public static <T> T getInstanceOf(Class<T> type) {
        Injector injector = Guice.createInjector(new ConcreteModule());

        return injector.getInstance(type);
    }

}
