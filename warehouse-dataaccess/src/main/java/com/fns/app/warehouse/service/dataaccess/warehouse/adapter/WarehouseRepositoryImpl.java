//import com.fns.warehouse.service.dataaccess.warehouse.repository.WarehouseJpaRepository;
//import com.fns.warehouse.service.domain.entity.Warehouse;
//import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
//import com.fns.warehouse.service.domain.valueobject.TrackingId;
//import org.springframework.stereotype.Component;
//import java.util.Optional;
//
//@Component
//public class WarehouseRepositoryImpl implements WarehouseRepository {
//
//    private final WarehouseJpaRepository warehouseJpaRepository;
//    private final WarehouseDataAccessMapper warehouseDataAccessMapper;
//
//    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository, WarehouseDataAccessMapper warehouseDataAccessMapper) {
//        this.warehouseJpaRepository = warehouseJpaRepository;
//        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
//    }
//
//    @Override
//    public Warehouse save(Warehouse warehouse) {
//        return warehouseDataAccessMapper.warehouseEntityToWarehouse(warehouseJpaRepository
//                .save(warehouseDataAccessMapper.warehouseToWarehouseEntity(warehouse)));
//    }
//
//    @Override
//    public Optional<Warehouse> findByTrackingId(TrackingId trackingId) {
//        return warehouseJpaRepository.findByTrackingId(trackingId.getValue())
//                .map(warehouseDataAccessMapper::warehouseEntityToWarehouse);
//    }
//}
