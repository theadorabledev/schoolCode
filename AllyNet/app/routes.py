from app import app, db, socketio
from models import *
from flask import Flask, render_template, redirect, url_for, request, make_response
from sqlalchemy import or_
from flask_socketio import SocketIO, emit
from datetime import datetime
import random
import string
@app.route('/signup', methods=["GET", "POST"])
def signup():
    if getUser(request):
        return redirect(url_for('home'))
    if request.method == 'POST':     
        #return str(globals())
        username = request.form["username"]
        password, password2 = request.form["password"], request.form["confirm_password"]
        email = request.form["email"]
        key = request.form["key"]
        isEmail = db.session.query(Invite).filter_by(email=email).first()
        isKey = db.session.query(Invite).filter_by(inviteKey=key).first()
        nameTaken = db.session.query(User).filter_by(username=username).first()
        if not isKey:
            return make_response(render_template('signup.html', error='That key is not in the server. Please try again. You may not have been invited.'))
        if not isEmail:
            return make_response(render_template('signup.html', error='That email is not in the server. Please try again. You may not have been invited.'))        
        if isKey.email != email:
            return make_response(render_template('signup.html', error='That invite was not for that email.'))
        if nameTaken:
            return make_response(render_template('signup.html', error='That username is already taken.'))    
        if not (password and password2 and username and email and key):
            return make_response(render_template('signup.html', error='A field was left blank.'))
        if password != password2:
            return make_response(render_template('signup.html', error='The passwords do not match'))
        u = User(username=username, email=email, password_hash=password, moderator=False, admin=False)        
        db.session.add(u)
        db.session.delete(isKey)
        db.session.commit()        
        return redirect(url_for('home'))  
        #return make_response(render_template('signup.html', error='You seem to be right.'))
        """
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
        """

        
    return render_template('signup.html')    
@app.route('/login', methods=['GET', 'POST'])
def login():
    if getUser(request):
        return redirect(url_for('home'))
    error = None   
    if request.method == 'POST': 
        user = User.query.filter((User.email == request.form["username"]) | (User.username == request.form["username"])).first()
        if user and request.form["password"] == user.password_hash:
            resp = make_response(redirect(url_for('login')))
            makeSessionID(user, resp)
            return resp
        else:
            error = 'Invalid Credentials. Please try again.'
            resp = make_response(render_template('login.html', error='Invalid Credentials. Please try again.'))
            return resp      
    return render_template('login.html')
@app.route('/logout', methods=['GET', 'POST'])
def logout():
    if getUser(request):
        #pass
    
        db.session.delete(getUser(request).sessions.first())
        db.session.commit()
    resp = make_response(redirect(url_for('login')))
    resp.set_cookie('sessionID', '', expires=0)
    return resp
@app.route("/", methods=["GET", "POST"])
@app.route('/home', methods=["GET", "POST"])
def home():
    Post.delete_expired()
    if request.method == 'POST':     
        socketio.emit("new_message",{"text":request.form["message"], "sender":getUser(request).username, "recipients":[], "private":False, "time":0})#, "sender" : getUser(request).username})            
        p = Post(body=request.form["message"], author=getUser(request))
        db.session.add(p)
        db.session.commit()          
        return redirect(url_for("home"))
    if getUser(request):
        name = getUser(request).username
        posts = [{"text":post.body, "sender":post.author.username, "recipients":[], "private":False, "time":post.timestamp} for post in Post.query.all()]
        return make_response(render_template('index.html', username=name, segment_details=posts))
    else:
        return redirect(url_for("login"))
@app.route("/invite", methods=["GET", "POST"])
def invite():
    if request.method == 'POST':
        key = ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in xrange(10))
        a = db.session.query(Invite).filter_by(email=request.form["email"]).first() 
        if a:
            a.inviteKey = key
        else:    
            i = Invite(email=request.form["email"], inviteKey=key)
            db.session.add(i)
        db.session.commit()         
        return make_response(render_template('invite.html', username=getUser(request).username, key=key))
    return make_response(render_template('invite.html', username=getUser(request).username))
    


@app.route("/profile", methods=["GET", "POST"])
def profile():
    if request.method == "POST":
        if request.form["formID"] == "password":
            if getUser(request).password_hash != request.form["old_password"]:
                return render_template("profile.html", username=getUser(request).username, password_error="You have entered an incorrect password.")
            if not (request.form["password"] and request.form["confirm_password"]):
                return render_template("profile.html", username=getUser(request).username, password_error="You have left a field blank.")
            if  request.form["password"] != request.form["confirm_password"]:
                return render_template("profile.html", username=getUser(request).username, password_error="The passwords do not match.")
            getUser(request).password_hash = request.form["password"]
            db.session.commit()
            return render_template("profile.html", username=getUser(request).username)
        elif request.form["formID"] == "email":
            if request.form["email"] != request.form["confirm_email"]:
                return render_template("profile.html", username=getUser(request).username, email_error="The emails do not match.")
            getUser(request).email = request.form["email"]
            db.session.commit()
            return render_template("profile.html", username=getUser(request).username)                
    if getUser(request):
        return render_template("profile.html", username=getUser(request).username)
    else:
        return redirect(url_for("login"))
@app.route("/search", methods=["GET", "POST"])
def search():
    if getUser(request):
        print request.args
        friendly1 = request.args.get("friendly", default=False, type=bool)
        person1 = request.args.get("people", default=False, type=bool)
        keywords = request.args.get("keywords", default="", type=str)
        results = db.session.query(Noun).filter_by(friendly = friendly1, person = person1).all()
        print person1, friendly1, results
        nouns = []
        for result in results:
            for word in keywords:
                if word in result.name.lower() or word in result.location.lower() or word in result.reason.lower():
                    nouns.append(result)
        if keywords:
            results = [{"name":noun.name, "location":noun.location, "reason":noun.reason} for noun in nouns]
        return render_template("search.html", friendly=friendly1, people=person1, username=getUser(request).username, nouns=results)
    else:
        return redirect(url_for("login"))
def makeSessionID(user, resp):
    s_id = ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in xrange(45))
    s = Session(user=user, session_id=s_id)
    db.session.add(s)
    db.session.commit()    
    resp.set_cookie('sessionID', s_id) 
def getUser(request):
    Session.delete_expired()
    if request.cookies.get('sessionID') and db.session.query(Session).filter_by(session_id=request.cookies.get('sessionID')).first():
        u = db.session.query(Session).filter_by(session_id=request.cookies.get('sessionID')).first().user
        return u
    else:       
        return None