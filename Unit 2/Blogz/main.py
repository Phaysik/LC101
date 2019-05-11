from flask import Flask, request, redirect, render_template, session, flash
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['DEBUG'] = True

# ://name-password@host:port/database_name
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://blogz:Wolverine28@localhost:8889/blogz'
app.config['SQLALCHEMY_ECHO'] = True
app.secret_key = 'y336kGcys&zP3B'

db = SQLAlchemy(app)

class Blogz(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(120))
    body = db.Column(db.String(120))
    owner_id = db.Column(db.Integer, db.ForeignKey('user.id'))

    def __init__(self, title, body):
        self.title = title
        self.body = body

    def __init__(self, title, body, owner):
        self.title = title
        self.body = body
        self.owner = owner

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(120))
    password = db.Column(db.String(120))
    blog = db.relationship('Blogz', backref='owner')

    def __init__(self, username, password):
        self.username = username
        self.password = password


@app.before_request
def require_login():
    allowed_routes = ['login', 'signup', 'blog']
    if request.endpoint not in allowed_routes and 'username' not in session:
        return redirect('/login')

@app.route('/logout')
def logout():
    del session['username']
    return redirect('/')


@app.route('/')
def red():
    return redirect('/index')


def get_blogs():
    return Blogz.query.all()

def get_users():
    return User.query.all()


@app.route('/blog', methods=['POST', 'GET'])
def blog():
    if request.method == 'POST':
        head = request.form['title']
        body = request.form['blog']

        if (len(head) < 1 or len(body) < 1):
            return redirect('/newpost?error=' + head + "," + body)

        owner = User.query.filter_by(username=session['username']).first()

        blog = Blogz(head, body, owner)
        db.session.add(blog)
        db.session.commit()
        content = Blogz.query.filter_by(id=blog.id).all()
        return render_template('blog.html', title="Build a Blog", blogs=content, posted=True, owner=owner.username)
    else:
        idPage = request.args.get('id')
        user = request.args.get('user')
        content = Blogz.query.filter_by(id=idPage).all()

        if (content):
            name = User.query.filter_by(id=content[0].owner_id).first()
            return render_template('blog.html', title="Build a Blog", blogs=content, owner=name.username)
        elif (user):
            username = User.query.filter_by(username=user).first()
            blogs = Blogz.query.filter_by(owner_id=username.id).all()
            name = User.query.filter_by(id=username.id).first()

            return render_template('blog.html', title="Build A Blog", blogs=blogs, owner=name.username)

    return render_template('blog.html', title="Build A Blog", blogs=get_blogs(), users=get_users())


@app.route('/newpost')
def newpost():
    error = request.args.get('error')
    if (error != None):
        values = error.split(',')
        if (len(values) >= 1):
            return render_template('newpost.html', title='Add Blog Entry', header=values[0], content=values[1], head=values[0], blog=values[1])
        else:
            return render_template('newpost.html', title="Add Blog Entry", header="Title", content="Blog Post")
    else:
        return render_template('newpost.html', title="Add Blog Entry", header="Title", content="Blog Post")


@app.route('/signup', methods=['POST', 'GET'])
def signup():
    if request.method == 'POST':
        username = request.form['userName']
        password = request.form['password']
        verify = request.form['verPass']
        error = False
        if (len(username) < 3):
            flash("Username must be at least three characters.", 'error')
            error = True
        if (len(password) < 3):
            flash("Password must be at least three characters.", 'error')
            error = True
        if (username == '' or password == '' or verify == ''):
            flash("One or more fields are empty.", 'error')
            error = True
        if (verify != password):
            flash("Your passwords do not match.", 'error')
            error = True

        existing_user = User.query.filter_by(username=username).first()

        if (existing_user):
            flash('There is already a user with that username.', 'error')
            error = True

        if (error == False):
            new_user = User(username, password)
            db.session.add(new_user)
            db.session.commit()
            session['username'] = username
            return redirect('/newpost')
        else:
            return render_template('signup.html', username=username)

    return render_template('signup.html')


@app.route('/login', methods=['POST', 'GET'])
def login():
    if request.method == 'POST':
        username = request.form['userName']
        password = request.form['password']
        user = User.query.filter_by(password=password).first()
        if user and user.password == password and user.username == username:
            session['username'] = username
            flash("Logged in")
            return redirect('/newpost')
        else:
            flash('User password is incorrect, or user does not exist', 'error')

    return render_template('login.html')

@app.route('/index')
def index():
    return render_template('index.html', users=get_users())


if __name__ == '__main__':
    app.run()
