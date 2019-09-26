# RolesControllerApi

All URIs are relative to **

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewRoleUsingPOST**](RolesControllerApi.md#addNewRoleUsingPOST) | **POST** /roles/role | Add Role
[**deleteRoleByIdUsingDELETE**](RolesControllerApi.md#deleteRoleByIdUsingDELETE) | **DELETE** /roles/role/{id} | Delete by Role Id
[**getRoleByIdUsingGET**](RolesControllerApi.md#getRoleByIdUsingGET) | **GET** /roles/role/{roleId} | Return Role by ID
[**getRoleByNameUsingGET**](RolesControllerApi.md#getRoleByNameUsingGET) | **GET** /roles/role/name/{roleName} | Return Roles by Name
[**listRolesUsingGET**](RolesControllerApi.md#listRolesUsingGET) | **GET** /roles/roles | Return All Roles


## **addNewRoleUsingPOST**

Add Role

### Example
```bash
 addNewRoleUsingPOST
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newRole** | [**Role**](Role.md) | newRole |

### Return type

[**array[Role]**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteRoleByIdUsingDELETE**

Delete by Role Id

### Example
```bash
 deleteRoleByIdUsingDELETE id=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **integer** | id |

### Return type

[**array[Role]**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getRoleByIdUsingGET**

Return Role by ID

### Example
```bash
 getRoleByIdUsingGET roleId=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **roleId** | **integer** | roleId |

### Return type

[**Role**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getRoleByNameUsingGET**

Return Roles by Name

### Example
```bash
 getRoleByNameUsingGET roleName=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **roleName** | **string** | roleName |

### Return type

[**Role**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listRolesUsingGET**

Return All Roles

### Example
```bash
 listRolesUsingGET
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Role**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

