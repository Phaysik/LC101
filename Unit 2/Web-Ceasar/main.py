from flask import Flask, request
from caesar import rotate_character 
import random

app = Flask(__name__)

app.config['DEBUG'] = True

# Double {{}} prevents python from interpreting the css as a placeholder
form = """
<!DOCTYPE html>

<html>
    <head>
        <title>Web Caesar</title>
        <meta charset="UTF-8">
        <meta name="description" content="Web Caesar">
        <meta name="author" content="Matthew Moore">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            form {{
                background-color: #eee;
                padding: 20px;
                margin: 0 auto;
                width: 540px;
                font: 16px sans-serif;
                border-radius: 10px;
            }}
            textarea {{
                margin: 10px 0;
                width: 540px;
                height: 120px;
                resize: none;
            }}
        </style>
    </head>
    <body>
      <form method="post">
        <label for="rot">Rotate By:</label> <input type="text" id="rot" name="rot" value="0" />
        <textarea name="text" placeholder="{0}"></textarea>
        <input type="submit" value="Submit Query" />
      </form>
    </body>
</html>
"""

@app.route("/")
def index():
    return form.format('')

# Whenever the app detects the form was submitted
@app.route("/", methods=['POST'])
def encrypt():
    # Get the values from the two form inputs
    rotate = request.form['rot']
    text = request.form['text']
    newString = ''
    
    # Rotate character
    for char in text:
        newString += rotate_character(char, int(rotate))

    return form.format(newString)

app.run()
