package com.osagie.customer.app.helpers;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * Created by OSAGIE on 5/1/2021
 */
public class AccountNumberGenerator implements IdentifierGenerator {
    private final String SUFFIX = "-01";
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream<String> ids = session.createQuery(query).stream();

        Long max = ids.map(o -> o.replace(SUFFIX, ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(1000000000L);

        return (max + 1) + SUFFIX;
    }
}
