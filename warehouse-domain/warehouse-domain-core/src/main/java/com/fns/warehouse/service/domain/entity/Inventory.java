package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueObject.*;

public class Inventory extends AggregateRoot<InventoryId> {

    private WarehouseId warehouseId;
    private ProductId productId;
    private int totalQuantity;
    private int reservedQuantity;
    private int availableQuantity;

    private Inventory(Builder builder) {
        super.setId(builder.inventoryId);
        this.warehouseId = builder.warehouseId;
        this.productId = builder.productId;
        this.totalQuantity = builder.totalQuantity;
        this.reservedQuantity = builder.reservedQuantity;
        this.availableQuantity = builder.availableQuantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addTotalQty(int qty, UserRole userRole) {
        validateAdminPermissions(userRole);
        this.totalQuantity += qty;
        this.availableQuantity += qty;
        createJournalEntry(qty, "Addition");
    }

    public void reduceTotalQty(int qty, UserRole userRole) {
        validateAdminPermissions(userRole);
        if (qty > this.availableQuantity) {
            throw new IllegalArgumentException("Not enough available quantity to reduce.");
        }
        this.totalQuantity -= qty;
        this.availableQuantity -= qty;
        createJournalEntry(-qty, "Reduction");
    }

    public void addReservedQty(int qty, UserRole userRole) {
        validateAdminPermissions(userRole);
        if (qty > this.availableQuantity) {
            throw new IllegalArgumentException("Not enough available quantity to reserve.");
        }
        this.reservedQuantity += qty;
        this.availableQuantity -= qty;
        createJournalEntry(qty, "Reserve");
    }

    public void reduceReservedQty(int qty, UserRole userRole) {
        validateAdminPermissions(userRole);
        if (qty > this.reservedQuantity) {
            throw new IllegalArgumentException("Not enough reserved quantity to release.");
        }
        this.reservedQuantity -= qty;
        this.availableQuantity += qty;
        createJournalEntry(-qty, "Release Reserve");
    }

    private void validateAdminPermissions(UserRole userRole) {
        if (userRole.getType() != UserRoleType.WH_ADMIN && userRole.getType() != UserRoleType.SUPER_ADMIN) {
            throw new IllegalStateException("Only Admins and Super Admins can modify stock.");
        }
    }

    private void createJournalEntry(int qtyChange, String reason) {
        InventoryJournal journalEntry = InventoryJournal.builder()
                .inventoryId(this.getId())
                .totalQuantity(this.totalQuantity)
                .reservedQuantity(this.reservedQuantity)
                .availableQuantity(this.availableQuantity)
                .reason(JournalReason.valueOf(reason))
                .build();
        journalEntry.addJournal();
    }

    public boolean isAuthorizedWarehouseAdmin(WarehouseId warehouseId, UserRole userRole) {
        return userRole.getType() == UserRoleType.WH_ADMIN && this.warehouseId.equals(warehouseId);
    }

    public WarehouseId getWarehouseId() { return warehouseId; }
    public ProductId getProductId() { return productId; }
    public int getTotalQuantity() { return totalQuantity; }
    public int getReservedQuantity() { return reservedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }

    // Builder class
    public static class Builder {
        private InventoryId inventoryId;
        private WarehouseId warehouseId;
        private ProductId productId;
        private int totalQuantity;
        private int reservedQuantity;
        private int availableQuantity;

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

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
