package com.fns.domain.valueObject;

import java.util.UUID;

public class UserId extends BaseId<UUID> {

    public UserId(UUID value){
        super(value);
    }

}
