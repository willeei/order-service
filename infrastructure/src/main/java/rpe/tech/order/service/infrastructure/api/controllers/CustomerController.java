package rpe.tech.order.service.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rpe.tech.order.service.application.customer.create.CreateCustomerCommand;
import rpe.tech.order.service.application.customer.create.CreateCustomerUseCase;
import rpe.tech.order.service.application.customer.delete.DeleteCustomerUseCase;
import rpe.tech.order.service.application.customer.retrieve.get.GetCustomerByIdUseCase;
import rpe.tech.order.service.application.customer.retrieve.list.ListCustomerUseCase;
import rpe.tech.order.service.application.customer.update.UpdateCustomerCommand;
import rpe.tech.order.service.application.customer.update.UpdateCustomerUseCase;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.infrastructure.api.CustomerAPI;
import rpe.tech.order.service.infrastructure.customer.models.CreateCustomerRequest;
import rpe.tech.order.service.infrastructure.customer.models.CustomerListResponse;
import rpe.tech.order.service.infrastructure.customer.models.CustomerResponse;
import rpe.tech.order.service.infrastructure.customer.models.UpdateCustomerRequest;
import rpe.tech.order.service.infrastructure.customer.presenters.CustomerApiPresenter;

import java.net.URI;
import java.util.Objects;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final ListCustomerUseCase listCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase,
            final DeleteCustomerUseCase deleteCustomerUseCase,
            final GetCustomerByIdUseCase getCustomerByIdUseCase,
            final ListCustomerUseCase listCustomerUseCase,
            final UpdateCustomerUseCase updateCustomerUseCase
    ) {
        this.createCustomerUseCase = Objects.requireNonNull(createCustomerUseCase);
        this.deleteCustomerUseCase = Objects.requireNonNull(deleteCustomerUseCase);
        this.getCustomerByIdUseCase = Objects.requireNonNull(getCustomerByIdUseCase);
        this.listCustomerUseCase = Objects.requireNonNull(listCustomerUseCase);
        this.updateCustomerUseCase = Objects.requireNonNull(updateCustomerUseCase);
    }

    @Override
    public ResponseEntity<?> createCustomer(final CreateCustomerRequest input) {
        final var aCommand = CreateCustomerCommand.with(
                input.name(),
                input.isActive() != null ? input.isActive() : Boolean.TRUE,
                input.rewardPoints(),
                input.address().street(),
                input.address().number(),
                input.address().city(),
                input.address().zipCode()
        );

        final var output = this.createCustomerUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/customer/" + output.id())).build();
    }

    @Override
    public Pagination<CustomerListResponse> listCustomers(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listCustomerUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CustomerApiPresenter::present);
    }

    @Override
    public CustomerResponse getById(final String id) {
        return CustomerApiPresenter.present(this.getCustomerByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCustomerRequest input) {
        final var aCommand = UpdateCustomerCommand.with(
                id,
                input.name(),
                input.isActive() != null ? input.isActive() : Boolean.TRUE,
                input.rewardPoints(),
                input.address().street(),
                input.address().number(),
                input.address().city(),
                input.address().zipCode()
        );

        final var output = this.updateCustomerUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteCustomerUseCase.execute(id);
    }
}
