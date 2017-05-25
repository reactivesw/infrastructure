# Infrastructure Service Rest API

## 1. Introduction
Infrastructure aims to provide basic service for other services.

## 2. View Model

### CurrencyView
| field name | field type | comments |
|-----|------|-----|
| id | String | Currency Id |
| version | Integer | |
| isoCode | String | ISOCode of currency |
| currencyName | String | currency name |
| conversionFactor | Integer | The conversion factor is used to express the relationship between a major currency unit and its corresponding minor currency unit. |
| defaultCurrency | Boolean | indicate whether it is the default currency |

### CurrencyDraft

| field name | field type | comments |
|-----|------|-----|
| isoCode | String | ISOCode of currency |
| currencyName | String | currency name |
| conversionFactor | Integer | The conversion factor is used to express the relationship between a major currency unit and its corresponding minor currency unit. |

### UpdateRequest

| field name | field type | comments |
|-|-|-|
| version | Integer | required, NotNull, min is 0 |
| actions | List\<UpdateAction\> | required, NotNull |

### Update Action

##### SetDefaultCurrency


| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setDefaultCurrency` |
| defaultCurrency | Boolean | required, NotNull |


### PagedQueryResult

| field name | field type | comments |
|-|-|-|
| offset | Integer | |
| count | Integer | |
| total | Integer | |
| results | List\<T\> | |
| facets | Object | |

## API

### add currency 

* Url : {infrastructure service url}/currency
* method : POST
* request body :

| name | type | required |
|-|-|-|
| addCurrency | CurrencyDraft | required |

* response : CurrencyView
* payload example:
```
{
    "isoCode": "RUB",
    "currencyName": "Russian ruble",
    "conversionFactor": 100
}
```

### get all currency

* URL : {infrastructure service url}/currency
* method : GET
* response : PagedQueryResult<CurrencyView>


### update currency by id

* URL : {infrastructure service url}/{currencyId}
* method : PUT
* request body :

| name | type | comments |
|-|-|-|
| updateRequest | UpdateRequest | required |

* response : CurrencyView
