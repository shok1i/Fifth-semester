# Используем официальный образ Apache с поддержкой PHP
FROM php:8.1-apache

RUN apt-get update && apt-get upgrade -y \
    && apt-get install -y \
    libpng-dev libjpeg-dev libfreetype6-dev \
    git \
    unzip \
    && docker-php-ext-configure gd --with-freetype --with-jpeg \
    && docker-php-ext-install pdo pdo_mysql mysqli gd \
    && pecl install redis \
    && docker-php-ext-enable redis \
    && rm -rf /var/lib/apt/lists/*


COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Устанавливаем зависимости PHP
WORKDIR /var/www/html/Six
COPY apache/Six/ /var/www/html/Six

RUN composer install