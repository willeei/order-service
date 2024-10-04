package rpe.tech.order.service.application;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN anIn);
}