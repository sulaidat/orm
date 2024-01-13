package org.proorm.query.orm;

import org.proorm.queryTarget.IQueryTarget;
import org.proorm.mapping.ORMapping;
import org.proorm.query.AbstractQuery;

public abstract class AbstractORMQuery<T> extends AbstractQuery implements IORMQuery<T> {

    private Class<T> clazz;

    public AbstractORMQuery(IQueryTarget target) {
        super(target);
    }

    @Override
    public IORMQuery<T> forClass(Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }

    protected Class<T> getTargetClass() {
        return clazz;
    }

    protected ORMapping getMapping() {
        return getQueryTarget().getConfig().getORMappingProvider().getORMapping(getTargetClass());
    }
}
