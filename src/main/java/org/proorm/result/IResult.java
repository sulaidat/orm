package org.proorm.result;

import org.proorm.exception.DBException;
import org.proorm.exception.UnexpectedNumberOfItemsException;

import java.util.List;

public interface IResult<T> {

    T one() throws UnexpectedNumberOfItemsException, DBException;

    T first() throws DBException;

    List<T> list() throws DBException;

}
