package org.proorm.query.generic;

import org.proorm.queryTarget.IQueryTarget;
import org.proorm.query.AbstractQuery;

public abstract class AbstractGenericQuery extends AbstractQuery implements IGenericQuery {

    public AbstractGenericQuery(IQueryTarget target) {
        super(target);
    }

}
