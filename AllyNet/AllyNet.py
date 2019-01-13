from flask import Flask, render_template, redirect, url_for, request, make_response
import json
import md5
import datetime
app = Flask(__name__)
users = json.loads(open("Users.json").read())
invites = set(open("Invites.txt").read().split(","))
posts = json.loads(open("chat.json").read())

def userPost(sender, text, recipients=None):
    if recipients:
        recipients.remove("")
    return {
            "sender" : sender,
            "text" : text,
            "time" : datetime.datetime.now().strftime("%Y-%m-%d %H:%M"),
            "private" : len(recipients) > 1 ,
            "recipients" : recipients
        }

        
def reloadData():
    users = json.loads(open("Users.json").read())
    invites = set(open("Invites.txt").read().split(",")) 
    posts = json.loads(open("chat.json").read())
def setupJSON():
    x = {
      "iwilliams10@stuy.edu":{
          "password" : "admin",
          "moderater" : True,
          "invite-chain" : []          
      }
    }
    
    # convert into JSON:
    y = json.dumps(x)    
    f = open("Users.json", "w")
    f.write(y)
    x = "me@me.com"
    open("Invites.txt", "w").write(x)
    
def makeAccount(user, password, moderator=False):
    users[user] = {"password":password}
    with open("Users.json", "w") as f:
        f.write(json.dumps(users))    
        reloadData()
    
@app.route('/signup', methods=["GET", "POST"])
def signup():
    reloadData()
    if request.cookies.get('userID'):
        return redirect(url_for('home'))
    if request.method == 'POST':     
        #return str(globals())
        username = request.form["username"]
        password, password2 = request.form["password"], request.form["password2"]
        
        if username in users:
            return make_response(render_template('signup.html', error='You already have an account. '))
        if username not in invites:
            return make_response(render_template('signup.html', error='You have not been invited.'))
        if password != password2:
            return make_response(render_template('signup.html', error='The passwords differ.'))
        if len(password) == 0:
            return make_response(render_template('signup.html', error='Incomplete fields.'))
        makeAccount(username, password)
        resp = make_response(redirect(url_for('home')))
        resp.set_cookie('userID', request.form["username"])            
        return resp

        
    return render_template('signup.html')    
@app.route('/login', methods=['GET', 'POST'])
def login():
    reloadData()
    if request.cookies.get('userID'):
        return redirect(url_for('home'))
    
    error = None
    
    if request.method == 'POST':     
        #return str(globals())
        if request.form["username"] in users and users[request.form["username"]]["password"] == request.form['password'] :
            resp = make_response(redirect(url_for('home')))
            resp.set_cookie('userID', request.form["username"])            
            return resp#redirect(url_for('home'))
            error = "na"
            #return "hello"
        else:
            error = 'Invalid Credentials. Please try again.'
            #return redirect(url_for('/'))
            resp = make_response(render_template('login.html', error=error))
            return resp 

    return render_template('login.html')
@app.route('/home', methods=["GET", "POST"])
def home():
    if request.method == 'POST':     
        thePost = userPost(request.cookies.get('userID'),  request.form["message"], recipients=request.form["recipients"].split(","))
        posts.append(thePost)
        with open("chat.json", "w") as f:
            f.write(json.dumps(posts))
            
        return redirect(url_for("home"))
    if request.cookies.get('userID'):
        name = request.cookies.get('userID')
        return make_response(render_template('index.html', username=name, segment_details=posts))
    else:
        return redirect(url_for("login"))
@app.route("/invite", methods=["POST"])
def invite():
    if request.form["email"]:
        invites.add(request.form["email"])
        with open("Invites.txt", "w") as f:
            f.write(",".join(invites))
    return redirect(url_for("home"))
    

@app.route("/sub", methods=["GET"])
def sub():
    name = request.cookies.get('userID')
    return render_template('messageBox.html', username=name, segment_details=posts)
@app.route('/')
def hello_world():
    return redirect(url_for('home'))
    #setupJSON()
    x = userPost("iwilliams10@stuy.edu", "Hello world!")
    return json.dumps([x])
    return 'Hello, World!2'

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0')