## Modules

* common - contails common classes for webhook and tespay services
* payemnt-api - testpay service
* webhook - callback service

## Checksum algorithm
The signature is constructed by performing an SHA-256 calculation on string build up by concatenating the fields sended to to webhook listener. Signature = SHA-256 (currency + amount + SHA-256(ASCII(secretWord)).toUpperCase + paymentId + externalId* + status)

* if externalId is null, field skipped

## How to build

```gradlew build```

## OAuth2

In order to accrue access token send POST request

```HTTP
POST /oauth2/token HTTP/1.1
Host: localhost:8080
Authorization: Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=
Cache-Control: no-cache
Content-Type: application/x-www-form-urlencoded

grant_type=client_credentials&scope=payments

{
    "access_token": "0dc34cad-791f-46cf-b156-b552dfd62a00",
    "token_type": "bearer",
    "expires_in": 1799,
    "scope": "payments"
}
```
Where Authorization header values is Base64 encoded string: Base64(cleint_id:client_secret)

To configure clients edit application.yml file:
``` YAML
oauth2:
  clients:
  - clientId: client_id
    clientSecret: client_secret
    scopes:
    - payments
    - scope
    redirectUris:
    - http://example.com
    accessTokenValiditySeconds: 1800
    authorizedGrantTypes:
    - client_credentials

```

Webhook event properties

```YAML
testpay:
  secret: secretWord
  webHook:
    readTimeout: 1000
    connectionTimeout: 2000
  job:
    retryCount: 25
    intervalInSeconds: 10368
```
