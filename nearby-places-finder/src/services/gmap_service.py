"""Google maps service"""
import sys
sys.path.append('src')
import googlemaps

from src.core.config import GOOGLE_MAPS_API_KEY


class GoogleMapsClient:
    """Google Maps service class."""

    def __init__(self) -> None:
        """Initialize the Google Maps client."""
        self.client = googlemaps.Client(key = "INSERT_TOP_SECRET_GOOGLE_MAPS_API_KEY")

    def geocode_location(self, location: str) -> tuple[float, float]:
        """Geocode the provided location"""
        geocode_data = self.client.geocode(location)

        if not geocode_data:
            raise Exception("Error geocoding location")  # noqa

        lat_lng = geocode_data[0]["geometry"]["location"]
        return lat_lng["lat"], lat_lng["lng"]

    def get_places_in_vicinity(
        self, location: str, place_type: str, radius: int = 2000
    ) -> list[dict]:
        """Get the location of schools in the provided vicinity"""
        latitude, longitude = self.geocode_location(location)
        places_data = self.client.places_nearby(
            location = (latitude, longitude), radius = radius, type = place_type
        )

        if not places_data["results"]:
            raise NoPlacesFoundError

        places_list = []
        for place in places_data["results"]:
            place_dict = {
                "Name": place["name"],
                "Latitude": place["geometry"]["location"]["lat"],
                "Longitude": place["geometry"]["location"]["lng"],
                "Address": place.get("vicinity", "Address not available"),
            }
            print(places_list)
            places_list.append(place_dict)

        return places_list


class NoPlacesFoundError(Exception):
    """Exception raised when no places of the specified type are found in the vicinity."""

    pass
