from django import forms
from registration.models import  Person
from registration.models import UserProfileInfro



class UserProfileInfoForm(forms.ModelForm):



class REGFORM(forms.ModelForm):
    password = forms.CharField(widget=forms.PasswordInput())
    class Meta:
        model=Person
        fields=['name','email','stdID','password',]

