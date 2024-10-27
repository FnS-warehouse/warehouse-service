package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueObject.*;
import java.time.LocalDateTime;

public class InventoryTransfer extends BaseEntity<InventoryTransferId> {

    private InventoryTransferId transferId;
    private InventoryId inventoryId;
    private UserId user;
    private TransferType transferType;
    private WarehouseId WHsourceId;
    private WarehouseId WHdestinationId;
    private int quantity;
    private TransferStatus status;
    private LocalDateTime requestTime;
    private LocalDateTime processedTime;

    private InventoryTransfer(Builder builder) {
        super.setId(builder.transferId);
        this.inventoryId = builder.inventoryId;
        this.user = builder.user;
        this.transferType = builder.transferType;
        this.WHsourceId = builder.WHsourceId;
        this.WHdestinationId = builder.WHdestinationId;
        this.quantity = builder.quantity;
        this.status = builder.status;
        this.requestTime = builder.requestTime != null ? builder.requestTime : LocalDateTime.now();
        this.processedTime = builder.processedTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void createMutationRequest(int qty, UserRole userRole) {
        validateAdminPermissions(userRole);
        this.quantity = qty;
        this.status = TransferStatus.PENDING;
        createJournalEntry(JournalReason.ORDERING);
    }

    public void approveTransfer(UserRole userRole) {
        validateAdminPermissions(userRole);
        this.status = TransferStatus.APPROVED;
        this.processedTime = LocalDateTime.now();
        createJournalEntry(JournalReason.TRANSFERRED);
    }

    public void cancelTransfer(UserRole userRole) {
        validateAdminPermissions(userRole);
        this.status = TransferStatus.CANCELLED;
        this.processedTime = LocalDateTime.now();
        createJournalEntry(JournalReason.CANCELED);
    }

    private void createJournalEntry(JournalReason reason) {
        InventoryJournal journalEntry = InventoryJournal.builder()
                .inventoryId(this.inventoryId)
                .totalQuantity(this.quantity)
                .reason(reason)  // Using the JournalReason enum
                .build();
        journalEntry.addJournal();
    }

    private void validateAdminPermissions(UserRole userRole) {
        if (userRole.getType() != UserRoleType.WH_ADMIN && userRole.getType() != UserRoleType.SUPER_ADMIN) {
            throw new IllegalStateException("Only Admins and Super Admins can process stock transfers.");
        }
    }


    public InventoryTransferId getTransferId() { return transferId; }
    public InventoryId getInventoryId() { return inventoryId; }
    public UserId getUser() { return user; }
    public TransferType getTransferType() { return transferType; }
    public WarehouseId getWHsourceId() { return WHsourceId; }
    public WarehouseId getWHdestinationId() { return WHdestinationId; }
    public int getQuantity() { return quantity; }
    public TransferStatus getStatus() { return status; }
    public LocalDateTime getRequestTime() { return requestTime; }
    public LocalDateTime getProcessedTime() { return processedTime; }

    // Builder class
    public static class Builder {
        private InventoryTransferId transferId;
        private InventoryId inventoryId;
        private UserId user;
        private TransferType transferType;
        private WarehouseId WHsourceId;
        private WarehouseId WHdestinationId;
        private int quantity;
        private TransferStatus status;
        private LocalDateTime requestTime;
        private LocalDateTime processedTime;

        public Builder transferId(InventoryTransferId transferId) {
            this.transferId = transferId;
            return this;
        }

        public Builder inventoryId(InventoryId inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder user(UserId user) {
            this.user = user;
            return this;
        }

        public Builder transferType(TransferType transferType) {
            this.transferType = transferType;
            return this;
        }

        public Builder WHsourceId(WarehouseId WHsourceId) {
            this.WHsourceId = WHsourceId;
            return this;
        }

        public Builder WHdestinationId(WarehouseId WHdestinationId) {
            this.WHdestinationId = WHdestinationId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder status(TransferStatus status) {
            this.status = status;
            return this;
        }

        public Builder requestTime(LocalDateTime requestTime) {
            this.requestTime = requestTime;
            return this;
        }

        public Builder processedTime(LocalDateTime processedTime) {
            this.processedTime = processedTime;
            return this;
        }

        public InventoryTransfer build() {
            return new InventoryTransfer(this);
        }
    }
}
