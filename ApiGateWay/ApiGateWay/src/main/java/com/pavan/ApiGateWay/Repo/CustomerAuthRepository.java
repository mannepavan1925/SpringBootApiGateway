package com.pavan.ApiGateWay.Repo;

import com.pavan.ApiGateWay.beans.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerAuthRepository extends JpaRepository<User, String> {
    public List<User> findByName(String name) ;
}
