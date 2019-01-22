from app import db
from datetime import datetime, timedelta
#import datetime


class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(64), index=True, unique=True)
    email = db.Column(db.String(120), index=True, unique=True)
    password_hash = db.Column(db.String(128))
    moderator = db.Column(db.Boolean)
    admin = db.Column(db.Boolean)
    posts = db.relationship('Post', backref='author', lazy='dynamic')
    sessions = db.relationship('Session', backref='user', lazy='dynamic')
    def __repr__(self):
        return '<User {}>'.format(self.username)
class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    body = db.Column(db.String(200))
    timestamp = db.Column(db.DateTime, index=True, default=datetime.utcnow)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))
    _viewers = db.Column(db.String(100), default="?")
    @property
    def viewers(self):
        return [x for x in self._viewers.split(';')]
    @classmethod
    def delete_expired(cls):
        expiration_days = 1
        limit = datetime.now() - timedelta(days=expiration_days)
        cls.query.filter(cls.timestamp <= limit).delete()
        db.session.commit()     
    def __repr__(self):
        return '<Post {}>'.format(self.body)
class Invite(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), index=True, unique=True)
    inviteKey = db.Column(db.String(120), index=True)
class Session(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    timestamp = db.Column(db.DateTime, index=True, default=datetime.utcnow)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))
    session_id = db.Column(db.String(50), index=True, unique=True)
    @classmethod
    def delete_expired(cls):
        expiration_days = 1
        limit = datetime.now() - timedelta(days=expiration_days)
        cls.query.filter(cls.timestamp <= limit).delete()
        db.session.commit()    
