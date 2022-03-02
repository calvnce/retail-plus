package inventory.service.supplier;

import inventory.data.Repository;
import inventory.data.supplier.SupplierRepository;
import inventory.model.Supplier;
import inventory.service.Service;

import java.util.List;

public class SupplierService implements Service<Supplier> {
        private final Repository<Supplier> supplierRepository;
    public SupplierService() {
            this.supplierRepository = new SupplierRepository();
    }
    @Override
    public void add(Supplier supplier) {
        this.supplierRepository.add(supplier);
    }

    @Override
    public void edit(Supplier supplier) {
        this.supplierRepository.edit(supplier);
    }

    @Override
    public void delete(long id) {
        this.supplierRepository.delete(id);
    }

    @Override
    public Supplier getById(long id) {
        return this.supplierRepository.getById(id);
    }

    @Override
    public List<Supplier> getAll() {
        return this.supplierRepository.getAll();
    }

    @Override
    public List<Supplier> filter(String name) {
        return this.supplierRepository.filter(name);
    }
}
