package rpe.tech.order.service.application.checkout.retrieve.get;

import rpe.tech.order.service.application.UseCase;

public abstract sealed class GetOrderByIdUseCase extends UseCase<String, OrderOutput>
        permits DefaultGetOrderByIdUseCase {

}
