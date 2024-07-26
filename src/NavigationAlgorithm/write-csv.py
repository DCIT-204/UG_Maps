import pandas as pd
import googlemaps
import os
import time

def calculate_distances(api_key, input_file, output_file):
    # Let's check if the file exists
    print(f"Checking if the file exists: {input_file}")
    if not os.path.exists(input_file):
        print(f"Error: The file {input_file} does not exist.")
        return

    # Initialize the Google Maps client
    gmaps = googlemaps.Client(key=api_key)
    
    # Load the locations data
    locations_df = pd.read_csv(input_file)

    # Print the column names for debugging
    print("Columns in the CSV file:", locations_df.columns)
    
    # Checking if required columns exist
    if 'ID' not in locations_df.columns or 'Latitude' not in locations_df.columns or 'Longitude' not in locations_df.columns:
        print("Error: The CSV file must contain 'ID', 'Latitude', and 'Longitude' columns.")
        return

    # Prepare a DataFrame to store the distances with IDs as both rows and columns
    distance_matrix = pd.DataFrame(index=locations_df['ID'], columns=locations_df['ID'])

    # Get the total number of locations
    num_locations = len(locations_df)

    # Batch size for API requests (It seems you cannot call more than 10)
    batch_size = 10

    # Iterate through all pairs of locations in batches
    for i in range(0, num_locations, batch_size):
        for j in range(0, num_locations, batch_size):
            origins = locations_df.iloc[i:i+batch_size][['Latitude', 'Longitude']].apply(tuple, axis=1).tolist()
            destinations = locations_df.iloc[j:j+batch_size][['Latitude', 'Longitude']].apply(tuple, axis=1).tolist()

            # Request the distance matrix in batches
            result = gmaps.distance_matrix(origins=origins, destinations=destinations, mode='walking')

            # Check if the API call was successful and contains the expected data
            try:
                for oi, origin in enumerate(origins):
                    for di, destination in enumerate(destinations):
                        element = result['rows'][oi]['elements'][di]
                        if element['status'] == 'OK':
                            distance = element['distance']['value']
                        else:
                            print(f"Error in response for {origin} to {destination}: {element['status']}")
                            distance = None
                        distance_matrix.at[locations_df.at[i+oi, 'ID'], locations_df.at[j+di, 'ID']] = distance
            except KeyError as e:
                print(f"KeyError: {e} - Response: {result}")

            # Introduce a delay to avoid hitting the rate limit
            time.sleep(1)

    # Write the distances to a new CSV file
    distance_matrix.to_csv(output_file)
    print(f"Distance data has been written to {output_file}")

# Define your input file path and output file path
input_file_path = r'c:/Users/Bruker/Desktop/UG_Maps/NavigationAlgorithm/src/UG-locations.csv'
output_file_path = r'c:/Users/Bruker/Desktop/UG_Maps/NavigationAlgorithm/src/UG-distances.csv'

# Get the API key from environment variable
API_KEY = os.getenv('GOOGLE_MAPS_API_KEY')

if API_KEY is None:
    print("Error: API key not found. Please set the environment variable 'GOOGLE_MAPS_API_KEY'.")
else:
    # Calculate the distances and write to the new CSV file
    calculate_distances(API_KEY, input_file_path, output_file_path)
