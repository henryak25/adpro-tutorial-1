package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(300);
        assertEquals(product, productService.create(product));
    }
    @Test
    void testProductFindAll(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);
        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(200);
        productService.create(product2);
        List<Product> listProduct = productService.findAll();
        assertEquals(product1,listProduct.get(0));
        assertEquals(product2,listProduct.get(1));
    }
    @Test
    void testProductFindAllIsEmpty(){
        List<Product> listProduct = productService.findAll();
        assertTrue(listProduct.isEmpty());
    }
    @Test
    void testEditProduct(){
        Product beforeProduct = new Product();
        beforeProduct.setProductName("Ayam");
        beforeProduct.setProductQuantity(100);
        productService.create(beforeProduct);
        Product productUpdate = new Product();
        productUpdate.setProductName("Kucing");
        productUpdate.setProductQuantity(200);
        productUpdate.setProductId(beforeProduct.getProductId());
        productService.edit(productUpdate);
        assertEquals("Kucing", beforeProduct.getProductName());
        assertEquals(200, beforeProduct.getProductQuantity());
    }
    @Test
    void testGetFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productService.create(product);
        assertTrue(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")!=null);
    }
    @Test
    void testGetNotFound(){
        Product product = new Product();
        product.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        productService.create(product);
        assertFalse(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")!=null);
    }

    @Test
    void testDeleteFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productService.create(product);
        productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testDeleteNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productService.create(product);
        assertThrows(IllegalArgumentException.class, () -> {
            productService.delete("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        });
    }

    @Test
    void testEditNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> {
            productService.edit(null);
        });
    }

    @Test
    void testDeleteNullProductId() {
        assertThrows(IllegalArgumentException.class, () -> {
            productService.delete(null);
        });
    }

    @Test
    void testDeleteBlankProductId() {
        assertThrows(IllegalArgumentException.class, () -> {
            productService.delete("   ");
        });
    }
}