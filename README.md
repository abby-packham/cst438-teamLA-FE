# cst438 Team LA Flight Reservation service


## prerequisites

git clone git@github.com:charlesk40/cst438-teamLA-FE.git
git clone git@github.com:charlesk40/cst438-teamLA-BE1.git
git clone git@github.com:charlesk40/cst438-teamLA-BE2.git

### rabbitmg for cst438-teamLA-BE2
Install rabbitmg and erlang


rabbitmq:

https://www.rabbitmq.com/install-generic-unix.html


erlang:

```
brew install erlang
```

Start rabbitmq_server

```
rabbitmq_server-3.8.3/sbin/rabbitmq-server 
```

## Running 

Start rabbitmq_server and st438-teamLA-BE2 first.
Then, start cst438-teamLA-BE1 and cst438-teamLA-FE as FE requires both backend services.

![alt text](https://raw.githubusercontent.com/charlesk40/cst438-teamLA-FE/master/cst438-teamLA-FE-main.png)


