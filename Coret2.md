API Endpoint:

### Warehouse
-> Restriction:
- Only Super Admin can manage
- WH Admin only view assigned wh

-> API
- GET `/warehouse`
- GET `/warehouse/{id}`
- POST `/warehouse`
```
{
    name: "",
    location: {
        name: "",
        address: "",
        lat:"",
        long:""
    }
}
  
   ```
- PUT `/warehouse`
- POST `warehouse/assign/admin`
```
{
 users: [
     {
        user_id:1,
     }
 ]
}
```

### Stock
-> API
- GET `/warehouse/stock`
- GET `/warehouse/stock/{id}`
- POST `/warehouse/stock`
- PUT `/warehouse/stock`
- POST `/warehouse/stock/transfer`

## Change State API

### POST `/warehouse`
- Business Logic
  - create location
  - create warehouse status default active
  - crete wh admin default empty list
  - create list stock default empty list
### PUT `/warehouse`
- Business Logic
  - update location
    - name
    - address
    - lat long
    - city
    - postalcode 
  - update name
  - update status
### POST `warehouse/assign/admin`
- Business logic
  - update admin 
### POST `/warehouse/stock`
- Business logic
  - add qty
  - reduce qty 
### PUT `/warehouse/stock`
- Business logic
  - add qty
  - reduce qty 
### POST `/warehouse/stock/transfer`
- Business Logic
  - update transfer status
  - determine transfer type. Enum (Auto, Manual)
```json
  {
    "transfer_type": "manual",
    "product_id": "uisnk-892-3289ndjsh",
    "destination_warehouse_id": "sskajksa8923",
    "source_warehouse_id": "jskksa",
    "qty": 10
  }
 ```
  