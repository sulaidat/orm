package org.proorm.result;

import org.proorm.exception.DBException;
import org.proorm.exception.UnexpectedNumberOfItemsException;

import java.util.List;

/**
 * Represent the result of a select query casted into type T.
 *
 * @param <T>
 */
public interface IResult<T> {

    /**
     * Extracts the first result of the result set. Throws an exception if there is more than or less than one element
     * in the result set.
     *
     * @return
     * @throws UnexpectedNumberOfItemsException when there are more than one or zero items in the result set.
     * @throws DBException
     */
    T one() throws UnexpectedNumberOfItemsException, DBException;

    /**
     * Extracts the first result of the result set.
     *
     * @return
     * @throws DBException
     */
    T first() throws DBException;

    /**
     * Returns all the items in the result set.
     *
     * @return
     * @throws DBException
     */
    List<T> list() throws DBException;

}
