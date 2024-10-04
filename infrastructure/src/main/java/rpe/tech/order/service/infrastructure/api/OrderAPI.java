package rpe.tech.order.service.infrastructure.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.infrastructure.checkout.models.CreateOrderRequest;
import rpe.tech.order.service.infrastructure.checkout.models.OrderListResponse;
import rpe.tech.order.service.infrastructure.checkout.models.OrderResponse;
import rpe.tech.order.service.infrastructure.checkout.models.UpdateOrderRequest;

@RequestMapping(value = "order")
@Tag(name = "Orders")
public interface OrderAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest input);

    @GetMapping
    @Operation(summary = "List all order paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    Pagination<OrderListResponse> listOrders(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a customer by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    OrderResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a customer by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "404", description = "Order was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody UpdateOrderRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a customer by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),})
    void deleteById(@PathVariable(name = "id") String id);
}
