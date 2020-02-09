package th.go.dss.register.repositories;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import th.go.dss.register.models.Registration;

@Repository
public interface RegistrationRepository extends PagingAndSortingRepository<Registration, Integer>, QuerydslPredicateExecutor<Registration> {

}
