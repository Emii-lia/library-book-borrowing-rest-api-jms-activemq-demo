package tech.java.workflowejbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tech.java.workflowejbs.entity.Member;

@CrossOrigin
@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Long> {

}
