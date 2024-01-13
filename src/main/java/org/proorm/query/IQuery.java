package org.proorm.query;

import org.proorm.queryTarget.IQueryTarget;

public interface IQuery {

    void setQueryTarget(IQueryTarget target);

    IQueryTarget getQueryTarget();

}
