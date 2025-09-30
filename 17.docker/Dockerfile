FROM diamol/node

# 개발자가 docker run ~ 별도의 환경변수가 
ENV TARGET="blog.sixeyed.com"
ENV METHOD="HEAD"
ENV INTERVAL="3000"

WORKDIR /web-ping
COPY app.js app.js

CMD ["node", "/web-pring/app.js"]

