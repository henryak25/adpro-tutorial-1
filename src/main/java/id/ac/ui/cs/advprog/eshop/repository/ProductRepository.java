package id.ac.ui.cs.advprog.eshop.repository;

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

    public void edit(Product product) {
        try {
            Product oldProduct = findById(product.getProductId());
            if (oldProduct != null) {
                oldProduct.setProductName(product.getProductName());
                oldProduct.setProductQuantity(product.getProductQuantity());
            } else {
                throw new IllegalArgumentException("Product not found");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(String productId) {
        try {
            Product product = findById(productId);
            if (product != null) {
                productData.remove(product);
            } else {
                throw new IllegalArgumentException("Product not found");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}