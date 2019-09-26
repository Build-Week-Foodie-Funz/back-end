# Foodie Back End, Build Week Bash client

## Overview
This is a Bash client script for accessing Foodie Back End, Build Week service.

The script uses cURL underneath for making all REST calls.

## Usage

```shell
# Make sure the script has executable rights
$ chmod u+x 

# Print the list of operations available on the service
$ ./ -h

# Print the service description
$ ./ --about

# Print detailed information about specific operation
$ ./ <operationId> -h

# Make GET request
./ --host http://<hostname>:<port> --accept xml <operationId> <queryParam1>=<value1> <header_key1>:<header_value2>

# Make GET request using arbitrary curl options (must be passed before <operationId>) to an SSL service using username:password
 -k -sS --tlsv1.2 --host https://<hostname> -u <user>:<password> --accept xml <operationId> <queryParam1>=<value1> <header_key1>:<header_value2>

# Make POST request
$ echo '<body_content>' |  --host <hostname> --content-type json <operationId> -

# Make POST request with simple JSON content, e.g.:
# {
#   "key1": "value1",
#   "key2": "value2",
#   "key3": 23
# }
$ echo '<body_content>' |  --host <hostname> --content-type json <operationId> key1==value1 key2=value2 key3:=23 -

# Preview the cURL command without actually executing it
$  --host http://<hostname>:<port> --dry-run <operationid>

```

## Docker image
You can easily create a Docker image containing a preconfigured environment
for using the REST Bash client including working autocompletion and short
welcome message with basic instructions, using the generated Dockerfile:

```shell
docker build -t my-rest-client .
docker run -it my-rest-client
```

By default you will be logged into a Zsh environment which has much more
advanced auto completion, but you can switch to Bash, where basic autocompletion
is also available.

## Shell completion

### Bash
The generated bash-completion script can be either directly loaded to the current Bash session using:

```shell
source .bash-completion
```

Alternatively, the script can be copied to the `/etc/bash-completion.d` (or on OSX with Homebrew to `/usr/local/etc/bash-completion.d`):

```shell
sudo cp .bash-completion /etc/bash-completion.d/
```

#### OS X
On OSX you might need to install bash-completion using Homebrew:
```shell
brew install bash-completion
```
and add the following to the `~/.bashrc`:

```shell
if [ -f $(brew --prefix)/etc/bash_completion ]; then
  . $(brew --prefix)/etc/bash_completion
fi
```

### Zsh
In Zsh, the generated `_` Zsh completion file must be copied to one of the folders under `$FPATH` variable.


## Documentation for API Endpoints

