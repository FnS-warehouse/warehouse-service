package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueObject.*;
import com.fns.warehouse.service.domain.exception.UnauthorizedActionException;

import java.time.LocalDateTime;

public class InventoryTransfer extends BaseEntity<InventoryTransferId> {

    private final InventoryId inventoryId;
    private final UserId userId;
    private final TransferType transferType;
    private final WarehouseId sourceWarehouseId;
    private final WarehouseId destinationWarehouseId;
    private final int quantity;
    private TransferStatus status;
    private final LocalDateTime requestTime;
    private LocalDateTime processedTime;

    private InventoryTransfer(Builder builder) {
        super.setId(builder.transferId);
        this.inventoryId = builder.inventoryId;
        this.userId = builder.userId;
        this.transferType = builder.transferType;
        this.sourceWarehouseId = builder.sourceWarehouseId;
        this.destinationWarehouseId = builder.destinationWarehouseId;
        this.quantity = builder.quantity;
        this.status = builder.status;
        this.requestTime = builder.requestTime != null ? builder.requestTime : LocalDateTime.now();
        this.processedTime = builder.processedTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void createMutationRequest(UserRole userRole) {
        validateAdminPermissions(userRole);
        this.status = TransferStatus.PENDING;
    }

    public void approveTransfer(UserRole userRole) {
        validateAdminPermissions(userRole);
        if (this.status != TransferStatus.PENDING) {
            throw new IllegalStateException("Only pending transfers can be approved.");
        }
        this.status = TransferStatus.APPROVED;
        this.processedTime = LocalDateTime.now();
    }

    public void cancelTransfer(UserRole userRole) {
        validateAdminPermissions(userRole);
        if (this.status == TransferStatus.APPROVED) {
            throw new IllegalStateException("Approved transfers cannot be canceled.");
        }
        this.status = TransferStatus.CANCELLED;
        this.processedTime = LocalDateTime.now();
    }

    private void validateAdminPermissions(UserRole userRole) {
        if (userRole.getType() != UserRoleType.WH_ADMIN && userRole.getType() != UserRoleType.SUPER_ADMIN) {
            throw new UnauthorizedActionException("Only Admins and Super Admins can process stock transfers.");
        }
    }

    // Getters
    public InventoryId getInventoryId() { return inventoryId; }
    public UserId getUserId() { return userId; }
    public TransferType getTransferType() { return transferType; }
    public WarehouseId getSourceWarehouseId() { return sourceWarehouseId; }
    public WarehouseId getDestinationWarehouseId() { return destinationWarehouseId; }
    public int getQuantity() { return quantity; }
    public TransferStatus getStatus() { return status; }
    public LocalDateTime getRequestTime() { return requestTime; }
    public LocalDateTime getProcessedTime() { return processedTime; }

    // Builder class with validation
    public static class Builder {
        private InventoryTransferId transferId;
        private InventoryId inventoryId;
        private UserId userId;
        private TransferType transferType;
        private WarehouseId sourceWarehouseId;
        private WarehouseId destinationWarehouseId;
        private int quantity;
        private TransferStatus status = TransferStatus.PENDING; // Default status
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

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder transferType(TransferType transferType) {
            this.transferType = transferType;
            return this;
        }

        public Builder sourceWarehouseId(WarehouseId sourceWarehouseId) {
            this.sourceWarehouseId = sourceWarehouseId;
            return this;
        }

        public Builder destinationWarehouseId(WarehouseId destinationWarehouseId) {
            this.destinationWarehouseId = destinationWarehouseId;
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
            // Validate mandatory fields
            if (transferId == null) {
                throw new IllegalStateException("TransferId must be provided.");
            }
            if (inventoryId == null) {
                throw new IllegalStateException("InventoryId must be provided.");
            }
            if (userId == null) {
                throw new IllegalStateException("UserId must be provided.");
            }
            if (sourceWarehouseId == null || destinationWarehouseId == null) {
                throw new IllegalStateException("Both source and destination WarehouseIds must be provided.");
            }
            if (quantity <= 0) {
                throw new IllegalStateException("Quantity must be positive.");
            }
            if (transferType == null) {
                throw new IllegalStateException("TransferType must be provided.");
            }
            return new InventoryTransfer(this);
        }
    }
}
