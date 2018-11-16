from django.contrib import admin
from registration.models import Person
from registration.models import UserProfileInfro

# Register your models here.

admin.site.register(Person)
admin.site.register(UserProfileInfro)
