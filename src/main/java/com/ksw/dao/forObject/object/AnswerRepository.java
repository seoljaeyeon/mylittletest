package com.ksw.dao.forObject.object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
