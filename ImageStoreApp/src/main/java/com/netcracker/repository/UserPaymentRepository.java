package com.imagestore.repository;


import org.springframework.data.repository.CrudRepository;

import com.imagestore.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment,Long>{

}
