package rpe.tech.order.service.application.customer.create;

public record CreateCustomerCommand(
        String name,
        boolean active,
        int rewardPoints,
        String street,
        int number,
        String city,
        String zipCode
) {

    public static CreateCustomerCommand with(
            String name,
            boolean active,
            int rewardPoints,
            String street,
            int number,
            String city,
            String zipCode
    ) {
        return new CreateCustomerCommand(name, active, rewardPoints, street, number, city, zipCode);
    }
}
