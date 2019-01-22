from flask import Flask, render_template, redirect, url_for, request, make_response
from flask_sqlalchemy import SQLAlchemy
from config import Config
from flask_socketio import SocketIO, emit


app = Flask(__name__)
app.config.from_object(Config)
socketio = SocketIO(app)
db = SQLAlchemy(app)
from app import routes, models
#app.run(debug=True)