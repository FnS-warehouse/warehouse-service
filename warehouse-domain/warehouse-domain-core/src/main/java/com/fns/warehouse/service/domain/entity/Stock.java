package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.AggregateRoot;
import com.fns.domain.valueobject.StockId;
import com.fns.domain.valueobject.WarehouseId;
import com.fns.domain.valueobject.ProductId;
import com.fns.warehouse.service.domain.exception.InsufficientQuantityException;
import com.fns.warehouse.service.domain.valueobject.TransferStatus;
import com.fns.warehouse.service.domain.valueobject.TransferType;

public class Stock extends AggregateRoot<StockId> {

    private final WarehouseId warehouseId;
    private final ProductId productId;
    private int totalQuantity;
    private int reservedQuantity;
    private int availableQuantity;

    private TransferStatus transferStatus;
    private TransferType transferType;

    private Stock(Builder builder) {
        super.setId(builder.stockId);
        this.warehouseId = builder.warehouseId;
        this.productId = builder.productId;
        this.totalQuantity = builder.totalQuantity;
        this.reservedQuantity = builder.reservedQuantity;
        this.availableQuantity = builder.availableQuantity;
        this.transferStatus = builder.transferStatus;
        this.transferType = builder.transferType;
        validateInvariants();
    }

    public static Builder builder() {
        return new Builder();
    }

    public void increaseQty(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to increase must be non-negative.");
        }
        this.totalQuantity += qty;
        this.availableQuantity += qty;
        validateInvariants();
    }

    public void reduceQty(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to reduce must be non-negative.");
        }
        if (qty > this.availableQuantity) {
            throw new InsufficientQuantityException("Not enough available quantity to reduce.");
        }
        this.totalQuantity -= qty;
        this.availableQuantity -= qty;
        validateInvariants();
    }

    public void reserve(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to reserve must be non-negative.");
        }
        if (qty > this.availableQuantity) {
            throw new InsufficientQuantityException("Not enough available quantity to reserve.");
        }
        this.reservedQuantity += qty;
        this.availableQuantity -= qty;
        validateInvariants();
    }

    public void finalizeReservation(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to finalize reservation must be non-negative.");
        }
        if (qty > this.reservedQuantity) {
            throw new InsufficientQuantityException("Not enough reserved quantity to finalize.");
        }
        this.reservedQuantity -= qty;
        this.totalQuantity -= qty;
        validateInvariants();
    }

    public void cancel(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to cancel must be non-negative.");
        }
        if (qty > this.reservedQuantity) {
            throw new InsufficientQuantityException("Not enough reserved quantity to release.");
        }
        this.reservedQuantity -= qty;
        this.availableQuantity += qty;
        validateInvariants();
    }

    private void validateInvariants() {
        if (this.totalQuantity != this.reservedQuantity + this.availableQuantity) {
            throw new IllegalStateException("Total quantity must equal reserved plus available quantity.");
        }
    }

    public void initiateTransfer(TransferType transferType) {
        if (this.transferStatus != null && this.transferStatus != TransferStatus.CANCELLED) {
            throw new IllegalStateException("There is already an ongoing transfer for this stock.");
        }
        this.transferType = transferType;
        this.transferStatus = TransferStatus.PENDING;
    }

    public void approveTransfer() {
        if (this.transferStatus != TransferStatus.PENDING) {
            throw new IllegalStateException("Transfer cannot be approved in its current state.");
        }
        this.transferStatus = TransferStatus.APPROVED;
    }

    public void cancelTransfer() {
        if (this.transferStatus != TransferStatus.PENDING) {
            throw new IllegalStateException("Only pending transfers can be canceled.");
        }
        this.transferStatus = TransferStatus.CANCELLED;
    }

    // Getters
    public WarehouseId getWarehouseId() { return warehouseId; }
    public ProductId getProductId() { return productId; }
    public int getTotalQuantity() { return totalQuantity; }
    public int getReservedQuantity() { return reservedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    // Builder class with added transfer fields
    public static class Builder {
        private StockId stockId;
        private WarehouseId warehouseId;
        private ProductId productId;
        private int totalQuantity;
        private int reservedQuantity;
        private int availableQuantity;
        private TransferStatus transferStatus;
        private TransferType transferType;

        public Builder stockId(StockId stockId) {
            this.stockId = stockId;
            return this;
        }

        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder totalQuantity(int totalQuantity) {
            this.totalQuantity = totalQuantity;
            return this;
        }

        public Builder reservedQuantity(int reservedQuantity) {
            this.reservedQuantity = reservedQuantity;
            return this;
        }

        public Builder availableQuantity(int availableQuantity) {
            this.availableQuantity = availableQuantity;
            return this;
        }

        public Builder transferStatus(TransferStatus transferStatus) {
            this.transferStatus = transferStatus;
            return this;
        }

        public Builder transferType(TransferType transferType) {
            this.transferType = transferType;
            return this;
        }

        public Stock build() {
            if (stockId == null) {
                throw new IllegalStateException("StockId must be provided.");
            }
            if (warehouseId == null) {
                throw new IllegalStateException("WarehouseId must be provided.");
            }
            if (productId == null) {
                throw new IllegalStateException("ProductId must be provided.");
            }
            if (totalQuantity < 0 || reservedQuantity < 0 || availableQuantity < 0) {
                throw new IllegalStateException("Quantities must be non-negative.");
            }
            if (totalQuantity != reservedQuantity + availableQuantity) {
                throw new IllegalStateException("Total quantity must equal reserved plus available quantity.");
            }
            return new Stock(this);
        }
    }
}

