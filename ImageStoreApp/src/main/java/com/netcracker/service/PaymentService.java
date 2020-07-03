package com.imagestore.service;

import com.imagestore.domain.Payment;
import com.imagestore.domain.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
