package inventory.service.product;

import inventory.data.Repository;
import inventory.data.product.ProductRepository;
import inventory.model.Product;
import inventory.service.Service;

import java.util.List;

public class ProductService implements Service<Product> {
    private final Repository <Product> productRepository;
    public ProductService() {
        this.productRepository = new ProductRepository();
    }
    @Override
    public void add(Product product) {
        this.productRepository.add(product);
    }

    @Override
    public void edit(Product product) {
        this.productRepository.edit(product);
    }

    @Override
    public void delete(long id) {
        this.productRepository.delete(id);
    }

    @Override
    public Product getById(long id) {
        return this.productRepository.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.getAll();
    }

    @Override
    public List<Product> filter(String name) {
        return this.productRepository.filter(name);
    }
}
