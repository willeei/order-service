package rpe.tech.order.service.application.customer.update;

public record UpdateCustomerCommand(
        String id,
        String name,
        boolean active,
        int rewardPoints,
        String street,
        int number,
        String city,
        String zipCode
) {

    public static UpdateCustomerCommand with(
            String id,
            String name,
            boolean active,
            int rewardPoints,
            String street,
            int number,
            String city,
            String zipCode
    ) {
        return new UpdateCustomerCommand(id, name, active, rewardPoints, street, number, city, zipCode);
    }
}
