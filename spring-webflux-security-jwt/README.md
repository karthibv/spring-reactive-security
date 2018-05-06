**JWT Authentication and Authorization using Spring Security 5, Spring WebFlux and SpringBoot 2**




### Test Output ###

#1) Login endpoint with credentials - http://localhost:8080/login#

curl -ukarthik@gmail.com:password -v http://localhost:50034/login
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 50034 (#0)
* Server auth using Basic with user 'karthik@gmail.com'
> GET /login HTTP/1.1
> Host: localhost:50034
> Authorization: Basic a2FydGhpa0BnbWFpbC5jb206cGFzc3dvcmQ=
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXJ0aGlrQGdtYWlsLmNvbSIsImF1dGhzIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJpc3MiOiJyYXBoYS5pbyIsImV4cCI6MTUyNTU4NzUwMH0.NVjv93u9O_nGvlFwY8avq2MeXIOooNckgyt7FniqcpY
< Content-Type: application/json;charset=UTF-8
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Content-Type-Options: nosniff
< X-Frame-Options: DENY
< X-XSS-Protection: 1 ; mode=block
< 
* Connection #0 to host localhost left intact
[{"name":"Default","message":"Hello World!"}]


#2) invoke other endpoints without token in header#

curl  -v http://localhost:50034/api/private
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 50034 (#0)
> GET /api/private HTTP/1.1
> Host: localhost:50034
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 500 Internal Server Error
< transfer-encoding: chunked
< Content-Type: application/json;charset=UTF-8
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Content-Type-Options: nosniff
< X-Frame-Options: DENY
< X-XSS-Protection: 1 ; mode=block
< 


#3) invoke other endpoints with token in header#




curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXJ0aGlrQGdtYWlsLmNvbSIsImF1dGhzIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJpc3MiOiJyYXBoYS5pbyIsImV4cCI6MTUyNTU4NzYzNn0.lCHbBN94s3o7z_d4e83np3E8Wi6n5HPS9wv4eZ_sTks" -v http://localhost:50034/api/private
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 50034 (#0)
> GET /api/private HTTP/1.1
> Host: localhost:50034
> User-Agent: curl/7.54.0
> Accept: */*
> Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXJ0aGlrQGdtYWlsLmNvbSIsImF1dGhzIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJpc3MiOiJyYXBoYS5pbyIsImV4cCI6MTUyNTU4NzYzNn0.lCHbBN94s3o7z_d4e83np3E8Wi6n5HPS9wv4eZ_sTks
> 
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/json;charset=UTF-8
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Content-Type-Options: nosniff
< X-Frame-Options: DENY
< X-XSS-Protection: 1 ; mode=block
< 
* Connection #0 to host localhost left intact
[{"name":"User","message":"Hello User!"}]

