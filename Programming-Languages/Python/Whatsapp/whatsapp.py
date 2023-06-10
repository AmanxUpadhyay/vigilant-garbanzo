import os
import time
import pywhatkit as pw


class WhatsAppImageSender:
    def __init__(self, phone_num, img_dir):
        self.phone_num = phone_num
        self.img_dir = img_dir

    def send_images(self):
        try:
            for file in os.listdir(self.img_dir):
                if file.endswith(".jpg") or file.endswith(".jpeg") or file.endswith(".png"):
                    img_path = os.path.join(self.img_dir, file)
                    caption = f"Sent from WhatsAppImageSender: {file}"
                    pw.sendwhats_image(
                        self.phone_num, img_path, caption=caption)
                    print(f"Successfully sent {file} to {self.phone_num}")
                    time.sleep(5)
                else:
                    print(f"{file} is not a valid image file")
        except Exception as e:
            print(f"Error occurred while sending images: {e}")


sender = WhatsAppImageSender("+918017583797", "/Users/m1/Downloads")
sender.send_images()