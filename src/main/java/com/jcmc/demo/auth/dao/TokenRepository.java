package com.jcmc.demo.auth.dao;

import com.jcmc.demo.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t.id_token, t.token, t.token_type, t.revoked, t.expired, t.id_user \s
      from tokens t inner join users u \s
      on t.id_user = u.id_user \s
      where u.id_user = :id_user and (t.expired = 0 or t.revoked = 0) \s
      """, nativeQuery=true)
    List<Token> findAllValidTokenByUser(@Param("id_user") Integer idUser);


    Optional<Token> findByToken(String token);
}
