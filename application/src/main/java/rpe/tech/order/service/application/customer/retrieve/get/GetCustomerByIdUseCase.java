package rpe.tech.order.service.application.customer.retrieve.get;

import rpe.tech.order.service.application.UseCase;

public abstract sealed class GetCustomerByIdUseCase extends UseCase<String, CustomerOutput>
        permits DefaultGetCustomerByIdUseCase {

}
