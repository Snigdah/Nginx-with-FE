# Microservices Architecture with React, Spring Cloud Gateway, and Eureka

![Architecture](https://github.com/Snigdah/images/blob/main/Nginx.png)

**Overview:**  
This project demonstrates a production-ready microservices setup with:  

- React frontends served via Nginx (`/app1`, `/app2`)  
- Spring Cloud Gateway for API routing  
- Eureka for service discovery  
- Backend microservices: Order Service and Product Service  
- Suitable for multi-EC2, Docker Swarm, or Kubernetes  

---

## Services

| Service         | Port | Purpose |
|-----------------|------|---------|
| Eureka Server    | 8761 | Service discovery |
| Gateway Service  | 8080 | API routing |
| Order Service    | 8081 | Handles order APIs |
| Product Service  | 8082 | Handles product APIs |
| Frontend App1    | 80   | React App 1 |
| Frontend App2    | 80   | React App 2 |
| Nginx            | 443  | Reverse proxy + SSL |

---

## Docker Compose (Simplified)

```yaml
version: "3.9"

services:
  frontend-1:
    image: frontend1:0.0.2
    networks: [frontend-network]

  frontend-2:
    image: frontend2:0.0.2
    networks: [frontend-network]

  eureka-service:
    image: eureka-service:1.0
    networks: [backend-network]

  gateway-service:
    image: gateway-service:1.0
    depends_on: [eureka-service]
    environment:
      - EUREKA_SERVER_URL=http://eureka-service:8761/eureka/
    networks: [backend-network]

  order-service:
    image: order-service:1.0
    depends_on: [eureka-service]
    environment:
      - EUREKA_SERVER_URL=http://eureka-service:8761/eureka/
    networks: [backend-network]

  product-service:
    image: product-service:1.0
    depends_on: [eureka-service]
    environment:
      - EUREKA_SERVER_URL=http://eureka-service:8761/eureka/
    networks: [backend-network]

  nginx:
    image: nginx:alpine
    ports:
      - "80:80"
      - "443:443"
    volumes:
      - ./nginx/nginx.conf:/etc/nginx/nginx.conf
      - ./ssl:/etc/nginx/ssl
    networks: [frontend-network, backend-network]

networks:
  frontend-network:
    driver: bridge
  backend-network:
    driver: bridge
