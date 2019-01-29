from app import db
from app.models import *
db.create_all()
u = User(username="norbek", email="iwilliams10@stuy.edu", password_hash="1234", moderator=True, admin=True)
u2 = User(username="bob", email="bob@bob.edu", password_hash="12345", moderator=False, admin=False)
db.session.add(u)
db.session.add(u2)
db.session.commit()
#Noun.__table__.create(db.session.bind)