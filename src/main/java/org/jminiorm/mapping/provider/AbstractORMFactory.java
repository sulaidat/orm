package org.jminiorm.mapping.provider;

import org.jminiorm.mapping.ORMapping;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractORMFactory implements IORMappingProvider {

    private Map<Class<?>, ORMapping> mappings = new HashMap<>();

    private AbstractORMFactory() {

    }

    @Override
    public ORMapping getORMapping(Class<?> clazz) {
        if (!mappings.containsKey(clazz))
            mappings.put(clazz, createORMapping(clazz));
        return mappings.get(clazz);
    }

    protected abstract ORMapping createORMapping(Class<?> clazz);

}
