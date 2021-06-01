package edu.sapientia.requestmanager.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestRegistryNumberGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        String currentDate = DateTimeFormatter.ofPattern("uuuu-MM-dd").format(LocalDate.now());

        String query = String.format("select %s from %s where %s like '%s%%'",
                session.getEntityPersister(o.getClass().getName(), o).getIdentifierPropertyName(),
                o.getClass().getSimpleName(),
                session.getEntityPersister(o.getClass().getName(), o).getIdentifierPropertyName(),
                currentDate);

        Long count = session.createQuery(query).stream().count();

        return currentDate.concat("/").concat(String.valueOf(1L + count));
    }
}
