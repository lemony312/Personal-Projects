FROM node:9.3.0

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json /usr/src/app
RUN npm install

COPY . /usr/src/app

EXPOSE 8080
CMD [ "npm", "start" ]