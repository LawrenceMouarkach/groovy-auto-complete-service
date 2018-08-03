# Groovy & Ratpack AutoComplete service for autocomplete

- Example uses airports but is configurable
- Ratpack and groovy technologies
- Mongodb required


##### Run the following mongo command to import required data: 

`mongoimport -c autoComplete -d database --mode merge  --jsonArray airport-codes.json`

##### Endpoint & path parameter (5ad3d9700000000000000000 is LARGE_AIRPORT)
`localhost:5050/5ad3d9700000000000000000`

##### Query parameters
- key=${airportNameVariable} i.e key=manchester

##### Examples 
- Full: `localhost:5050/5ad3d9700000000000000000?key=manchester`
- Partial: `localhost:5050/5ad3d9700000000000000000?key=man`
