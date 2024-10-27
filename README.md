Run dependency graph
```commandline
    mvn depgraph:aggregate  
    dot -Tpng target/dependency-graph.dot -o dependency-graph.png
```

### Assignment Week 3 (29-31 Oct 2024)
- [x] Register GCP
- [x] Install docker 
- [x] Install postgres
- [x] Implement the domain. Implement all of those things. Just domain core and domain application service
    - [x] Implement the design in there
    - [x] Implement all of them in there every component in the design
    - [x] Core domain entity
      - [x] Create all core domain entity logic. Builder
      - [ ] Create Exception
      - [x] Create core domain event 
    - [x] Domain Service with Implementation
      - [x] Create Warehouse Domain Service Impl
      - [x] Create Inventory Domain Service Impl
      - [x] Create Interface in Warehouse Domain
      - [x] Crete Interface in Inventory Domain
- [ ] Integrate with SpringBoot, Lombok, and Avro
  - [x] Install Spring
  - [x] Install Lombok
  - [ ] Install Avro
  - [ ] Learning Basic Spring
  - [ ] Implement Basic Spring in this project
  - [ ] Learning Lombok
  - [ ] Implement Basic Lombok in this project
- [ ] Install Database. Integrate with DB. -> warehouse-container
- [ ] Must can run the kafka
