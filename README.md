### Order POST Request JSON example: 

```
{
    "createdAt": "2020-04-24T17:09:42.411",
    "address": {
        "country": "Romania",
        "city": "Cluj-Napoca",
        "county": "Cluj",
        "streetAddress": "Obs Street"
    },
    "products": [
        {
            "productId": 2,
            "quantity": 1
        },
        {
            "productId": 3,
            "quantity": 1
        },
        {
            "productId": 2,
            "quantity": 2
        }
    ]
}
```

###  Product POST Request JSON example: 

```
{
	"id": 3,
    "name": "Samsung J8",
    "description": "Samsung Phone",
    "price": 150,
    "weight": 0.5,
    "imageUrl": "URL",
    "categoryName": "Phones",
    "categoryDescription": "Technology",
    "supplierName": "Miller Limited"
} 
```