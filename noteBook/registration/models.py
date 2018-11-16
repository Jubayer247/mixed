# Create your models here.
from django.db import models

class Person(models.Model):

    stdID=models.IntegerField(primary_key=True,default=0)
    name=models.CharField(max_length=30)
    email=models.CharField(max_length=30)
    password=models.CharField(max_length=30)
    sflag=models.BooleanField(default=True)
    mflag=models.BooleanField(default=False)


from django.contrib.auth.models import User
from django.db import models

class UserProfileInfro(models.Model):

    #create relationsship (don't inherit from User!)
    user=models.OneToOneField(User)

    #Add any aditional attribute you want
    stdID=models.IntegerField(blank=False)


    def __str__(self):
        return self.user.username