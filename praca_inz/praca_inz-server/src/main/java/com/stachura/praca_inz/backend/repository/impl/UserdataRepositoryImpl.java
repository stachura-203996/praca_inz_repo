package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.Userdata_;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserdataRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserdataRepositoryImpl extends AbstractRepository<Userdata> implements UserdataRepository {

    protected UserdataRepositoryImpl() {
        super(Userdata.class);
    }


    public Userdata findByEmail(String email){
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Userdata> query = builder.createQuery(Userdata.class);

        Root<Userdata> root = query.from(Userdata.class);
        query.select(root).distinct(true);

        Predicate emailPredicate = builder.equal(root.get(Userdata_.EMAIL), email);
                query.where(builder.and(emailPredicate));

        return DataAccessUtils.singleResult(getEntityManager().createQuery(query).getResultList());
    }
}
