from flask import Flask, request, redirect, render_template
import cgi
import re

app = Flask(__name__)

app.config['DEBUG'] = True      # displays runtime errors in the browser, too


@app.route("/welcome", methods=['POST'])
def welcome():
    username = request.form['name']
    password = request.form['password']
    verPass = request.form['verPass']
    email = request.form['email']

    if (len(username) < 3 or len(password) > 20 or len(password) < 3 or len(password) > 20 or verPass != password or len(email) > 0 or ' ' in password or ' ' in verPass or ' ' in username):
        if (not re.match("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[com|org|edu|gov]", email)):
            return redirect("/?error=" + username + " " +  password + " " +  verPass + " error" + " " + email)
        elif (not (len(username) < 3 or len(password) > 20 or len(password) < 3 or len(password) > 20 or verPass != password or ' ' in password or ' ' in verPass or ' ' in username)):
            return render_template('welcome.html', user=cgi.escape(username))
        else:
            return redirect("/?error=" + username + " " + password + " " + verPass + " " + email)
            
    else:
        return render_template('welcome.html', user=cgi.escape(username))

@app.route("/")
def index():
    error = request.args.get('error')
    if (error != None):
        values = error.split(' ')
        if (len(values) > 3):
            return render_template('form.html', username=values[0], password=values[1], verPass=values[2], email=values[3], user=values[0], mail=values[len(values) - 1])
        else:
            return render_template('form.html', username=values[0], password=values[1], verPass=values[2], email=values[3], user=values[0])
    else:
        return render_template('form.html', username="iii", password="iii", verPass="iii", email="i@i.com")

app.run()
