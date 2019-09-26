# UserControllerApi

All URIs are relative to **

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewUserUsingPOST1**](UserControllerApi.md#addNewUserUsingPOST1) | **POST** /users/user | Add User
[**deleteUserByIdUsingDELETE**](UserControllerApi.md#deleteUserByIdUsingDELETE) | **DELETE** /users/user/{id} | Delete by User Id
[**deleteUserRoleByIdsUsingDELETE**](UserControllerApi.md#deleteUserRoleByIdsUsingDELETE) | **DELETE** /users/user/{userid}/role/{roleid} | Delete Role ID of User Id
[**getCurrentUserNameUsingGET**](UserControllerApi.md#getCurrentUserNameUsingGET) | **GET** /users/getusername | Get Current User Info
[**getUserByIdUsingGET**](UserControllerApi.md#getUserByIdUsingGET) | **GET** /users/user/{userId} | List User by ID
[**getUserByNameUsingGET**](UserControllerApi.md#getUserByNameUsingGET) | **GET** /users/user/name/{userName} | List User By Name
[**listAllUsersUsingGET**](UserControllerApi.md#listAllUsersUsingGET) | **GET** /users/users | List All Users
[**postUserRoleByIdsUsingPOST**](UserControllerApi.md#postUserRoleByIdsUsingPOST) | **POST** /users/user/{userid}/role/{roleid} | Add Role to User
[**updateUserUsingPUT**](UserControllerApi.md#updateUserUsingPUT) | **PUT** /users/user/{id} | Update User by Id


## **addNewUserUsingPOST1**

Add User

### Example
```bash
 addNewUserUsingPOST1
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newuser** | [**User**](User.md) | newuser |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteUserByIdUsingDELETE**

Delete by User Id

### Example
```bash
 deleteUserByIdUsingDELETE id=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **integer** | id |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteUserRoleByIdsUsingDELETE**

Delete Role ID of User Id

### Example
```bash
 deleteUserRoleByIdsUsingDELETE roleid=value userid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **roleid** | **integer** | roleid |
 **userid** | **integer** | userid |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getCurrentUserNameUsingGET**

Get Current User Info

### Example
```bash
 getCurrentUserNameUsingGET  authenticated=value  authorities[0].authority=value  Specify as:   Specify as:   Specify as: 
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authenticated** | **boolean** |  | [optional]
 **authorities[0].authority** | **string** |  | [optional]
 **credentials** | [**map[String, string]**](string.md) |  | [optional]
 **details** | [**map[String, string]**](string.md) |  | [optional]
 **principal** | [**map[String, string]**](string.md) |  | [optional]

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getUserByIdUsingGET**

List User by ID

### Example
```bash
 getUserByIdUsingGET userId=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **integer** | userId |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getUserByNameUsingGET**

List User By Name

### Example
```bash
 getUserByNameUsingGET userName=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userName** | **string** | userName |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listAllUsersUsingGET**

List All Users

### Example
```bash
 listAllUsersUsingGET
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **postUserRoleByIdsUsingPOST**

Add Role to User

### Example
```bash
 postUserRoleByIdsUsingPOST roleid=value userid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **roleid** | **integer** | roleid |
 **userid** | **integer** | userid |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateUserUsingPUT**

Update User by Id

### Example
```bash
 updateUserUsingPUT id=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **integer** | id |
 **updateUser** | [**User**](User.md) | updateUser |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

