import os
os.environ["KERAS_BACKEND"] = "torch"

import logging
import numpy as np
from flask import Flask, request, jsonify
import keras as kr 

# Configure logging
logging.basicConfig(level=logging.INFO, format="%(asctime)s [%(levelname)s] %(message)s")

# Load the model once when the application starts
MODEL_PATH = os.path.join("model", "mnist_model.keras")
model = kr.models.load_model(MODEL_PATH)

def predict(image: np.ndarray) -> int:
    prediction = model.predict(image)
    return int(np.argmax(prediction))

app = Flask(__name__)

@app.route('/api/predict', methods=['POST'])
def predict_route():
    try:
        if request.is_json:
            content = request.get_json()
            print(content)
            image = content.get("image")
            if image is None:
                return jsonify({"error": "No 'image' key provided in JSON payload"}), 400
            arr = np.array(image, dtype=np.float32)
        else:
            image_str = request.image.decode("utf-8").strip()
            if not image_str:
                return jsonify({"error": "Empty request model.image"}), 400
            arr = np.array(image_str.split(), dtype=np.float32)

        if arr.size != 28 * 28:
            return jsonify({"error": "Invalid input size. Expected 784 float values for a 28x28 model.image."}), 400

        image = arr.reshape(1, 28, 28, 1)

        result = predict(image)
        logging.info(f"Prediction result: {result}")
        return jsonify({"prediction": result})
    except Exception as e:
        logging.exception("Error during prediction")
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    # Run the Flask server on port 9999
    app.run(host='0.0.0.0', port=9999)