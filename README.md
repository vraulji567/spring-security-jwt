# Docker commands

docker build -t spring-jet -f Dockerfile .
docker image . —tag spring-jwt
docker run -d -p 8080:8080 spring-jwt


# Install Minikube
$ brew install minikube

# Start Minikube in docker container
$ minikube start —driver docker

$ minikube status

$ minikube stop

# Run kubernetes with local image

$ minikube docker-env
Run the eval command

$ docker image ls
$ minikube image ls --format table 

# kubectl commands

$ kubectl —help
$ kubectl get —help

# apply Kubernetes template - deployment, service, configMap, secret
$ kubectl apply -f fine_name.yml

# delete Kubernetes template - deployment, service, confogMap, secret
$ kubectl delete -f file_name.yml

$kubectl get pod
$kubectl get all
$kubectl get node -o wide
$kubectl logs {pod-name}

# Nodeport port forwarding
$ kubectl port-forward <pod-name> <nodeport:port>