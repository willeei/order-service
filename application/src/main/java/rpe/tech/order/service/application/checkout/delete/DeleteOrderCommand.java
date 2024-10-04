package rpe.tech.order.service.application.checkout.delete;

public record DeleteOrderCommand(
        String id
) {

    public static DeleteOrderCommand with(
            final String id
    ) {
        return new DeleteOrderCommand(id);
    }
}
