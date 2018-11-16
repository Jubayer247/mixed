from django.contrib import admin
from django.urls import path,re_path
from django.shortcuts import render
from registration.RegForm import REGFORM
from login.LoginForm import LogForm
from django.views.decorators.csrf import csrf_protect
from login.FIleUploadForm import UploadFileForm



@csrf_protect
def NewUserNLogin(request):
    form =REGFORM()
    form2=LogForm()
    form3=UploadFileForm()

    if request.method=='POST':
        form=REGFORM(request.POST)
        form2=LogForm(request.POST)
        form3=UploadFileForm(request.POST,request.FILES)
        if form.is_valid():
            user=form.save()
            user.set_password()

        if form2.is_valid():

            form2.save(commit=True)
        if form3.is_valid():
            # f=form3.fields
            # f2i=form3.save(commit=False)
            # nw2=NewUpload2()
            # nw2.Dname=f2i.Department_Name
            # nw2.newUpload=f2i
            # nw2.courseTitle=f2i.Course_Title
            # nw2.save()
            # cif=contentInfo()
            # cif.newUpload=f2i
            # cif.Dname=f2i
            # cif.content=f2i.Course_Title
            # f2i.save()
            form3.save(commit=True)
        return render(request, 'upload/upload.html',{'form3':form3})

    return render(request, 'index.html', {'form': form,'form2':form2})
