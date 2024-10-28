package com.fns.domain.valueObject;

public enum JournalReason {
    ADDED,           // Increment available qty
    REDUCED,         // Reduce available qty
    RESERVED,        // Changes from available qty to reserved qty
    CANCELING,       // Initiate cancellation of reserved qty
    CANCELED,        // Revert reserved qty back to available qty
    ORDERING,        // Start the ordering process
    ORDERED,         // Finalize ordering, reducing total and reserved qty
    TRANSFERRED,     // Complete the transfer, reducing total and reserved qty
}