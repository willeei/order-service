package rpe.tech.order.service.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}