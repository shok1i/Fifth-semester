# Используем официальный образ Apache с поддержкой PHP
FROM php:8.1-apache
RUN apt-get update; apt-get upgrade; docker-php-ext-install pdo pdo_mysql mysqli;
RUN apt-get update && apt-get install -y \
    libpng-dev libjpeg-dev libfreetype6-dev \
    && docker-php-ext-configure gd --with-freetype --with-jpeg \
    && docker-php-ext-install gd \
    && pecl install redis \
    && docker-php-ext-enable redis
