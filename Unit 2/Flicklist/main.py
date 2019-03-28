from flask import Flask, request
import random

app = Flask(__name__)

app.config['DEBUG'] = True

header = """
        <!DOCTYPE html>

        <html>

            <head>
                <title>Flicklist Walkthrough/Studio 2</title>
                <meta charset="UTF-8">
                <meta name="description" content="Flicklist Walkthrough/Studio 2">
                <meta name="author" content="Matthew Moore">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>

            <body>
        <h1>Flicklist</h1>
        """

footer = """
            </body>

        </html>
        """
@app.route("/")
def index():
    form = """
            <form action="/add" method="post">
                <label for="movie">Enter in a movie:</label> <input type="text" name="movie"placeholder="TAG" id="movie" />
                <input type="submit" value="Add It" />
            </form>

            <form action="/crossoff" method="post">
                <label for="movies">Select a movie to crossoff</label>

                <select name="movies" id="movies">
                    <option value="tag">TAG</option>
                </select>
                <input type="submit" value="Cross It Off" />
            </form>
            """
    
    return header + form + footer


@app.route("/crossoff", methods=['POST'])
def crossoff():
    movie = "<p>"
    movie += request.form['movies']
    movie += " was crossed off.</p>"

    return header + movie + footer

@app.route("/add", methods=['POST'])
def addMovie():
    movie = "<p>"
    movie += request.form['movie']
    movie += " was added to your watch list.</p>"

    return header + movie + footer


app.run()
