import csv
from telethon import errors, TelegramClient, sync, functions


class Xscraper:
    def __init__(self, api_id, api_hash, channel_name, phone_number):
        self.api_id = api_id
        self.api_hash = api_hash
        self.channel_name = channel_name
        self.phone_number = phone_number
        self.client = TelegramClient(
            channel_name, api_id, api_hash, timeout=60)
        self.scraped_users = []

    def connect(self):
        try:
            self.client.connect()
            print("Connected to Telegram client.")
        except errors.ConnectionError:
            print("Connection failed. Check your internet connection and try again.")

    def disconnect(self):
        try:
            self.client.disconnect()
            print("Disconnected from Telegram client.")
        except errors.ConnectionError:
            print(
                "Unable to disconnect from Telegram client. Check your internet connection and try again.")

    def authorize(self):
        try:
            if not self.client.is_user_authorized():
                self.client.send_code_request(self.phone_number)
                print("Verification code sent. Please check your phone.")
                self.client.sign_in(self.phone_number, input('Enter code: '))
        except errors.PhoneNumberInvalidError:
            print("Invalid phone number. Please check the phone number and try again.")
        except errors.SessionPasswordNeededError:
            print("Two-step verification is enabled. Please enter your password.")
            self.client.sign_in(password=input('Enter password: '))
        except errors.InvalidTwoFactorPasswordError:
            print("Invalid two-factor verification password. Please try again.")

    def scrape_users(self):
        try:
            self.scraped_users = []
            for user in self.client.iter_participants(self.channel_name, aggressive=True):
                self.scraped_users.append(
                    {'id': user.id, 'username': user.username})
            print(
                f"Scraped {len(self.scraped_users)} users from {self.channel_name}")
        except errors.ChannelPrivateError:
            print(
                "You don't have permission to access the channel. Please check the channel name and try again.")
        except errors.ChannelInvalidError:
            print(
                "The channel name is invalid. Please check the channel name and try again.")
        except errors.FloodWaitError as e:
            print(
                f"Too many requests. Please try again after {e.seconds} seconds.")
        except errors.RPCError as e:
            print(f"Error occurred while scraping users: {e}")

    def store_users(self, file_path):
        try:
            with open(file_path, 'w', newline='') as file:
                writer = csv.writer(file)
                writer.writerow(["ID", "Username"])
                for user in self.scraped_users:
                    if user['username']:
                        writer.writerow([user['id'], user['username']])
            print(f"User data stored in {file_path}")
        except FileNotFoundError:
            print("File not found. Please check the file path and try again.")
        except PermissionError:
            print("Permission denied. Please check file permissions and try again.")
        except Exception as e:
            print(f"Error occurred while storing user data: {e}")
