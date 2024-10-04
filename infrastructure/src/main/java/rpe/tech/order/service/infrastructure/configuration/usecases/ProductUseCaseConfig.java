package rpe.tech.order.service.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rpe.tech.order.service.application.product.create.CreateProductUseCase;
import rpe.tech.order.service.application.product.create.DefaultCreateProductUseCase;
import rpe.tech.order.service.application.product.delete.DefaultDeleteProductUseCase;
import rpe.tech.order.service.application.product.delete.DeleteProductUseCase;
import rpe.tech.order.service.application.product.retrieve.get.DefaultGetProductByIdUseCase;
import rpe.tech.order.service.application.product.retrieve.get.GetProductByIdUseCase;
import rpe.tech.order.service.application.product.retrieve.list.DefaultListProductUseCase;
import rpe.tech.order.service.application.product.retrieve.list.ListProductUseCase;
import rpe.tech.order.service.application.product.update.DefaultUpdateProductUseCase;
import rpe.tech.order.service.application.product.update.UpdateProductUseCase;
import rpe.tech.order.service.domain.product.ProductGateway;

@Configuration
public class ProductUseCaseConfig {

    private final ProductGateway productGateway;

    public ProductUseCaseConfig(final ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Bean
    public CreateProductUseCase createProductUseCase() {
        return new DefaultCreateProductUseCase(productGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase() {
        return new DefaultUpdateProductUseCase(productGateway);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase() {
        return new DefaultDeleteProductUseCase(productGateway);
    }

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase() {
        return new DefaultGetProductByIdUseCase(productGateway);
    }

    @Bean
    public ListProductUseCase listProductUseCase() {
        return new DefaultListProductUseCase(productGateway);
    }
}
