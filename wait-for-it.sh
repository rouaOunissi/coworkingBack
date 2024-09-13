#!/bin/bash
host="$1"
port="$2"
shift 2
cmd="$@"

until nc -z "$host" "$port"; do
  >&2 echo "Waiting for MySQL to be available at $host:$port..."
  sleep 5
done

>&2 echo "MySQL is up - executing command!"
exec $cmd
