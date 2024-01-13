package org.proorm.mapping.provider;

import org.proorm.mapping.ORMapping;

public interface IORMappingProvider {
    ORMapping getORMapping(Class<?> clazz);

}
