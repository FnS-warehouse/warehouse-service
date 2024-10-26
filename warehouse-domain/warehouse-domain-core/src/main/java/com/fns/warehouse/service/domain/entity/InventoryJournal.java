package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.AggregateRoot;
import com.fns.domain.valueObject.*;
import java.time.LocalDateTime;

public class InventoryJournal extends AggregateRoot<InventoryJournalId> {

    private InventoryId inventoryId;
    private int totalQuantity;
    private int reservedQuantity;
    private int availableQuantity;
    private JournalReason reason;
    private LocalDateTime timestamp;

    private InventoryJournal(Builder builder) {
        super.setId(builder.inventoryId);
        this.inventoryId = builder.inventoryId;
        this.totalQuantity = builder.totalQuantity;
        this.reservedQuantity = builder.reservedQuantity;
        this.availableQuantity = builder.availableQuantity;
        this.reason = builder.reason;
        this.timestamp = builder.timestamp != null ? builder.timestamp : LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addJournal() {
        this.timestamp = LocalDateTime.now();
    }

    public InventoryId getInventoryId() { return inventoryId; }
    public int getTotalQuantity() { return totalQuantity; }
    public int getReservedQuantity() { return reservedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
    public JournalReason getReason() { return reason; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Builder class
    public static class Builder {
        private InventoryJournalId journalId;
        private InventoryId inventoryId;
        private int totalQuantity;
        private int reservedQuantity;
        private int availableQuantity;
        private JournalReason reason;
        private LocalDateTime timestamp;

        public Builder journalId(InventoryJournalId journalId) {
            this.journalId = journalId;
            return this;
        }

        public Builder inventoryId(InventoryId inventoryId) {
            this.inventoryId = inventoryId;
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

        public Builder reason(JournalReason reason) {
            this.reason = reason;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public InventoryJournal build() {
            return new InventoryJournal(this);
        }
    }
}
