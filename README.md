## System Architecture

```mermaid
flowchart LR

    User["User Browser"]

    Nginx["Nginx Reverse Proxy\n(SSL Termination)"]

    Frontend1["Frontend App 1\nReact + Vite"]
    Frontend2["Frontend App 2\nReact + Vite"]

    Gateway["Spring Cloud Gateway"]

    Eureka["Eureka Service Discovery"]

    OrderService["Order Service"]
    ProductService["Product Service"]

    User -->|HTTPS Request| Nginx

    Nginx -->|/app1| Frontend1
    Nginx -->|/app2| Frontend2

    Nginx -->|API Requests| Gateway

    Gateway --> Eureka

    Gateway --> OrderService
    Gateway --> ProductService

    OrderService --> Eureka
    ProductService --> Eureka
