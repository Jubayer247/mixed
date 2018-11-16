import os
os.environ.setdefault('DJANGO_SETTINGS_MODULE','noteBook.settings')

import django
django.setup()


import random
from registration.models import Person
from faker import Faker
fg = Faker()
for i in range(100):
    p=Person()
    p.name=fg.name()
    p.email=fg.email(20)
    p.password=fg.text(20)
    p.save()
