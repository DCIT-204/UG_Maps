"""Main file"""

from src.services.gmap_service import GoogleMapsClient, NoPlacesFoundError
from src.services.io import IOService
from src.utils.io import get_file_type
from src.utils.valid_place import get_valid_place_type


def main(maps_service: GoogleMapsClient, io_service: IOService) -> None:
    """Main function for the script."""
    place_types = get_valid_place_type()
    print(place_types)
    location = input("Enter the location: ")
    file_type = get_file_type()
    for place_type in place_types:
        initialSetOfPlaces = False
        if place_type == place_types[0]:
            initialSetOfPlaces = True
        try:           
            places = maps_service.get_places_in_vicinity(location, place_type)
            if not places:
                print(f"No {place_type} found in the {location} vicinity")
                continue

            file_path = io_service.create_file_path(place_type, location, file_type)
            (
                io_service.write_to_csv(places, file_path, initialSetOfPlaces)
                if file_type == "csv"
                else io_service.write_to_text(places, file_path)
            )
            print(f"Results written to {file_path}")
        except NoPlacesFoundError:
            print(f"No {place_type} found in the {location} vicinity.")

if __name__ == "__main__":
    google_maps_service = GoogleMapsClient()
    io_service = IOService()
    main(google_maps_service, io_service)
