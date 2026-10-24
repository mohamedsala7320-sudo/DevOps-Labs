import os
from flask import Flask, jsonify

app = Flask(__name__)

APP_NAME = os.getenv("APP_NAME")
APP_ENV = os.getenv("APP_ENV")

if not APP_NAME or not APP_ENV:
    raise ValueError("APP_NAME and APP_ENV environment variables are required")


@app.route("/")
def home():
    return jsonify({
        "application": APP_NAME,
        "environment": APP_ENV,
        "message": "Welcome to the Docker Env & Resource Management Lab!"
    })


@app.route("/health")
def health():
    return jsonify({"status": "healthy"})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)

