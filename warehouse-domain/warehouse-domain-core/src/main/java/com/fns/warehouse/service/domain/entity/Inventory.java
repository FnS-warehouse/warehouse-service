package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueObject.*;
import com.fns.warehouse.service.domain.exception.InsufficientQuantityException;

import java.time.LocalDateTime;

public class Inventory extends AggregateRoot<InventoryId> {

    private final WarehouseId warehouseId;
    private final ProductId productId;
    private int totalQuantity;
    private int reservedQuantity;
    private int availableQuantity;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Inventory(Builder builder) {
        super.setId(builder.inventoryId);
        this.warehouseId = builder.warehouseId;
        this.productId = builder.productId;
        this.totalQuantity = builder.totalQuantity;
        this.reservedQuantity = builder.reservedQuantity;
        this.availableQuantity = builder.availableQuantity;
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
        updateTimestamp();
        createJournalEntry(qty, JournalReason.ADDED);
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
        updateTimestamp();
        createJournalEntry(qty, JournalReason.REDUCED);
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
        updateTimestamp();
        createJournalEntry(qty, JournalReason.RESERVED);
    }

    public void finalizeReservation(int qty, JournalReason reason) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity to finalize reservation must be non-negative.");
        }
        if (qty > this.reservedQuantity) {
            throw new InsufficientQuantityException("Not enough reserved quantity to finalize.");
        }
        this.reservedQuantity -= qty;
        this.totalQuantity -= qty;
        updateTimestamp();
        createJournalEntry(qty, reason);
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
        updateTimestamp();
        createJournalEntry(qty, JournalReason.CANCELED);

    }

    private void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    private void validateInvariants() {
        if (this.totalQuantity != this.reservedQuantity + this.availableQuantity) {
            throw new IllegalStateException("Total quantity must equal reserved plus available quantity.");
        }
    }

    private void createJournalEntry(int qtyChange, JournalReason reason) {
        InventoryJournal journalEntry = InventoryJournal.builder()
                .inventoryId(this.getId())
                .totalQuantity(this.totalQuantity)
                .reservedQuantity(this.reservedQuantity)
                .availableQuantity(this.availableQuantity)
                .reason(reason)
                .build();
        journalEntry.addJournal();
    }

    // Getters
    public WarehouseId getWarehouseId() { return warehouseId; }
    public ProductId getProductId() { return productId; }
    public int getTotalQuantity() { return totalQuantity; }
    public int getReservedQuantity() { return reservedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public LocalDateTime getDeletedAt() { return deletedAt; }

    // Builder class with validation
    public static class Builder {
        private InventoryId inventoryId;
        private WarehouseId warehouseId;
        private ProductId productId;
        private int totalQuantity;
        private int reservedQuantity;
        private int availableQuantity;
        private LocalDateTime createdAt;

        public Builder inventoryId(InventoryId inventoryId) {
            this.inventoryId = inventoryId;
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

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Inventory build() {
            // Validate mandatory fields
            if (inventoryId == null) {
                throw new IllegalStateException("InventoryId must be provided.");
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
            return new Inventory(this);
        }
    }
}
