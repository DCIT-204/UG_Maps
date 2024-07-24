"""IO Service module."""

import csv


class IOService:
    """Service class for handling input/output operations."""

    def write_to_csv(self, schools: list[dict], file_path: str, initialSetOfPlaces: bool) -> None:
        """Write the search results to a CSV file."""
        if initialSetOfPlaces:
            with open(file_path, "a", newline = "", encoding = "utf-8") as fa1:
                writer = csv.writer(fa1)
                writer.writerow(["Index", "Name", "Latitude", "Longitude", "Address"])  # Header row for CSV
                for idx, school in enumerate(schools, 1):
                    writer.writerow([idx, school["Name"].replace(',', ' '), school["Latitude"], school["Longitude"], school["Address"]])
        else:
            with open(file_path, "r", encoding = "utf-8") as fr:
                reader = csv.reader(fr)
                lastIdx = 0
                for row in reader: 
                    lastIdx = row[0]
                nextIdx = int(lastIdx) + 1
            with open(file_path, "a", newline="", encoding="utf-8") as fa2:
                writer = csv.writer(fa2)
                for idx, school in enumerate(schools, nextIdx):
                    writer.writerow([idx, school["Name"], school["Latitude"], school["Longitude"], school["Address"]])

    def write_to_text(self, schools: list[dict], file_path: str) -> None:
        """Write the search results to a text file."""
        with open(file_path, "w", newline = "", encoding = "utf-8") as f:
            for idx, school in enumerate(schools, 1):
                f.write(f"{idx}. {school['name']} - {school['address']}\n")

    def create_file_path(self, place_type: str, location: str, file_type: str) -> str:
        """Create a file name based on the location."""
        file_extension = ".csv" if file_type == "csv" else ".txt"
        return f"data/search_results_{location.replace(' ', '_')}{file_extension}"