# pull official base image
FROM node:10 AS builder

# set working directory
WORKDIR /app

# install app dependencies
#copies package.json and package-lock.json to Docker environment
COPY package.json ./

# Installs all node packages
RUN npm install 

ARG REACT_APP_POD_ID
ARG REACT_APP_TEST

ENV REACT_APP_POD_ID $REACT_APP_POD_ID
ENV REACT_APP_TEST $REACT_APP_TEST
# Copies everything over to Docker environment
COPY . ./
RUN npm run build

#Stage 2
#######################################
#pull the official nginx:1.19.0 base image
FROM nginx:1.19.0
#copies React to the container directory
# Set working directory to nginx resources directory
WORKDIR /usr/share/nginx/html
# Remove default nginx static resources
RUN rm -rf ./*
# Copies static resources from builder stage
COPY --from=builder /app/build .
# Expose port 80 for HTTP Traffic 
EXPOSE 3000
# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]