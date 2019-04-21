from flask import Flask, request, redirect, render_template
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['DEBUG'] = True

# ://name-password@host:port/database_name
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://build-a-blog:Wolverine28@localhost:8889/build-a-blog'
app.config['SQLALCHEMY_ECHO'] = True

db = SQLAlchemy(app)


class Blog(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(120))
    body = db.Column(db.String(120))

    def __init__ (self, title, body):
        self.title = title
        self.body = body


@app.route('/')
def red():
    return redirect('/blog')

def get_blogs():
    return Blog.query.all()


@app.route('/blog', methods=['POST', 'GET'])
def index():
    if request.method == 'POST':
        head = request.form['title']
        body = request.form['blog']

        if (len(head) < 1 or len(body) < 1):
            return redirect('/newpost?error=' + head + "," + body)
        
        blog = Blog(head, body)
        db.session.add(blog)
        db.session.commit()
        content = Blog.query.filter_by(id=blog.id).all()
        return render_template('blog.html', title="Build a Blog", blogs=content, posted=True)
    else:
        idPage = request.args.get('id')
        content = Blog.query.filter_by(id=idPage).all()
        if (content):
            return render_template('blog.html', title="Build a Blog", blogs=content)

    return render_template('blog.html', title="Build A Blog", blogs=get_blogs())


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

if __name__ == '__main__':
    app.run()
