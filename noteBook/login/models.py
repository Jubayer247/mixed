from django.db import models

class NewLogin(models.Model):
    email=models.EmailField(default="email",null=False)
    password=models.CharField(max_length=30,default="password",null=False)
