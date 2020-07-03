package com.imagestore.service;

import com.imagestore.domain.BillingAddress;
import com.imagestore.domain.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
