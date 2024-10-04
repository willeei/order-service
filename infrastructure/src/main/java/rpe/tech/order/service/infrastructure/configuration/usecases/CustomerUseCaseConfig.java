package rpe.tech.order.service.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rpe.tech.order.service.application.customer.create.CreateCustomerUseCase;
import rpe.tech.order.service.application.customer.create.DefaultCreateCustomerUseCase;
import rpe.tech.order.service.application.customer.delete.DefaultDeleteCustomerUseCase;
import rpe.tech.order.service.application.customer.delete.DeleteCustomerUseCase;
import rpe.tech.order.service.application.customer.retrieve.get.DefaultGetCustomerByIdUseCase;
import rpe.tech.order.service.application.customer.retrieve.get.GetCustomerByIdUseCase;
import rpe.tech.order.service.application.customer.retrieve.list.DefaultListCustomerUseCase;
import rpe.tech.order.service.application.customer.retrieve.list.ListCustomerUseCase;
import rpe.tech.order.service.application.customer.update.DefaultUpdateCustomerUseCase;
import rpe.tech.order.service.application.customer.update.UpdateCustomerUseCase;
import rpe.tech.order.service.domain.customer.CustomerGateway;

@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;

    public CustomerUseCaseConfig(final CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase() {
        return new DefaultCreateCustomerUseCase(customerGateway);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase() {
        return new DefaultUpdateCustomerUseCase(customerGateway);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase() {
        return new DefaultDeleteCustomerUseCase(customerGateway);
    }

    @Bean
    public GetCustomerByIdUseCase getCustomerByIdUseCase() {
        return new DefaultGetCustomerByIdUseCase(customerGateway);
    }

    @Bean
    public ListCustomerUseCase listCustomerUseCase() {
        return new DefaultListCustomerUseCase(customerGateway);
    }
}
