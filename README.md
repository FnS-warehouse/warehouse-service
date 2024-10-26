Run dependency graph
```commandline
    mvn depgraph:aggregate  
    dot -Tpng target/dependency-graph.dot -o dependency-graph.png
```

### Assignment Week 3 (29-31 Oct 2024)
- [x] Register GCP
- [x] Install docker 
- [x] Install postgres
- [ ] Implement the domain. Implement all of those things. Just domain core and domain application service
    - [x] Implement the design in there
    - [x] Implement all of them in there every component in the design
    - [ ] Core domain entity
      - [x] Create all core domain entity logic. Builder
      - [ ] Create Exception
      - [ ] Create core domain event 
    - [ ] Application Service with Input Output Interface
      - [ ] Create Warehouse Application Service Impl
      - [ ] Create åInventory Warehouse Impl
      - [ ] Create Interface in Input
      - [ ] Crete Interface in Output 
- [ ] Integrate with SpringBoot, Lombok, and Avro
  - [ ] Learning Basic Spring
  - [ ] Implement Basic Spring in this project
  - [ ] Learning Lombok
  - [ ] Implement Basic Lombok in this project
- [ ] Install Database. Integrate with DB. -> warehouse-container
- [ ] Must can run the kafka