All URIs are relative to **

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*LogoutControllerApi* | [**logoutUsingGET**](docs/LogoutControllerApi.md#logoutusingget) | **GET** /oauth/revoke-token | logout
*OpenControllerApi* | [**addNewUserUsingPOST**](docs/OpenControllerApi.md#addnewuserusingpost) | **POST** /createnewuser | Add User
*RestaurantControllerApi* | [**addNewRestaurantUsingPOST**](docs/RestaurantControllerApi.md#addnewrestaurantusingpost) | **POST** /user/restaurant | Add Restaurant
*RestaurantControllerApi* | [**deleteRestaurantByIdUsingDELETE**](docs/RestaurantControllerApi.md#deleterestaurantbyidusingdelete) | **DELETE** /user/restaurant/{restid} | Delete by Restaurant Id
*RestaurantControllerApi* | [**getRestaurantByIdUsingGET**](docs/RestaurantControllerApi.md#getrestaurantbyidusingget) | **GET** /user/restaurant/{restid} | Return Restaurant by id
*RestaurantControllerApi* | [**listAllRestaurantsUsingGET**](docs/RestaurantControllerApi.md#listallrestaurantsusingget) | **GET** /user/restaurants | Return All Restaurants
*RestaurantControllerApi* | [**updateRestaurantUsingPUT**](docs/RestaurantControllerApi.md#updaterestaurantusingput) | **PUT** /user/restaurant/{restid} | Update restaurant by Id
*ReviewsControllerApi* | [**addNewReviewUsingPOST**](docs/ReviewsControllerApi.md#addnewreviewusingpost) | **POST** /user/restaurant/{restid}/reviews | Add Review to Restaurant
*ReviewsControllerApi* | [**deleteReviewUsingDELETE**](docs/ReviewsControllerApi.md#deletereviewusingdelete) | **DELETE** /user/reviews/{reviewid} | Delete by Review Id
*ReviewsControllerApi* | [**listReviewOfRestaurantByIdUsingGET**](docs/ReviewsControllerApi.md#listreviewofrestaurantbyidusingget) | **GET** /user/reviews/{reviewid} | Return Reviews by id
*ReviewsControllerApi* | [**listReviewsOfRestaurantUsingGET**](docs/ReviewsControllerApi.md#listreviewsofrestaurantusingget) | **GET** /user/restaurants/{restid}/reviews | Return Reviews by restaurant id
*ReviewsControllerApi* | [**updateReviewUsingPUT**](docs/ReviewsControllerApi.md#updatereviewusingput) | **PUT** /user/restaurant/{restid}/reviews/{reviewid} | Update Review by Id
*RolesControllerApi* | [**addNewRoleUsingPOST**](docs/RolesControllerApi.md#addnewroleusingpost) | **POST** /roles/role | Add Role
*RolesControllerApi* | [**deleteRoleByIdUsingDELETE**](docs/RolesControllerApi.md#deleterolebyidusingdelete) | **DELETE** /roles/role/{id} | Delete by Role Id
*RolesControllerApi* | [**getRoleByIdUsingGET**](docs/RolesControllerApi.md#getrolebyidusingget) | **GET** /roles/role/{roleId} | Return Role by ID
*RolesControllerApi* | [**getRoleByNameUsingGET**](docs/RolesControllerApi.md#getrolebynameusingget) | **GET** /roles/role/name/{roleName} | Return Roles by Name
*RolesControllerApi* | [**listRolesUsingGET**](docs/RolesControllerApi.md#listrolesusingget) | **GET** /roles/roles | Return All Roles
*UserControllerApi* | [**addNewUserUsingPOST1**](docs/UserControllerApi.md#addnewuserusingpost1) | **POST** /users/user | Add User
*UserControllerApi* | [**deleteUserByIdUsingDELETE**](docs/UserControllerApi.md#deleteuserbyidusingdelete) | **DELETE** /users/user/{id} | Delete by User Id
*UserControllerApi* | [**deleteUserRoleByIdsUsingDELETE**](docs/UserControllerApi.md#deleteuserrolebyidsusingdelete) | **DELETE** /users/user/{userid}/role/{roleid} | Delete Role ID of User Id
*UserControllerApi* | [**getCurrentUserNameUsingGET**](docs/UserControllerApi.md#getcurrentusernameusingget) | **GET** /users/getusername | Get Current User Info
*UserControllerApi* | [**getUserByIdUsingGET**](docs/UserControllerApi.md#getuserbyidusingget) | **GET** /users/user/{userId} | List User by ID
*UserControllerApi* | [**getUserByNameUsingGET**](docs/UserControllerApi.md#getuserbynameusingget) | **GET** /users/user/name/{userName} | List User By Name
*UserControllerApi* | [**listAllUsersUsingGET**](docs/UserControllerApi.md#listallusersusingget) | **GET** /users/users | List All Users
*UserControllerApi* | [**postUserRoleByIdsUsingPOST**](docs/UserControllerApi.md#postuserrolebyidsusingpost) | **POST** /users/user/{userid}/role/{roleid} | Add Role to User
*UserControllerApi* | [**updateUserUsingPUT**](docs/UserControllerApi.md#updateuserusingput) | **PUT** /users/user/{id} | Update User by Id


## Documentation For Models

 - [ErrorDetail](docs/ErrorDetail.md)
 - [RestPhotos](docs/RestPhotos.md)
 - [Restaurant](docs/Restaurant.md)
 - [Reviews](docs/Reviews.md)
 - [Role](docs/Role.md)
 - [SimpleGrantedAuthority](docs/SimpleGrantedAuthority.md)
 - [User](docs/User.md)
 - [UserRoles](docs/UserRoles.md)
 - [ValidationError](docs/ValidationError.md)


## Documentation For Authorization

 All endpoints do not require authorization.

