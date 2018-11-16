from django.db import models
from registration.models import Person
#from noteBook.settings import MEDIA_ROOT
#from time import time
# Create your models here.


# def get_upload_filename(instance,filename):
#     return "upload_files/%s_%s" % (str(time()).replace('.','_'),filename)

class NewUpload(models.Model):
    Selected_Person=models.ForeignKey(Person,on_delete=models.CASCADE)
    Serial_NO=models.AutoField(primary_key=True)
    Course_Title=models.CharField(max_length=10,null=True,default="CSE370")
    Lecture_Date=models.DateField(null=True,default="2018-4-10")
    Size=models.CharField(max_length=4,null=True)
    Department_Name=models.CharField(max_length=30,null=True,default="CSE")
    Choose_File=models.FileField(upload_to='files/')


class NewUpload2(models.Model):
    Dname=models.CharField(max_length=30,null=True)
    courseTitle = models.CharField(max_length=30,null=True)
    faculty=models.CharField(max_length=30,null=True)
    newUpload=models.ForeignKey(NewUpload,on_delete=models.CASCADE)



class contentInfo(models.Model):
    Dname=models.CharField(max_length=30,null=True)
    content=models.CharField(max_length=30,null=True)
    newUpload=models.ForeignKey(NewUpload,on_delete=models.CASCADE)