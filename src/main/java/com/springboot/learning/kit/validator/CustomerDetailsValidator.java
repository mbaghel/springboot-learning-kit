package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.dto.request.CustomerDetailsRequest;
import com.springboot.learning.kit.exception.OrderValidationException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsValidator implements Validator<CustomerDetailsRequest> {

    private static final String VALID_PHONE_REGEX = "^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{3}\\)?[-.\\s]?)?(\\d[-.\\s]?){6,9}\\d$";

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new OrderValidationException("Customer name cannot be null or empty");
        }
    }

    private void validateEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            throw new OrderValidationException("Invalid email provided: " + email);
        }
    }

    private void validatePhoneNumber(String phoneNum) {
        if (phoneNum == null || phoneNum.isEmpty()) {
            throw new OrderValidationException("Phone number cannot be empty");
        }
        if (!phoneNum.matches(VALID_PHONE_REGEX)) {
            throw new OrderValidationException("Invalid phone number provided: " + phoneNum);
        }
    }

    @Override
    public void validate(CustomerDetailsRequest customerDetails) throws OrderValidationException {
        validateName(customerDetails.getName());
        validateEmail(customerDetails.getEmail());
        validatePhoneNumber(customerDetails.getPhone());
    }
}
