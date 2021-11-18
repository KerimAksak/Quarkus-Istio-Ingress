# Quarkus-Istio-Ingress

## Topology
![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/aqua.png)


![Topology](https://raw.githubusercontent.com/KerimAksak/Quarkus-Istio-Ingress/master/img/ingress-gateway-topoloji.png)


## Installation
![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/aqua.png)

### Backend - Quarkus

Steps to deploy on minikube

``` json
➜  ~ cd caller/k8s
➜  ~ kubectl apply -f deployment.yml
➜  ~ kubectl apply -f istio-rule.yml
➜  ~ cd callme/k8s
➜  ~ kubectl apply -f deployment.yml
➜  ~ kubectl apply -f istio-rule.yml
```

### Frontend - React

Steps to deploy on minikube

``` json
➜  ~ cd react-frontend/k8s
➜  ~ kubectl apply -f deployment.yml
➜  ~ kubectl apply -f istio-rule.yml
➜  ~ kubectl apply -f istio-ingress.yml
```

>In order to use [istio ingress gateway](https://istio.io/latest/docs/setup/platform-setup/minikube/), we must use the minikube tunnel command. If the command is not run, the external-ip value of the istio-ingressgateway service (**kubectl get service -n istio-system**) will remain as < PENDING >

### After all the steps 
![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/aqua.png)

![kubectl](https://raw.githubusercontent.com/KerimAksak/Quarkus-Istio-Ingress/master/img/kbctl.png)

* kgp ➜ kubectl get pod
* k get vs ➜ kubectl get virtualservice
* k get vs ➜ kubectl get virtualservice
* k get dr ➜ kubectl get destinationrule
* kgs ➜ kubectl get service

## Test
![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/aqua.png)

### Ingress Gateway Information

![ingress-info](https://raw.githubusercontent.com/KerimAksak/Quarkus-Istio-Ingress/master/img/ingress-info.png)


### Frontend

![caller-ping](https://raw.githubusercontent.com/KerimAksak/Quarkus-Istio-Ingress/master/img/caller-ping.png)

### Backend - Caller Ping

![ingress-info](https://raw.githubusercontent.com/KerimAksak/Quarkus-Istio-Ingress/master/img/callme-ping.png)

### Backend - Callme ping with Caller