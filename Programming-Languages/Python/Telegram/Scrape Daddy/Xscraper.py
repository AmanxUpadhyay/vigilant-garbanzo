# Important Library to Import
import csv
from telethon import errors, TelegramClient, sync, functions


class Xscraper:
    def __init__(self, apiID, apiHash, channelName, phoneNumber):
        self.apiID = apiID
        self.apiHash = apiHash
        self.channelName = channelName
        self.phoneNumber = phoneNumber
        self.client = TelegramClient(
            channelName, self.apiID, self.apiHash, timeout=60)
        self.scrapedUsers = []

    def connectXscraper(self):
        self.client.connect()
        print("Connected To Xscraper")

    def disconnectXscraper(self):
        self.client.disconnect()
        print("Disconnected From Xscraper")

    def authorizeXscraper(self):
        if not self.client.is_user_authorized():
            self.client.send_code_request(self.phoneNumber)
            print("Code Sent... Kindly Check Your Phone")
            self.client.sign_in(self.phoneNumber, input('Enter code: '))

    def userDetails(self):
        self.scrapedUsers = []
        for user in self.client.iter_participants(self.channelName, aggressive=True):
            self.scrapedUsers.append(
                {
                    'id': user.id,
                    'username': user.username,
                }
            )

    def storeUserDetails(self, filePath):
        with open(filePath, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(["ID", "Username"])
            for user in self.scrapedUsers:
                # Only Store ID and Username. If Username is empty ignore it
                if user['username'] is not None:
                    writer.writerow([user['id'], user['username']])
        print(f"User data stored in {filePath}")
