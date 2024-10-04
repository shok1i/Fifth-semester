# Используем официальный образ Apache с поддержкой PHP
FROM php:8.1-apache
RUN apt-get update; apt-get upgrade; docker-php-ext-install pdo pdo_mysql mysqli;

# Копируем файлы сайта
COPY ./html /var/www/html/

