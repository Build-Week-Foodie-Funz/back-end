# ReviewsControllerApi

All URIs are relative to **

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewReviewUsingPOST**](ReviewsControllerApi.md#addNewReviewUsingPOST) | **POST** /user/restaurant/{restid}/reviews | Add Review to Restaurant
[**deleteReviewUsingDELETE**](ReviewsControllerApi.md#deleteReviewUsingDELETE) | **DELETE** /user/reviews/{reviewid} | Delete by Review Id
[**listReviewOfRestaurantByIdUsingGET**](ReviewsControllerApi.md#listReviewOfRestaurantByIdUsingGET) | **GET** /user/reviews/{reviewid} | Return Reviews by id
[**listReviewsOfRestaurantUsingGET**](ReviewsControllerApi.md#listReviewsOfRestaurantUsingGET) | **GET** /user/restaurants/{restid}/reviews | Return Reviews by restaurant id
[**updateReviewUsingPUT**](ReviewsControllerApi.md#updateReviewUsingPUT) | **PUT** /user/restaurant/{restid}/reviews/{reviewid} | Update Review by Id


## **addNewReviewUsingPOST**

Add Review to Restaurant

### Example
```bash
 addNewReviewUsingPOST restid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newReviews** | [**Reviews**](Reviews.md) | newReviews |
 **restid** | **integer** | restid |

### Return type

[**array[Reviews]**](Reviews.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteReviewUsingDELETE**

Delete by Review Id

### Example
```bash
 deleteReviewUsingDELETE reviewid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **reviewid** | **integer** | reviewid |

### Return type

[**array[Reviews]**](Reviews.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listReviewOfRestaurantByIdUsingGET**

Return Reviews by id

### Example
```bash
 listReviewOfRestaurantByIdUsingGET reviewid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **reviewid** | **integer** | reviewid |

### Return type

[**Reviews**](Reviews.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listReviewsOfRestaurantUsingGET**

Return Reviews by restaurant id

### Example
```bash
 listReviewsOfRestaurantUsingGET restid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **restid** | **integer** | restid |

### Return type

[**Reviews**](Reviews.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateReviewUsingPUT**

Update Review by Id

### Example
```bash
 updateReviewUsingPUT restid=value reviewid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **restid** | **integer** | restid |
 **reviewid** | **integer** | reviewid |
 **updateReviews** | [**Reviews**](Reviews.md) | updateReviews |

### Return type

[**array[Reviews]**](Reviews.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

