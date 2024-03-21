import requests
import base64

# Set your username and password
username = "your_username"
password = "your_password"

# Encode the username and password in Base64
credentials = f"{username}:{password}"
credentials_base64 = base64.b64encode(credentials.encode()).decode()

# Set the URL and payload for the POST request
url = "https://example.com/api/endpoint"
payload = {"key": "value"}

# Set the headers with Basic authentication
headers = {
    "Authorization": f"Basic {credentials_base64}",
    "Content-Type": "application/json"  # Adjust content type as needed
}

# Send the POST request
response = requests.post(url, json=payload, headers=headers)

# Check the response
if response.status_code == 200:
    print("Request was successful.")
    print(response.json())
else:
    print("Error:", response.status_code)
    print(response.text)
