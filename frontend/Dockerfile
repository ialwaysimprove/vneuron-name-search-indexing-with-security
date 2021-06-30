### STAGE 1: Build ###
FROM node:12.14-alpine AS build
WORKDIR /usr/src/vneuron-watchlist
#COPY ["package.json","package-lock.json","/app/"]
COPY . .
RUN npm install
RUN npm install -g @angular/cli
COPY . .
# CMD /usr/src/vneuron-watchlist/node_modules/.bin/ng serve --host 0.0.0.0 --disableHostCheck
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY default.conf /temp/default.conf
COPY replace_placeholders.sh /
COPY --from=build /usr/src/vneuron-watchlist/dist/frontend/ /usr/share/nginx/html/

EXPOSE 80
ENTRYPOINT [ "sh", "/replace_placeholders.sh" ]
CMD ["nginx",  "-g", "daemon off;"]
