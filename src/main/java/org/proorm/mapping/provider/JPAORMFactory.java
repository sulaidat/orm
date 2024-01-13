package org.proorm.mapping.provider;

import org.proorm.mapping.JPAORMapping;
import org.proorm.mapping.ORMapping;

/**
 * A mapping provider that uses JPA annotations.
 */
public class JPAORMFactory extends AbstractORMFactory {

    @Override
    protected ORMapping createORMapping(Class<?> clazz) {
        return new JPAORMapping(clazz);
    }

}
