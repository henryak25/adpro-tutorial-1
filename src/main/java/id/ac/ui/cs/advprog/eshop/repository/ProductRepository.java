package id.ac.ui.cs.advprog.eshop.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Product update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        Product oldProduct = findById(product.getProductId());
        if (oldProduct != null) {
            oldProduct.setProductName(product.getProductName());
            oldProduct.setProductQuantity(product.getProductQuantity());
            System.out.println("Product Edited");
            return oldProduct;
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public Product delete(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }

        Product product = findById(productId);
        if (product != null) {
            productData.remove(product);
            System.out.println("Product Deleted");
            return product;
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }
}