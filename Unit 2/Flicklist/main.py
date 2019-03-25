from flask import Flask
import random

app = Flask(__name__)

app.config['DEBUG'] = True

@app.route("/")
def index():
    movie = getRandomMovie()
    tomorrow = getRandomMovie()

    content = "<h1>Movie of the Day</h1>"
    content += "<ul>"
    content += "<li>" + movie + "</li>"
    content += "</ul>"
    content += "<h1>Tomorrow's Movie of the Day will be</h1>"
    while (movie == tomorrow):
        tomorrow = getRandomMovie()

    content += "<ul>"
    content += "<li>" + tomorrow + "</li>"
    content += "</ul>"

    return content

def getRandomMovie():
    movies = ["The Big Lebowski", "The Wedding Ringer", "TAG", "Thor", "A Quiet Place"]

    return random.choice(movies)
app.run()
