package rpe.tech.order.service.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rpe.tech.order.service.application.product.create.CreateProductCommand;
import rpe.tech.order.service.application.product.create.CreateProductUseCase;
import rpe.tech.order.service.application.product.delete.DeleteProductUseCase;
import rpe.tech.order.service.application.product.retrieve.get.GetProductByIdUseCase;
import rpe.tech.order.service.application.product.retrieve.list.ListProductUseCase;
import rpe.tech.order.service.application.product.update.UpdateProductCommand;
import rpe.tech.order.service.application.product.update.UpdateProductUseCase;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.infrastructure.api.ProductAPI;
import rpe.tech.order.service.infrastructure.product.models.CreateProductRequest;
import rpe.tech.order.service.infrastructure.product.models.ProductListResponse;
import rpe.tech.order.service.infrastructure.product.models.ProductResponse;
import rpe.tech.order.service.infrastructure.product.models.UpdateProductRequest;
import rpe.tech.order.service.infrastructure.product.presenters.ProductApiPresenter;

import java.net.URI;
import java.util.Objects;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final ListProductUseCase listProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(
            final CreateProductUseCase createProductUseCase,
            final DeleteProductUseCase deleteProductUseCase,
            final GetProductByIdUseCase getProductByIdUseCase,
            final ListProductUseCase listProductUseCase,
            final UpdateProductUseCase updateProductUseCase
    ) {
        this.createProductUseCase = Objects.requireNonNull(createProductUseCase);
        this.deleteProductUseCase = Objects.requireNonNull(deleteProductUseCase);
        this.getProductByIdUseCase = Objects.requireNonNull(getProductByIdUseCase);
        this.listProductUseCase = Objects.requireNonNull(listProductUseCase);
        this.updateProductUseCase = Objects.requireNonNull(updateProductUseCase);
    }

    @Override
    public ResponseEntity<?> createProduct(final CreateProductRequest input) {
        final var aCommand = CreateProductCommand.with(
                input.name(),
                input.price(),
                input.active() != null ? input.active() : Boolean.TRUE
        );

        final var output = this.createProductUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/products/" + output.id())).build();
    }

    @Override
    public Pagination<ProductListResponse> listProducts(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listProductUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(ProductApiPresenter::present);
    }

    @Override
    public ProductResponse getById(final String id) {
        return ProductApiPresenter.present(this.getProductByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateProductRequest input) {
        final var aCommand = UpdateProductCommand.with(
                id,
                input.name(),
                input.price(),
                input.active() != null ? input.active() : Boolean.TRUE
        );

        final var output = this.updateProductUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteProductUseCase.execute(id);
    }
}
