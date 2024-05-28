# coreapi

# Generate random jwt secret key

```bash
node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
```

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