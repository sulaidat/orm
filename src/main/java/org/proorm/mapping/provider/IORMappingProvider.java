package org.proorm.mapping.provider;

import org.proorm.mapping.ORMapping;

/**
 * Interface of objects responsible for creating and returning object-relational mappings to the ORM. Mappings should be
 * cached.
 */
public interface IORMappingProvider {

    /**
     * Returns the mapping for the given class.
     *
     * @param clazz
     * @return
     */
    ORMapping getORMapping(Class<?> clazz);

}
