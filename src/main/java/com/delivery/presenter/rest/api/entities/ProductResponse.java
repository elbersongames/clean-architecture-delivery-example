package com.delivery.presenter.rest.api.entities;

import com.delivery.core.domain.Product;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

import static com.delivery.data.db.jpa.entities.IdConverter.convertId;

@Value
public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final Long storeId;

    private static ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                convertId(product.getId()),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                convertId(product.getStore().getId()));
    }

    public static List<ProductResponse> fromProduct(List<Product> products) {
        return products
                .parallelStream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }
}