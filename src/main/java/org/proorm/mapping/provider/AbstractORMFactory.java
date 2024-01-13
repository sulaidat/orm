package org.proorm.mapping.provider;

import org.proorm.mapping.ORMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for mapping providers.
 */
public abstract class AbstractORMFactory implements IORMappingProvider {

    private Map<Class<?>, ORMapping> mappings = new HashMap<>();

    @Override
    public ORMapping getORMapping(Class<?> clazz) {
        if (!mappings.containsKey(clazz))
            mappings.put(clazz, createORMapping(clazz));
        return mappings.get(clazz);
    }

    /**
     * Implement this to create the object relational mapping for the given class.
     *
     * @param clazz
     * @return
     */
    protected abstract ORMapping createORMapping(Class<?> clazz);

}
