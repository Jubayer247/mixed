from django.shortcuts import render

# Create your views here.
from fileUpload.models import NewUpload

def viewFiles(request):
    allfiles=NewUpload.objects.all()
    return render(request,'UserView.html',{'allfiles':allfiles})
