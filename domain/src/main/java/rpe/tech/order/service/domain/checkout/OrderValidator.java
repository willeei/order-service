package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.validation.Error;
import rpe.tech.order.service.domain.validation.ValidationHandler;
import rpe.tech.order.service.domain.validation.Validator;

public class OrderValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 1;

    private final Order order;

    protected OrderValidator(final Order anOrder, final ValidationHandler aHandler) {
        super(aHandler);
        this.order = anOrder;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkOrderItemConstraints();
    }

    private void checkNameConstraints() {
        final var customerId = this.order.customerId();
        if (customerId == null) {
            this.validationHandler().append(new Error("'customerId' should not be null"));
        }
    }

    private void checkOrderItemConstraints() {
        final var items = this.order.items();
        if (items == null) {
            this.validationHandler().append(new Error("'items' should not be null"));
            return;
        }

        if (items.isEmpty()) {
            this.validationHandler().append(new Error("'items' should not be empty"));
            return;
        }

        for (OrderItem item : this.order.items()) {
            if (item.quantity() <= 0) {
                this.validationHandler().append(new Error("'quantity' should be greater than 0"));
                return;
            }
        }
    }
}