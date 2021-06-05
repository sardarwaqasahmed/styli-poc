# Description
This a poc for testing E-Commerece category hierarchical data structure with MongoDB. 

# Test Data
Test data is located on mongo-json-data folder with file name categorytree.json


# Swagger Docs
swagger api end point can be accessed via 
- http://localhost:8080/swagger-ui/index.html#/

## Endpoints 

Method	| Path	| Description
GET	| /app/{treeId}	| retrieve a whole category Tree along with all childrens by treeId
GET	| /app/{treeId}/set/{nodeId}	| retrieve a sub-tree of a tree
POST | /app/ add a new category node to a given tree
PUT | /app/{treeId}/{nodeId} | update an existing node category
DELETE | /app/{treeId}/{nodeId} | delete a node category

To View the E-commerece category structure use below end point
- http://localhost:8080/app/1001

To Retrieve any subtree from E-commerece category use below end point
- http://localhost:8080/app/1001/st/100

## How to run
mvn spring-boot:run
