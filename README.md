# coreapi

# Getting Started for Developers

## Install PostgreSQL

```bash
$ brew update
$ brew install postgresql@11
$ brew services start postgres
$ createuser -s postgres
$ psql -U postgres
postgres=# create database coreapi;
```