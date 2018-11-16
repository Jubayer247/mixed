"""noteBook URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
#from django.contrib import admin
from django.urls import path,re_path
from django.shortcuts import render
from django.conf.urls.static import static
from django.conf import settings
from fileUpload.views import viewFiles
from registration.views import NewUserNLogin


from django.conf.urls import url
from django.contrib import admin
from django.contrib.auth import views as auth_views

def files(request):
    if request.method=='POST':
        return render(request, 'upload.html')

    return render(request,'upload.html')

urlpatterns = [
    path('admin/', admin.site.urls),
    re_path('^$',NewUserNLogin,name='home'),
    path('files/',viewFiles,name='Browse'),
    re_path(r'^login/$',auth_views.login,name='login'),
    re_path(r'^logout/$',auth_views.logout,name='logout')
    #re_path('^files/files/[\w]*')
    #re_path(r'^new_user$',registration.views.createNewUser,name="User_registration"),
]



if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)