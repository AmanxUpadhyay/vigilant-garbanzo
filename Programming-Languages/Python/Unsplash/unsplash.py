import requests
import json
import os

# Set your Unsplash API access key
access_key = '2tlDzlHjpOXkmViqcD_2r2pUpgW264IG2vOrDVil0rk'

# Set the ID of the collection you want to download
collection_id = '12264302'

# Set the directory where you want to save the downloaded images
save_directory = '/Users/m1/Downloads'

# Make the initial API request to get the first page of photos in the collection
url = f'https://api.unsplash.com/collections/{collection_id}/photos'
headers = {'Authorization': f'Client-ID {access_key}'}
response = requests.get(url, headers=headers)

# Check if the request was successful
if response.status_code == 200:
    # Convert the response data to JSON format
    photos_data = json.loads(response.text)

    # Loop through the photos and download them
    for photo_data in photos_data:
        # Get the URL of the photo and its ID
        photo_url = photo_data['urls']['full']
        photo_id = photo_data['id']

        # Make the request to download the photo
        photo_response = requests.get(photo_url)

        # Check if the request was successful
        if photo_response.status_code == 200:
            # Save the photo to the specified directory with the photo ID as the filename
            with open(os.path.join(save_directory, f'{photo_id}.jpg'), 'wb') as file:
                file.write(photo_response.content)

            print(f'Downloaded {photo_id}.jpg')
        else:
            print(
                f'Error downloading {photo_id}.jpg: {photo_response.status_code}')

    # Check if there are more pages of photos to download
    while 'next' in response.links.keys():
        # Make the API request to get the next page of photos in the collection
        url = response.links['next']['url']
        response = requests.get(url, headers=headers)

        # Check if the request was successful
        if response.status_code == 200:
            # Convert the response data to JSON format
            photos_data = json.loads(response.text)

            # Loop through the photos and download them
            for photo_data in photos_data:
                # Get the URL of the photo and its ID
                photo_url = photo_data['urls']['full']
                photo_id = photo_data['id']

                # Make the request to download the photo
                photo_response = requests.get(photo_url)

                # Check if the request was successful
                if photo_response.status_code == 200:
                    # Save the photo to the specified directory with the photo ID as the filename
                    with open(os.path.join(save_directory, f'{photo_id}.jpg'), 'wb') as file:
                        file.write(photo_response.content)

                    print(f'Downloaded {photo_id}.jpg')
                else:
                    print(
                        f'Error downloading {photo_id}.jpg: {photo_response.status_code}')
        else:
            print(
                f'Error getting photos from collection: {response.status_code}')
else:
    print(f'Error getting photos from collection: {response.status_code}')
