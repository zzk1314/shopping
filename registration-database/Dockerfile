FROM mysql:latest

ENV TZ=Asia/Shanghai
# Copy the database initialize script: 
# Contents of /docker-entrypoint-initdb.d are run on mysqld startup
ADD  docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/
COPY my-config.cnf /etc/mysql/conf.d/
# Default values for passwords and database name. Can be overridden on docker run
# ENV MYSQL_ROOT_PASSWORD=my-secret-pw # Not defaulted for security reasons!
ENV MYSQL_DATABASE=eshop
ENV MYSQL_USER=nethunder
ENV MYSQL_PASSWORD=zzk93615
ENV MYSQL_ROOT_PASSWORD=root