package org.proorm.query;

import org.proorm.queryTarget.IQueryTarget;

/**
 * Interface of all queries.
 */
public interface IQuery {

    /**
     * Sets the target of this query.
     *
     * @param target
     */
    void setQueryTarget(IQueryTarget target);

    /**
     * Returns the target of this query.
     *
     * @return
     */
    IQueryTarget getQueryTarget();

}
