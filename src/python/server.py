import socketserver
import numpy as np
import os
os.environ["KERAS_BACKEND"] = "tensorflow"
os.environ["TF_ENABLE_ONEDNN_OPTS"] = "0"

import keras as kr

class Server_class(socketserver.BaseRequestHandler):

    def handle(self):
        self.data = self.request.recv(1568).strip()
        print("Received from {}:".format(self.client_address[0]))
        print(self.data.decode("UTF-8"))
        
        prediction = np.asfarray(self.data.decode("UTF-8").split(" ")).reshape(28,28)
        prediction = prediction.reshape(1,28,28)
        print(prediction)
        prediction = np.expand_dims(prediction, -1)

        print(predict(prediction))
        self.request.sendall((predict(prediction)+48).tobytes())
        
model = kr.saving.load_model(".\\model\\mnist_model.keras")

def predict(image):
    prediction = model.predict(image)
    return np.argmax(prediction)

def main():
    HOST, PORT = "localhost", 9999
    with socketserver.ThreadingTCPServer((HOST,PORT),Server_class) as server:
        server.serve_forever()


if __name__ == '__main__':
    main()