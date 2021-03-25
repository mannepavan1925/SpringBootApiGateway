package com.pavan.ApiGateWay.service;

import com.pavan.ApiGateWay.Repo.CustomerAuthRepository;
import com.pavan.ApiGateWay.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerAuthService {
    @Autowired
    private CustomerAuthRepository customerAuthRepository;

    public List<User> findByName(String name) {
       List<User> result =customerAuthRepository.findByName(name);
        if (result==null)
            throw new EmptyResultDataAccessException(1);

        return result;
    }

//    public QuerySideCustomer findByEmailAndPassword(String email, String password) {
//        QuerySideCustomer result =  DataAccessUtils.uniqueResult(customerAuthRepository.findByEmailAndPassword(email, password));
//        if (result==null)
//            throw new EmptyResultDataAccessException(1);
//
//        return result;
//    }
}
