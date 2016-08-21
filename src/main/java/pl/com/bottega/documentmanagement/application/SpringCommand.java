package pl.com.bottega.documentmanagement.application;

import org.hsqldb.persist.CachedObject;
import org.springframework.context.ApplicationContext;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
public abstract class SpringCommand implements Command {

    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    public <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
