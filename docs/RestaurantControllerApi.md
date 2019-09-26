# RestaurantControllerApi

All URIs are relative to **

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewRestaurantUsingPOST**](RestaurantControllerApi.md#addNewRestaurantUsingPOST) | **POST** /user/restaurant | Add Restaurant
[**deleteRestaurantByIdUsingDELETE**](RestaurantControllerApi.md#deleteRestaurantByIdUsingDELETE) | **DELETE** /user/restaurant/{restid} | Delete by Restaurant Id
[**getRestaurantByIdUsingGET**](RestaurantControllerApi.md#getRestaurantByIdUsingGET) | **GET** /user/restaurant/{restid} | Return Restaurant by id
[**listAllRestaurantsUsingGET**](RestaurantControllerApi.md#listAllRestaurantsUsingGET) | **GET** /user/restaurants | Return All Restaurants
[**updateRestaurantUsingPUT**](RestaurantControllerApi.md#updateRestaurantUsingPUT) | **PUT** /user/restaurant/{restid} | Update restaurant by Id


## **addNewRestaurantUsingPOST**

Add Restaurant

### Example
```bash
 addNewRestaurantUsingPOST
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newRestaurant** | [**Restaurant**](Restaurant.md) | newRestaurant |

### Return type

[**array[Restaurant]**](Restaurant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteRestaurantByIdUsingDELETE**

Delete by Restaurant Id

### Example
```bash
 deleteRestaurantByIdUsingDELETE restid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **restid** | **integer** | restid |

### Return type

[**array[Restaurant]**](Restaurant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getRestaurantByIdUsingGET**

Return Restaurant by id

### Example
```bash
 getRestaurantByIdUsingGET restid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **restid** | **integer** | restid |

### Return type

[**Restaurant**](Restaurant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listAllRestaurantsUsingGET**

Return All Restaurants

### Example
```bash
 listAllRestaurantsUsingGET  page=value  size=value  Specify as:  sort=value1 sort=value2 sort=...
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | [**Object**](.md) | Results page you want to retrieve(0..N) | [optional]
 **size** | [**Object**](.md) | Results of records per page | [optional]
 **sort** | [**array[string]**](string.md) | Sorting criteria in the format: property(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [optional]

### Return type

[**Restaurant**](Restaurant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateRestaurantUsingPUT**

Update restaurant by Id

### Example
```bash
 updateRestaurantUsingPUT restid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **restid** | **integer** | restid |
 **updateRestaurant** | [**Restaurant**](Restaurant.md) | updateRestaurant |

### Return type

[**array[Restaurant]**](Restaurant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

