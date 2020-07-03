package com.imagestore.service;

import com.imagestore.domain.ShippingAddress;
import com.imagestore.domain.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
