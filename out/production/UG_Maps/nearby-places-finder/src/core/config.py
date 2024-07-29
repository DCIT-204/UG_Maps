"""Config file."""

from starlette.config import Config

config = Config(".env")

GOOGLE_MAPS_API_KEY = config("INSERT_TOP_SECRET_GOOGLE_MAPS_API_KEY", cast = str, default = None)
