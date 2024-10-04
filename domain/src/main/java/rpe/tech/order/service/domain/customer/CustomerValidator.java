package rpe.tech.order.service.domain.customer;

import rpe.tech.order.service.domain.validation.Error;
import rpe.tech.order.service.domain.validation.ValidationHandler;
import rpe.tech.order.service.domain.validation.Validator;

public class CustomerValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 1;

    private final Customer customer;

    protected CustomerValidator(final Customer aCustomer, final ValidationHandler aHandler) {
        super(aHandler);
        this.customer = aCustomer;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkAddressConstraints();
    }

    private void checkNameConstraints() {
        String name = this.customer.name();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 1 and 255 characters"));
        }
    }

    private void checkAddressConstraints() {
        final var address = this.customer.address();
        if (address == null) {
            this.validationHandler().append(new Error("'address' should not be null"));
        }
    }
}