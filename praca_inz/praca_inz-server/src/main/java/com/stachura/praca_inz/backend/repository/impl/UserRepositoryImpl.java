package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.exception.repository.*;
import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.User_;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    protected UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User find(String name) {
 
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);

            Root<User> root = query.from(User.class);
            query.select(root).distinct(true);
            Predicate idPredicate = builder.equal(root.get(User_.USERNAME), name);
            query.where(builder.and(idPredicate));

            return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
       
    }

}
