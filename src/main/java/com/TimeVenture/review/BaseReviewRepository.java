package com.TimeVenture.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*Review Repository가 구현하는 인터페이스
@NoRepositoryBean : 해당 인터페이스에 대해 실제로 동작하는 Repository 인터페이스가 아니라는 것
-> 상속 받는 자식객체의 인터페이스는 @Repository 에너테이션을 이용하여 실제로 해당 역할을 수행하는 것을
명시 함

* extends JpaRepository : JPA 리포지토리를 구현하는 인터페이스
JPA에서 엔티티를 저장, 검색, 수정, 삭제하기 위한 다양한 메서드를 제공
ex) save, findById, findAll, deleteById 등의 메서드가 포함 */
@NoRepositoryBean
public interface BaseReviewRepository<T extends BaseReview, ID> extends JpaRepository<T, ID> {

/*    @Query("SELECT t FROM #{#entityName} t WHERE t.tId = :id")
    List<T> findAllByTId(@Param("id") int id);*/
}