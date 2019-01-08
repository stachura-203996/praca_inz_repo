package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.model.security.UserRole_;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRoleRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

@Repository
public class UserRoleRepositoryImpl extends AbstractRepository<UserRole> implements UserRoleRepository {

    protected UserRoleRepositoryImpl() {
        super(UserRole.class);
    }

    @Override
    public UserRole find(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);

        Root<UserRole> root = query.from(UserRole.class);
        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(UserRole_.name), name);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
    }
}
