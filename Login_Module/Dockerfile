FROM node:16

WORKDIR /usr/src/app

COPY package.json ./

RUN npm install

COPY . .

EXPOSE 8080
CMD ["node", "index.js"]

#sha256:a13d2d2aec7f0dae18a52ca4d38b592e45a45cc4456ffab82e5ff10d8a53d042

#sha256:9572bf073edc0621f8a537f27623357f29731273272ab8e986042e33330741da
